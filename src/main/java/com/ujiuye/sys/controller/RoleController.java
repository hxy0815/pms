package com.ujiuye.sys.controller;

import com.ujiuye.common.ResultEntity;
import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.service.RoleService;
import com.ujiuye.sys.service.RoleSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleSourceService roleSourceService;

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoleList(){
        return roleService.getRoleList();
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){
        int roleid = roleService.saveInfo(role);
        roleSourceService.saveInfo(roleid,ids);
        return ResultEntity.success();
    }
}
