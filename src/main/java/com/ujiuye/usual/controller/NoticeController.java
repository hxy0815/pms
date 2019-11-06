package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.common.ResultEntity;
import com.ujiuye.common.StringUtils;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/info/{id}")
    @ResponseBody
    public Notice getNoticeInfo(@PathVariable("id") Integer nid){
        return noticeService.getNoticeInfo(nid);
    }

    @RequestMapping(value = "/latestNoticeList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLatestNoticeList(){
        List<Notice> notices = noticeService.getLatestNoticeList();
        return ResultEntity.success().put("notices",notices);
    }

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNoticeList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        Map<String, Object> parameteMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parseParameterMapToString(parameteMap);
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        PageInfo<Notice> page = noticeService.getNoticeJsonList(pageNum,parameteMap);

        return ResultEntity.success().put("page",page).put("queryStr",queryStr).put("requestURI",requestURI);
    }

    /*@RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNoticeJsonList(){
        List<Notice> list = noticeService.getNoticeJsonList();
        return ResultEntity.success().put("list",list);
    }*/

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }
}
