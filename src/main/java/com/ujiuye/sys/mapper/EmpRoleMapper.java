package com.ujiuye.sys.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface EmpRoleMapper {
    void insert(@Param("eid") int empid, @Param("rid") int roleid);
}
