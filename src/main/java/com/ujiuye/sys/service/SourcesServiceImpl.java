package com.ujiuye.sys.service;

import com.alibaba.fastjson.JSON;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import com.ujiuye.sys.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService{

    @Autowired
    private SourcesMapper sourcesMapper;

    @Autowired
    private JedisPool jedisPool;

    public List<Sources> getSourcesByPid(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }

    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);
    }

    public Sources getSourcesInfo(Integer sid) {
        return sourcesMapper.selectByPrimaryKey(sid);
    }

    public void updateInfo(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    public void delete(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }

    public List<Sources> getSourcesListByEid(Integer eid) {
        Jedis jedis = jedisPool.getResource();

        String s = jedis.get(eid + "");
        List<Sources> sources1 = JSON.parseArray(s, Sources.class);
        if(sources1 == null || sources1.size() == 0){
            sources1 = sourcesMapper.getSourcesListByEid(eid,1);
            for (Sources source : sources1) {
                Integer id = source.getId();
                List<Sources> list = sourcesMapper.getSourcesListByEid(eid, id);
                source.setChildren(list);
            }
            jedis.set(eid+"", JSON.toJSONString(sources1));
        }

        jedis.close();
        return sources1;
    }
}
