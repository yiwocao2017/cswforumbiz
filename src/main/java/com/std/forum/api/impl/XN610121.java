package com.std.forum.api.impl;

import com.std.forum.ao.IPostTalkAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610121Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 点赞/收藏，或取消点赞/收藏帖子
 * @author: xieyj 
 * @since: 2016年10月23日 下午9:21:09 
 * @history:
 */
public class XN610121 extends AProcessor {

    private IPostTalkAO postTalkAO = SpringContextHolder
        .getBean(IPostTalkAO.class);

    private XN610121Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        postTalkAO
            .doPostTalk(req.getPostCode(), req.getUserId(), req.getType());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610121Req.class);
        StringValidater.validateBlank(req.getPostCode(), req.getUserId(),
            req.getType());
    }
}
