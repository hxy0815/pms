package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.common.StringUtils;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaoxiaoServiceImpl implements BaoxiaoService{

    @Autowired
    private BaoxiaoMapper baoxiaoMapper;

    public void saveInfo(Baoxiao baoxiao) {
        String bxid = UUID.randomUUID().toString().replaceAll("-", "");
        baoxiao.setBxid(bxid);
        baoxiao.setBxstatus(0);
        baoxiaoMapper.insert(baoxiao);
    }

    public PageInfo<Baoxiao> getMyBaoxiaoList(Integer eid, Integer pageNum, Map<String, Object> parameteMap) {
        BaoxiaoExample example = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        Map<String, String> mybatisMap = StringUtils.parseParameterMapToMyBatisMap(parameteMap);
        String status = mybatisMap.get("status");
        String keyword = mybatisMap.get("keyword");
        if(status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
        if(keyword != null && keyword != ""){
            criteria.andBxremarkLike(keyword);
        }
        PageHelper.startPage(pageNum,3);
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(example);
        PageInfo<Baoxiao> page = new PageInfo<>(baoxiaos,5);
        return page;
    }

}
