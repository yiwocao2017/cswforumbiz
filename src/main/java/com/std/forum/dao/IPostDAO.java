/**
 * @Title IPostDAO.java 
 * @Package com.std.forum.dao 
 * @Description 
 * @author xieyj  
 * @date 2016年8月28日 下午9:41:35 
 * @version V1.0   
 */
package com.std.forum.dao;

import java.util.List;

import com.std.forum.dao.base.IBaseDAO;
import com.std.forum.domain.Post;

/** 
 * @author: xieyj 
 * @since: 2016年8月28日 下午9:41:35 
 * @history:
 */
public interface IPostDAO extends IBaseDAO<Post> {
    String NAMESPACE = IPostDAO.class.getName().concat(".");

    /**
     * 重新提交帖子
     */
    public int update(Post data);

    /**
     * 审核
     */
    public int updateApprove(Post data);

    /**
     * 举报更新状态
     */
    public int updateStatus(Post data);

    /**
     * 设置精华，置顶
     */
    public int updateLocation(Post data);

    /**
     * 设置锁定
     */
    public int updateLock(Post data);

    /**
     * 转版
     */
    public int updatePlate(Post data);

    public long selectPostNum(Post condition);

    /**
     * 打赏数
     */
    public int updateSumReward(Post data);

    /**
     * 阅读数
     */
    public int updateSumRead(Post data);

    /**
     * 点赞数
     */
    public int updateSumLike(Post data);

    /**
     * 评论数
     */
    public int updateSumComment(Post data);

    public int updateTitle(Post data);

    public Long getMyPostCount(Post condition);

    public List<Post> selectSCList(Post condition);

    public List<Post> selectSCList(Post condition, int start, int limit);

    public List<Post> selectTDList(Post condition);

    public List<Post> queryTDPostList(Post condition, int start, int limit);

    public Post selectMaxRead(Post condition);

    public Post selectSumRead(Post condition);

    public int updateUserInf(Post data);

    public int updateLoginName(Post data);

    public List<Post> selectPcList(Post condition);

    public List<Post> queryPcPostList(Post condition, int start, int limit);

}
