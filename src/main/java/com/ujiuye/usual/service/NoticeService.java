package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    void saveInfo(Notice notice);

    PageInfo<Notice> getNoticeJsonList(Integer pageNum, Map<String, Object> parameteMap);

    List<Notice> getLatestNoticeList();

    Notice getNoticeInfo(Integer nid);
}
