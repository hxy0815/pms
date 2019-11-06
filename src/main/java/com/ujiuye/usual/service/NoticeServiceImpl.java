package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.common.StringUtils;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Resource
    private NoticeMapper noticeMapper;

    public void saveInfo(Notice notice) {
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }

    public PageInfo<Notice> getNoticeJsonList(Integer pageNum, Map<String, Object> parameteMap) {
        /*NoticeExample example = new NoticeExample();
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;*/
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        Map<String, String> mybatisMap = StringUtils.parseParameterMapToMyBatisMap(parameteMap);
        PageHelper.startPage(pageNum,3);
        List<Notice> notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> page = new PageInfo<>(notices,5);
        return page;
    }

    public List<Notice> getLatestNoticeList() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    public Notice getNoticeInfo(Integer nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }
}
