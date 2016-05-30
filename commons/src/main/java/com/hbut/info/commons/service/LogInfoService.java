package com.hbut.info.commons.service;

import com.hbut.info.commons.model.LogVo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by keaxiang on 2015/12/31.
 */
public interface LogInfoService {

    /**
     * 创建表
     * @param source 为表名的前缀
     */
    void createLogTable(String source);

    /**
     * 查询全部
     * @return hashMap 存放表名
     */
    List<LogVo> findAll(HashMap<String, Object> hashMap);

    /**
     * 分页查询
     * @param currentPage  当前所在页
     * @param pageSize 每页大小
     * @return
     */
    List<LogVo> findByPage(int currentPage, int pageSize,String tableName);

    /**
     * 根据进程号进行查询
     * @param currentPage
     * @param pageSize
     * @param tableName
     * @param pid
     * @return
     */
    List<LogVo> findByPage_pid(int currentPage, int pageSize, String tableName,int pid);

    /**
     * 根据等级进行查询
     * @param currentPage
     * @param pageSize
     * @param tableName
     * @param level
     * @return
     */
    List<LogVo> findByPage_level(int currentPage, int pageSize, String tableName,int level);

    /**
     * 同时根据进程号和等级查询
     * @param currentPage
     * @param pageSize
     * @param tableName
     * @param pid
     * @param level
     * @return
     */
    List<LogVo> findByPage_level_pid(int currentPage, int pageSize, String tableName,int pid,int level);

    /**
     * 查询数据库总记录数
     * @return
     */
    int recordSum(HashMap<String,Object> params);

    /**
     * 查询不同等级的日志记录数
     * @param params 存储键值对level:xxxx  tableName:xxxx
     * @return
     */
    int countByLevel(HashMap<String,Object> params);

    /**
     * 查询相应进程号的日志记录数
     * @param params 存储键值对pid:xxxx  tableName:xxxx
     * @return
     */
    int countByPid(HashMap<String,Object> params);

    /**
     * 查询相应进程号和等级的日志记录数
     * @param params 存储键值对level:xxxx  pid:xxxx  tableName:xxxx
     * @return
     */
    int countByPidAndLevel(HashMap<String,Object> params);

    /**
     * 单条插入表
     * @param params
     */
    void insert(HashMap<String,Object> params);

    /**
     * 批量插入表
     * @param params
     */
    void insertList(HashMap<String,Object> params);

}
