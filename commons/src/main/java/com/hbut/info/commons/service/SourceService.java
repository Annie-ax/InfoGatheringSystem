package com.hbut.info.commons.service;

import com.hbut.info.commons.model.Source;

import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
public interface SourceService {
    /**
     * 查询所有信息源
     * @return
     */
    List<Source> findAll();

    /**
     * 保存信息源信息
     * @param source
     */
    void save(Source source);
}
