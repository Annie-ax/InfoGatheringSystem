package com.hbut.info.commons.dao;

import com.hbut.info.commons.model.Source;

import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
public interface SourceMapper {
    /**
     * 查询所有的信息源
     * @return
     */
    List<Source> selectAll();

    /**
     * 插入一条信息源信息
     * @param source
     */
    void insert(Source source);
}
