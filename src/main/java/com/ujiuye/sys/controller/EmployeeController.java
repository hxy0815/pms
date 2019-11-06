package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.EmpRoleService;
import com.ujiuye.sys.service.EmployeeService;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmpRoleService empRoleService;

    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee emp,String[] roleids){
        int empid = employeeService.saveInfo(emp);
        empRoleService.insert(empid,roleids);
        return "redirect:/user.jsp";
    }

    @RequestMapping(value = "/others",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getOthers(HttpSession session){
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Employee> emps = employeeService.getOthers(eid);
        return emps;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String validateCode = (String)session.getAttribute("validateCode");
        if(code.equalsIgnoreCase(validateCode)){
            session.removeAttribute("validateCode");
            employee = employeeService.login(employee);
            if(employee != null){
                Integer eid = employee.getEid();
                List<Sources> list = sourcesService.getSourcesListByEid(eid);
                session.setAttribute("sourcesList",list);
                session.setAttribute("loginUser",employee);
                return "redirect:/index.jsp";
            }else {
                attributes.addFlashAttribute("errorMsg","用户名或者密码错误");
                return "redirect:/login";
            }
        }
        attributes.addFlashAttribute("errorMsg","验证码错误");
        return "redirect:/login";
    }

    @RequestMapping(value = "/manages",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers(){
        return employeeService.getManagers();
    }
}
