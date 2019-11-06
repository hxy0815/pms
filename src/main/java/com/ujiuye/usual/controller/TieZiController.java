package com.ujiuye.usual.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.TieZi;
import com.ujiuye.usual.service.TieZiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/tz")
public class TieZiController {

    @Autowired
    private TieZiService tieZiService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(TieZi tieZi, HttpSession session){
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        tieZi.setEmpFk(eid);
        tieZi.setAddDate(new Date());
        tieZiService.saveInfo(tieZi);
        return "redirect:/main.jsp";
    }
}
