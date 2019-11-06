package com.ujiuye.pro.controller;

import com.ujiuye.exception.ZhiFuException;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/withAnalyseJsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectWithAnalyseJsonList(){
        return projectService.getProjectWithAnalyseJsonList();
    }

    @RequestMapping(value = "/info/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public Project info(@PathVariable("pid") Integer pid){
        Project project = projectService.getProjectDetail(pid);
        return project;
    }

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectJsonList(){
        return projectService.getProjectList();
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid,String keyword,Integer orderby){
        List<Project> list = projectService.search(cid,keyword,orderby);
        ModelAndView mv = new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = projectService.batchDelete(ids);
        Map<String,Object> map = new HashMap<String,Object>();
        if(result){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("statusCode",500);
            map.put("message","删除失败");
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Project project){
        projectService.updateProject(project);
        System.out.println(project);
        return "redirect:/pro/list";
    }

    @RequestMapping(value = "/edit/{pid}",method = RequestMethod.GET)
    public String edit(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project = projectService.getProjectDetail(pid);
        map.put("project",project);
        return "project-base-edit";
    }

    @RequestMapping(value = "/detail/{pid}",method = RequestMethod.GET)
    public String getProjectDetail(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project = projectService.getProjectDetail(pid);
        map.put("project",project);
        return "project-base-look";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getProjectList() throws ZhiFuException {
        List<Project> list = projectService.getProjectList();
        ModelAndView mv = new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/project-base.jsp";
    }
}
