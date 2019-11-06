package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.common.StringUtils;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoxiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/bx")
public class BaoxiaoController {

    @Autowired
    private BaoxiaoService baoxiaoService;

    @RequestMapping(value = "/myList",method = RequestMethod.GET)
    public ModelAndView getMyBaoxiaoList(HttpServletRequest request,HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        Map<String, Object> parameteMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parseParameterMapToString(parameteMap);
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();

        Integer eid = loginUser.getEid();
        PageInfo<Baoxiao> page = baoxiaoService.getMyBaoxiaoList(eid,pageNum,parameteMap);
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        mv.addObject("page",page);
        mv.addObject("queryStr",queryStr);
        mv.addObject("requestURI",requestURI);
        return mv;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Baoxiao baoxiao, HttpSession session){
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        baoxiao.setEmpFk(loginUser.getEid());
        baoxiaoService.saveInfo(baoxiao);
        return "redirect:/mybaoxiao-base.jsp";
    }
}
