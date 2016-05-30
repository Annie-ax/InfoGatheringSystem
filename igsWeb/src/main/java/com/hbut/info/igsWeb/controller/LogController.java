package com.hbut.info.igsWeb.controller;

import com.hbut.info.commons.model.LogVo;
import com.hbut.info.commons.model.Source;
import com.hbut.info.commons.model.User;
import com.hbut.info.commons.service.LogInfoService;
import com.hbut.info.commons.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by keaxiang on 2015/12/31.
 */

@Controller
public class LogController {
    @Autowired
    private LogInfoService logInfoService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/getLogQueryPage")
    public String getLogManagePage(Model model){
        List<Source> sourceList = new ArrayList<>();
        sourceList = sourceService.findAll();
        model.addAttribute("sourceList",sourceList);
        return "logQuery";
    }

    @RequestMapping("/getChartsPage")
    public String getChartsPage(Model model){
        List<Source> sourceList = new ArrayList<>();
        sourceList = sourceService.findAll();
        model.addAttribute("sourceList",sourceList);
        return "chartsQuery";
    }

    @RequestMapping("/showCharts")
    public String showCharts(Model model,String sourceSelect){

        //查询所有的数据源
        List<Source> sourceList = new ArrayList<>();
        sourceList = sourceService.findAll();
        model.addAttribute("sourceList",sourceList);
        model.addAttribute("sourceSelect",sourceSelect);

        String tableName = sourceSelect + "_log";
        model.addAttribute("tableName",tableName);
        List<LogVo> logList = new ArrayList<LogVo>();
        List<Integer> pageList = new ArrayList<Integer>();
        HashMap<String,Object> hashMap1 = new HashMap<>();
        HashMap<String,Object> hashMap2 = new HashMap<>();
        HashMap<String,Object> hashMap3 = new HashMap<>();
        hashMap1.put("tableName",tableName);
        hashMap1.put("level",1);
        hashMap2.put("tableName",tableName);
        hashMap2.put("level",2);
        hashMap3.put("tableName",tableName);
        hashMap3.put("level",3);
        int info = 0;
        int warning = 0;
        int error = 0;
        try {
            info = logInfoService.countByLevel(hashMap1);
            warning = logInfoService.countByLevel(hashMap2);
            error = logInfoService.countByLevel(hashMap3);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("info",info);
        model.addAttribute("warning",warning);
        model.addAttribute("error",error);
        return "charts";
    }

    /**
     *
     * @param model
     * @param sourceSelect
     * @return
     */
    @RequestMapping("/Logquery")
    public String logRequest(Model model,String sourceSelect,Integer level,Integer pid){
        String tableName = sourceSelect + "_log";
        model.addAttribute("tableName",tableName);
        model.addAttribute("pid",pid);
        model.addAttribute("level",level);
        List<LogVo> logList = new ArrayList<LogVo>();
        List<Integer> pageList = new ArrayList<Integer>();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("tableName",tableName);
        int sum = 0;

        //pid和level均为null时
        if (pid == null && level == null){
            sum = logInfoService.recordSum(hashMap);
            if (sum > 0){
                logList = logInfoService.findByPage(1, 10, tableName);
            }
            //当pid不为null，level为null时
        }else if (pid != null && level == null){
            hashMap.put("pid",pid);
            sum = logInfoService.countByPid(hashMap);
            if (sum > 0){
                logList = logInfoService.findByPage_pid(1,10,tableName,pid);
            }
          //pid为null,level不为null
        }else if (pid == null && level != null){
            hashMap.put("level",level);
            sum = logInfoService.countByLevel(hashMap);
            if (sum > 0){
                logList = logInfoService.findByPage_level(1, 10, tableName, level);
            }
         //pid和level均不为null
        }else {
            hashMap.put("pid",pid);
            hashMap.put("level",level);
            sum = logInfoService.countByPidAndLevel(hashMap);
            if (sum > 0){
                logList = logInfoService.findByPage_level_pid(1, 10, tableName, pid, level);
            }
        }
        model.addAttribute("pageSum", sum);

        model.addAttribute("logList", logList);
        model.addAttribute("page", 1);
        for (int i = 1; i <= Math.ceil(sum / 10.0); i++)
            pageList.add(i);
        model.addAttribute("pageList",pageList);

        return "findAll";
    }

    /**
     *
     * @param currentPage
     * @param pageSum
     * @param tableName
     * @return
     */
    @RequestMapping("/pageQuery")
    public String pageRequest(Model model,@RequestParam("currentPage") int currentPage,@RequestParam("pageSum") int pageSum,@RequestParam("tableName") String tableName,@RequestParam("pid") Integer pid,@RequestParam("level") Integer level){

        List<LogVo> logList = new ArrayList();

        if (level != null && pid != null){
            logList = logInfoService.findByPage_level_pid(currentPage, 10, tableName, pid, level);
        }else if (level != null && pid == null){
            logList = logInfoService.findByPage_level(currentPage,10,tableName,level);
        }else if (level == null && pid != null){
            logList = logInfoService.findByPage_pid(currentPage,10,tableName,pid);
        }else {
            logList = logInfoService.findByPage(currentPage, 10, tableName);
        }

        //logList = logInfoService.findByPage(currentPage,6,tableName);
        model.addAttribute("logList", logList);
        model.addAttribute("pageSum", pageSum);
        model.addAttribute("tableName", tableName);
        model.addAttribute("pid",pid);
        model.addAttribute("level",level);
        List<Integer> pageList = new ArrayList<Integer>();
        for (int i = 1; i <= Math.ceil(pageSum / 10.0); i++)
            pageList.add(i);
        model.addAttribute("pageList", pageList);
        model.addAttribute("page", currentPage);

        return "findAll";
    }

}
