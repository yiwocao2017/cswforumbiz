/**
 * @Title PostTalkBOImpl.java 
 * @Package com.std.forum.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午5:07:00 
 * @version V1.0   
 */
package com.std.forum.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.IPostTalkBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.OrderNoGenerater;
import com.std.forum.dao.IPostTalkDAO;
import com.std.forum.domain.PostTalk;
import com.std.forum.domain.User;
import com.std.forum.enums.EPrefixCode;
import com.std.forum.enums.ETalkType;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 下午5:07:00 
 * @history:
 */
@Component
public class PostTalkBOImpl extends PaginableBOImpl<PostTalk> implements
        IPostTalkBO {
    @Autowired
    private IPostTalkDAO postTalkDAO;

    /** 
     * @see com.std.forum.bo.IPostTalkBO#savePostTalk(com.std.forum.domain.PostTalk)
     */
    @Override
    public int savePostTalk(String postCode, User user, String type,
            String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(postCode)) {
            String code = OrderNoGenerater.generate(EPrefixCode.POSTTALK
                .getCode());
            PostTalk data = new PostTalk();
            data.setCode(code);
            data.setPostCode(postCode);
            data.setTalker(user.getUserId());
            data.setNickname(user.getNickname());
            data.setPhoto(user.getPhoto());

            data.setLoginName(user.getLoginName());
            data.setType(type);
            data.setTalkDatetime(new Date());
            data.setRemark(remark);
            count = postTalkDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostTalkBO#queryPostTalkList(com.std.forum.domain.PostTalk)
     */
    @Override
    public List<PostTalk> queryPostTalkSingleList(String postCode, String type,
            String talker) {
        List<PostTalk> resultList = null;
        if (StringUtils.isNotBlank(postCode)) {
            PostTalk condition = new PostTalk();
            condition.setPostCode(postCode);
            condition.setType(type);
            condition.setTalker(talker);
            resultList = postTalkDAO.selectList(condition);
        }
        return resultList;
    }

    /**
     * @see com.std.forum.bo.IPostTalkBO#queryPostTalkList(java.lang.String, java.lang.String, int)
     */
    @Override
    public List<PostTalk> queryPostTalkList(String postCode, String type,
            int limit) {
        List<PostTalk> resultList = null;
        if (StringUtils.isNotBlank(postCode) && StringUtils.isNotBlank(type)) {
            PostTalk condition = new PostTalk();
            condition.setPostCode(postCode);
            condition.setType(type);
            if (limit != 0) {
                resultList = postTalkDAO.selectList(condition, 0, limit);
            } else {
                resultList = postTalkDAO.selectList(condition);
            }
        }
        return resultList;
    }

    /** 
     * @see com.std.forum.bo.IPostTalkBO#queryPostTalkList(com.std.forum.domain.PostTalk)
     */
    @Override
    public List<PostTalk> queryPostTalkList(PostTalk condition) {
        return postTalkDAO.selectList(condition);
    }

    @Override
    public List<PostTalk> queryPostTalkLimitList(String postCode) {
        PostTalk condition = new PostTalk();
        condition.setPostCode(postCode);
        condition.setType(ETalkType.DZ.getCode());
        return postTalkDAO.selectLimitList(condition);
    }

    /** 
     * @see com.std.forum.bo.IPostTalkBO#getPostTalk(java.lang.String)
     */
    @Override
    public PostTalk getPostTalk(String code) {
        PostTalk result = null;
        if (StringUtils.isNotBlank(code)) {
            PostTalk condition = new PostTalk();
            condition.setCode(code);
            result = postTalkDAO.select(condition);
        }
        return result;
    }

    /** 
     * @see com.std.forum.bo.IPostTalkBO#getPostTalkByCondition(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public PostTalk getPostTalkByCondition(String postCode, String userId,
            String type) {
        PostTalk result = null;
        if (StringUtils.isNotBlank(postCode) && StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(type)) {
            PostTalk condition = new PostTalk();
            condition.setPostCode(postCode);
            condition.setTalker(userId);
            condition.setType(type);
            result = postTalkDAO.select(condition);
        }
        return result;
    }

    @Override
    public int removePostTalk(String code) {
        int count = 0;
        if (null != code) {
            PostTalk data = new PostTalk();
            data.setCode(code);
            count = postTalkDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostTalkBO#getPostTalkTotalCount(java.lang.String, java.lang.String)
     */
    @Override
    public long getPostTalkTotalCount(String postCode, String type) {
        long totalCount = 0;
        if (StringUtils.isNotBlank(postCode) && StringUtils.isNotBlank(type)) {
            PostTalk condition = new PostTalk();
            condition.setPostCode(postCode);
            condition.setType(type);
            totalCount = postTalkDAO.selectTotalCount(condition);
        }
        return totalCount;
    }

    @Override
    public List<PostTalk> queryPostTalkList(String postCode, String type) {
        PostTalk condition = new PostTalk();
        condition.setPostCode(postCode);
        condition.setType(type);
        return postTalkDAO.selectComList(condition);
    }

    @Override
    public void updateUserInf(String userId, String nickname, String photo) {
        PostTalk data = new PostTalk();
        data.setTalker(userId);
        data.setNickname(nickname);
        data.setPhoto(photo);
        postTalkDAO.updateUserInf(data);
    }

    @Override
    public void updateUserInf(String userId, String loginName) {
        PostTalk data = new PostTalk();
        data.setTalker(userId);
        data.setLoginName(loginName);
        postTalkDAO.updateLoginName(data);
    }
}
