package com.hbut.info.commons.service;

import com.hbut.info.commons.dao.LogInfoMapper;
import com.hbut.info.commons.dao.SourceMapper;
import com.hbut.info.commons.model.LogInfo;
import com.hbut.info.commons.model.LogVo;
import com.hbut.info.commons.model.Page;
import com.hbut.info.commons.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by keaxiang on 2015/12/31.
 */

@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;
    @Autowired
    private SourceService sourceService;

    @Override
    public void createLogTable(String source) {
        HashMap<String,Object> hashMap = new HashMap<>();
        String tableName = source + "_log";
        hashMap.put("tableName",tableName);
        logInfoMapper.createLogTable(hashMap);
        Source s = new Source();
        s.setSource(source);
        sourceService.save(s);
    }

    @Override
    public List<LogVo> findAll(HashMap<String, Object> hashMap) {
        List<LogVo> array = new ArrayList<LogVo>();

        List<LogInfo> logList = new ArrayList<LogInfo>();
        logList = logInfoMapper.selectAll(hashMap);
        array = logInfoToLogVo(logList);
        return array;
    }

    @Override
    public List<LogVo> findByPage(int currentPage, int pageSize, String tableName) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTableName(tableName);
        List<LogVo> array = new ArrayList<LogVo>();

        List<LogInfo> logList = new ArrayList<LogInfo>();
        logList = logInfoMapper.selectByPage(page);
        array = logInfoToLogVo(logList);
        return array;
    }

    @Override
    public List<LogVo> findByPage_pid(int currentPage, int pageSize, String tableName,int pid) {
        Page page = new Page();
        HashMap<String,Object> hashMap = new HashMap<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTableName(tableName);
        hashMap.put("page", page);
        hashMap.put("pid",pid);
        List<LogVo> array = new ArrayList<LogVo>();

        List<LogInfo> logList = new ArrayList<LogInfo>();
        logList = logInfoMapper.selectByPage_pid(hashMap);
        array = logInfoToLogVo(logList);
        return array;
    }

    @Override
    public List<LogVo> findByPage_level(int currentPage, int pageSize, String tableName,int level) {
        Page page = new Page();
        HashMap<String,Object> hashMap = new HashMap<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTableName(tableName);
        hashMap.put("page", page);
        hashMap.put("level",level);
        List<LogVo> array = new ArrayList<LogVo>();

        List<LogInfo> logList = new ArrayList<LogInfo>();
        logList = logInfoMapper.selectByPage_level(hashMap);
        array = logInfoToLogVo(logList);
        return array;
    }

    @Override
    public List<LogVo> findByPage_level_pid(int currentPage, int pageSize, String tableName,int pid,int level) {
        Page page = new Page();
        HashMap<String,Object> hashMap = new HashMap<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTableName(tableName);
        hashMap.put("page", page);
        hashMap.put("level",level);
        hashMap.put("pid",pid);
        List<LogVo> array = new ArrayList<LogVo>();

        List<LogInfo> logList = new ArrayList<LogInfo>();
        logList = logInfoMapper.selectByPage_level_pid(hashMap);
        array = logInfoToLogVo(logList);
        return array;
    }

    @Override
    public int recordSum(HashMap<String,Object> params) {
        return logInfoMapper.selectRecord(params);
    }

    @Override
    public int countByLevel(HashMap<String,Object> params) {
        return logInfoMapper.countByLevel(params);
    }

    @Override
    public int countByPid(HashMap<String, Object> params) {
        return logInfoMapper.countByPid(params);
    }

    @Override
    public int countByPidAndLevel(HashMap<String, Object> params) {
        return logInfoMapper.countByPidAndLevel(params);
    }

    @Override
    public void insert(HashMap<String, Object> params) {
        logInfoMapper.insert(params);
    }

    @Override
    public void insertList(HashMap<String, Object> params) {
        logInfoMapper.insertList(params);
    }

    /**
     * 转换日志信息的格式
     * @param logInfoList
     * @return
     */
    private List<LogVo> logInfoToLogVo(List<LogInfo> logInfoList){
        List<LogVo> logVoList = new ArrayList<LogVo>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(int i = 0;i < logInfoList.size();i++){
            LogVo logVo = new LogVo();
            logVo.setSource(logInfoList.get(i).getSource());
            String createtime = null;
            if(logInfoList.get(i).getCreateTime() != null){
                createtime = sdf.format(logInfoList.get(i).getCreateTime());
            }
            logVo.setCreatetime(createtime);
            String time = null;
            if(logInfoList.get(i).getTimeDate() != null){
                time = sdf.format(logInfoList.get(i).getTimeDate()) + "." + logInfoList.get(i).getTimeSecond();
            }
            logVo.setHappentime(time);
            if(logInfoList.get(i).getPid() != null){
                logVo.setPid(logInfoList.get(i).getPid());
            }
            if(logInfoList.get(i).getLevel() != null){
                logVo.setLevel(logInfoList.get(i).getLevel());
            }
            if(logInfoList.get(i).getMsg() != null){
                logVo.setMsg(logInfoList.get(i).getMsg());
            }

            logVoList.add(logVo);
        }
        return logVoList;
    }
}
