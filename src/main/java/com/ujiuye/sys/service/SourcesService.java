package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourcesByPid(int i);

    void saveInfo(Sources sources);

    Sources getSourcesInfo(Integer sid);

    void updateInfo(Sources sources);

    void delete(Integer id);

    List<Sources> getSourcesListByEid(Integer eid);
}
