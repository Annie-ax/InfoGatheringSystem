package com.hbut.info.commons.dao;


import com.hbut.info.commons.model.LogInfo;
import com.hbut.info.commons.model.Page;

import java.util.HashMap;
import java.util.List;

public interface LogInfoMapper {

    /**
     * 创建表
     * @param hashMap 存储表名  tableName：表名
     */
    void createLogTable(HashMap<String,Object> hashMap);

    /**
     *
     * @return
     */
    List<LogInfo> selectAll(HashMap<String,Object> hashMap);

    /**
     *分页查询所有记录
     * @param page
     * @return
     */
    List<LogInfo> selectByPage(Page page);

    /**
     *根据进程号进行查询（分页）
     * @param params
     * @return
     */
    List<LogInfo> selectByPage_pid(HashMap<String,Object> params);

    /**
     * 查询不同等级的日志记录数
     * @param params
     * @return
     */
    List<LogInfo> selectByPage_level(HashMap<String,Object> params);

    /**
     * 根据进程号和等级查询
     * @param params
     * @return
     */
    List<LogInfo> selectByPage_level_pid(HashMap<String,Object> params);

    /**
     *查询日志记录总数
     * @param params  存储要查询的表名
     * @return
     */
    int selectRecord(HashMap<String,Object> params);

    /**
     * 查询相应等级对应的日志记录数
     * @param params
     * @return
     */
    int countByLevel(HashMap<String,Object> params);

    /**
     * 查询相应线程号的日志记录数
     * @param params
     * @return
     */
    int countByPid(HashMap<String,Object> params);

    /**
     * 查询相应进程号和等级的日志记录数
     * @param params
     * @return
     */
    int countByPidAndLevel(HashMap<String,Object> params);

    /**
     *单条插入日志信息
     * @param params
     */
    void insert(HashMap<String,Object> params);

    /**
     * 批量插入日志信息
     * @param params
     */
    void insertList(HashMap<String,Object> params);
}