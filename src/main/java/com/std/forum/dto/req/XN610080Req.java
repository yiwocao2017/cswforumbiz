package com.std.forum.dto.req;

/**
 * 总部修改菜单
 * @author: asus 
 * @since: 2017年3月21日 下午1:35:24 
 * @history:
 */
public class XN610080Req {
    // 编号（必填）
    private String code;

    // 名称（必填）
    private String name;

    // 图片（必填）
    private String pic;

    // 序号（必填）
    private String orderNo;

    // 属于（必填）
    private String belong;

    // 备注（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
