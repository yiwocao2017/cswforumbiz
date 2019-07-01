package com.std.forum.dto.req;

/** 
 *  觉得该帖子写得好，通过资金奖励作者
 * @author: zuixian 
 * @since: 2016年9月28日 下午1:44:17 
 * @history:
 */
public class XN610122Req {

    // 帖子编号 （必填）
    private String postCode;

    // 打赏金额 （必填）
    private String amount;

    // 操作人 （必填）
    private String userId;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
