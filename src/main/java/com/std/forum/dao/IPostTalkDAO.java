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
import com.std.forum.domain.PostTalk;

/** 
 * 点赞/收藏/打赏记录DAO
 * @author: xieyj 
 * @since: 2016年8月29日 上午10:29:46 
 * @history:
 */
public interface IPostTalkDAO extends IBaseDAO<PostTalk> {
    String NAMESPACE = IPostTalkDAO.class.getName().concat(".");

    public List<PostTalk> selectLimitList(PostTalk condition);

    public List<PostTalk> selectComList(PostTalk condition);

    public int updateUserInf(PostTalk data);

    public int updateLoginName(PostTalk data);
}
