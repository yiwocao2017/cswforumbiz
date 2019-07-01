package com.std.forum.domain;

import java.util.List;

import com.std.forum.dao.base.ABaseDO;

/**
* 子系统配置
* @author: shan
* @since: 2017年03月21日
* @history:
*/
public class Subsystem extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 名称
    private String name;

    // URL
    private String url;

    // 图片
    private String pic;

    // UI位置(1 首页的三； 2 首页的八；3 菜单)
    private Integer location;

    // UI序号
    private Integer orderNo;

    // 属于（1 总 2 可配 3 父级编号）
    private String belong;

    // 地方编号
    private String companyCode;

    // 备注
    private String remark;

    // **************db***************
    private List<String> companyCodeList;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getLocation() {
        return location;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getBelong() {
        return belong;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public List<String> getCompanyCodeList() {
        return companyCodeList;
    }

    public void setCompanyCodeList(List<String> companyCodeList) {
        this.companyCodeList = companyCodeList;
    }

}
