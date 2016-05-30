package com.hbut.info.commons.service;

import com.hbut.info.commons.dao.SourceMapper;
import com.hbut.info.commons.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
@Service
public class SourceServiceImpl implements SourceService{
    @Autowired
    private SourceMapper sourceMapper;
    @Override
    public List<Source> findAll() {
        return sourceMapper.selectAll();
    }

    @Override
    public void save(Source source) {
        sourceMapper.insert(source);
    }
}
