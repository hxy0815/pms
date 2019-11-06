package com.ujiuye.sys.controller;

import com.ujiuye.common.ResultEntity;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {

    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity delete(Integer id){
        sourcesService.delete(id);
        return ResultEntity.success();
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public ModelAndView getSourcesInfo(@PathVariable("id") Integer sid){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pm-edit");
        Sources sources = sourcesService.getSourcesInfo(sid);
        mv.addObject("onesource",sources);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){
        List<Sources> list = sourcesService.getSourcesByPid(0);
        queryChildren(list.get(0));
        return list;
    }

    private void queryChildren(Sources source) {
        Integer id = source.getId();
        List<Sources> sources = sourcesService.getSourcesByPid(id);
        for (Sources source1 : sources) {
            queryChildren(source1);
        }
        source.setChildren(sources);
    }
}
