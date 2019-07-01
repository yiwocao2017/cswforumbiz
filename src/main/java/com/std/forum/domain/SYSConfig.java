/**
 * @Title SYSConfig.java 
 * @Package com.xnjr.moom.domain 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月16日 下午9:45:46 
 * @version V1.0   
 */
package com.std.forum.domain;

import java.util.Date;

import com.std.forum.dao.base.ABaseDO;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月16日 下午9:45:46 
 * @history:
 */
public class SYSConfig extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -6136818068168453506L;

    // 编号（自增长）
    private Long id;

    // 针对系统
    private String toSystem;

    // key值
    private String ckey;

    // value值
    private String cvalue;

    // 最近更新人
    private String updater;

    // 最近更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ************* 模糊查询 *****************
    private String ckeyForQuery;

    private String dhhlFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToSystem() {
        return toSystem;
    }

    public void setToSystem(String toSystem) {
        this.toSystem = toSystem;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCkeyForQuery() {
        return ckeyForQuery;
    }

    public void setCkeyForQuery(String ckeyForQuery) {
        this.ckeyForQuery = ckeyForQuery;
    }

    public String getDhhlFlag() {
        return dhhlFlag;
    }

    public void setDhhlFlag(String dhhlFlag) {
        this.dhhlFlag = dhhlFlag;
    }
}
