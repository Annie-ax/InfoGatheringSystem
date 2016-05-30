package com.hbut.info.commons.model;

/**
 * Created by keaxiang on 2015/12/31.
 */
public class LogVo {
    private String source;
    private String createtime;
    private String happentime;
    private Integer pid;
    private Integer level;
    private String msg;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getHappentime() {
        return happentime;
    }

    public void setHappentime(String happentime) {
        this.happentime = happentime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
