package com.std.forum.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.forum.ao.IPostTalkAO;
import com.std.forum.bo.IAccountBO;
import com.std.forum.bo.ICommentBO;
import com.std.forum.bo.IPostBO;
import com.std.forum.bo.IPostTalkBO;
import com.std.forum.bo.IRuleBO;
import com.std.forum.bo.IUserBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Comment;
import com.std.forum.domain.Post;
import com.std.forum.domain.PostTalk;
import com.std.forum.domain.User;
import com.std.forum.enums.EBizType;
import com.std.forum.enums.EBoolean;
import com.std.forum.enums.EChannelType;
import com.std.forum.enums.EPostStatus;
import com.std.forum.enums.EPostType;
import com.std.forum.enums.ETalkType;
import com.std.forum.exception.BizException;

@Service
public class PostTalkAOImpl implements IPostTalkAO {

    @Autowired
    protected IPostTalkBO postTalkBO;

    @Autowired
    protected IPostBO postBO;

    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected ICommentBO commentBO;

    @Autowired
    protected IRuleBO ruleBO;

    @Autowired
    protected IAccountBO accountBO;

    // 举报帖子
    @Override
    @Transactional
    public void reportPost(String code, String reporter, String reportNote,
            String type) {
        StringBuffer reportNoteBuffer = new StringBuffer(reportNote);
        User user = userBO.getRemoteUser(reporter);
        // 判断是否达到举报条数，更新帖子或评论状态待审核
        if (EPostType.TZ.getCode().equals(type)) {
            type = ETalkType.TZJB.getCode();
            Post post = postBO.getPost(code);
            if (EPostStatus.APPROVE_YES.getCode().equals(post.getStatus())) {
                throw new BizException("xn000000", "该帖已审核通过，不能举报");
            }
            if (EPostStatus.toReportAPPROVE.getCode().equals(post.getStatus())) {
                throw new BizException("xn000000", "该帖已举报成功，正等待处理");
            }
            boolean result = this.isToMax(code, post.getPublisher(), reporter,
                type, reportNoteBuffer);
            if (result) {
                postBO.refreshPostReport(code, reportNoteBuffer.toString());
            }
        } else {
            type = ETalkType.PLJB.getCode();
            Comment comment = commentBO.getComment(code);
            if (comment == null) {
                throw new BizException("xn000000", "评论编号不存在");
            }
            if (EPostStatus.APPROVE_YES.getCode().equals(comment.getStatus())) {
                throw new BizException("xn000000", "该评论已审核通过，不能举报");
            }
            boolean result = this.isToMax(code, comment.getCommer(), reporter,
                type, reportNoteBuffer);
            if (result) {
                commentBO.refreshCommentReport(code,
                    reportNoteBuffer.toString());
            }
        }
        postTalkBO.savePostTalk(code, user, type, reportNote);
    }

    private boolean isToMax(String code, String publisher, String reporter,
            String type, StringBuffer reportNote) {
        boolean result = false;
        // 判断该用户是否已举报
        List<PostTalk> reporterList = postTalkBO.queryPostTalkSingleList(code,
            null, reporter);
        if (CollectionUtils.isNotEmpty(reporterList)) {
            throw new BizException("xn000000", "您已举报成功，无需再次举报");
        }
        List<PostTalk> reportList = postTalkBO.queryPostTalkSingleList(code,
            type, null);
        int maxTimes = ruleBO.getJBTimesByUserId(publisher).intValue();
        StringBuffer sb = new StringBuffer();
        if (reportList.size() + 1 >= maxTimes) {
            for (PostTalk postTalk : reportList) {
                sb.append(postTalk.getRemark()).append(";");
            }
            sb.append(reportNote).append(";");
            reportNote = sb;
            result = true;
        }
        return result;
    }

    @Override
    @Transactional
    public void doPostTalk(String postCode, String userId, String type) {
        PostTalk postTalk = postTalkBO.getPostTalkByCondition(postCode, userId,
            type);
        Post post = postBO.getPost(postCode);
        if (EBoolean.YES.getCode().equals(post.getIsLock())) {
            throw new BizException("xn0000", "帖子已被锁定，不可进行操作");
        }
        User user = userBO.getRemoteUser(userId);
        if (null != postTalk) {
            if (ETalkType.DZ.getCode().equals(type)) {
                postBO.refreshPostSumLike(postCode, post.getSumLike() - 1);
            }
            postTalkBO.removePostTalk(postTalk.getCode());
        } else {
            if (ETalkType.DZ.getCode().equals(type)) {
                postBO.refreshPostSumLike(postCode, post.getSumLike() + 1);
            }
            postTalkBO.savePostTalk(postCode, user, type, ETalkType
                .getTalkTypeMap().get(type).getValue());
        }
    }

    @Override
    @Transactional
    public void doPostTalk(String postCode, String userId, Long amount) {
        Post post = postBO.getPost(postCode);
        if (post.getIsLock().equals(EBoolean.YES.getCode())) {
            throw new BizException("xn0000", "帖子已被锁定，不能打赏");
        }
        if (userId.equals(post.getPublisher())) {
            throw new BizException("xn0000", "用户为发帖人，不能打赏自己");
        }
        User user = userBO.getRemoteUser(userId);
        List<PostTalk> postTalkList = postTalkBO.queryPostTalkSingleList(
            postCode, ETalkType.DS.getCode(), userId);
        if (CollectionUtils.isNotEmpty(postTalkList)) {
            throw new BizException("xn0000", "您已打赏过该贴，不能重复打赏");
        }
        postTalkBO.savePostTalk(postCode, user, ETalkType.DS.getCode(),
            String.valueOf(amount));
        postBO.refreshPostSumReward(postCode, post.getSumReward() + 1);
        accountBO.doTransferAmountRemote(userId, post.getPublisher(),
            EChannelType.JF, amount, EBizType.AJ_DATZ, "打赏帖子", "打赏帖子");
    }

    /** 
     * @see com.std.forum.ao.IPostTalkAO#queryPostTalkPage(int, int, com.std.forum.domain.PostTalk)
     */
    @Override
    public Paginable<PostTalk> queryPostTalkPage(int start, int limit,
            PostTalk condition) {
        return postTalkBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PostTalk> queryPostTalkList(String postCode) {
        PostTalk condition = new PostTalk();
        condition.setPostCode(postCode);
        condition.setType(ETalkType.DS.getCode());
        return postTalkBO.queryPostTalkList(condition);
    }

}
