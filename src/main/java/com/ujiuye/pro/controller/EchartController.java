package com.ujiuye.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/echart")
public class EchartController {

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDatas(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("IOS",100000);
        map.put("Android",18000);
        map.put("Window",7000);
        map.put("Sambian",6000);
        map.put("HongMeng",8000);
        return map;
    }
}
