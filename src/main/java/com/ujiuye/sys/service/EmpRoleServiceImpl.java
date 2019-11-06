package com.ujiuye.sys.service;

import com.ujiuye.sys.mapper.EmpRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpRoleServiceImpl implements EmpRoleService{

    @Autowired
    private EmpRoleMapper empRoleMapper;

    public void insert(int empid, String[] roleids) {
        for (String roleid : roleids) {
            empRoleMapper.insert(empid,Integer.parseInt(roleid));
        }
    }
}
