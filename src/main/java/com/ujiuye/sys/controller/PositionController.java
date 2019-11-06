package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pos")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Position> getPositionList(){
        return positionService.getPositionList();
    }
}
