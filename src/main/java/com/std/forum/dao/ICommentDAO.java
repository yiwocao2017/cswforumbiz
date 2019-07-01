/**
 * @Title ICommentDAO.java 
 * @Package com.std.forum.dao 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 上午10:29:46 
 * @version V1.0   
 */
package com.std.forum.dao;

import java.util.List;

import com.std.forum.dao.base.IBaseDAO;
import com.std.forum.domain.Comment;

/** 
 * 评论DAO
 * @author: xieyj 
 * @since: 2016年8月29日 上午10:29:46 
 * @history:
 */
public interface ICommentDAO extends IBaseDAO<Comment> {
    String NAMESPACE = ICommentDAO.class.getName().concat(".");

    public int updateApprove(Comment data);

    public int updateStatus(Comment data);

    public int deleteCommentByPostCode(Comment data);

    public List<Comment> selectLimitList(Comment condition);

    public List<Comment> selectMyList(Comment condition);

    public List<Comment> queryMyCommentList(Comment condition, int start,
            int limit);

    public List<Comment> selectTDList(Comment condition);

    public List<Comment> queryTDCommentList(Comment condition, int start,
            int limit);

    public int updateUserInf(Comment data);

    public int updateLoginName(Comment data);
}
