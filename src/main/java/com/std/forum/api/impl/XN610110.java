package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610110Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 发布帖子
 * @author: xieyj 
 * @since: 2016年10月13日 下午1:00:19 
 * @history:
 */
public class XN610110 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610110Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return postAO.publishPost(req.getTitle(), req.getContent(),
            req.getPic(), req.getPlateCode(), req.getPublisher(),
            req.getIsPublish());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610110Req.class);
        StringValidater.validateBlank(req.getTitle(), req.getContent(),
            req.getPlateCode(), req.getPublisher(), req.getIsPublish());
    }
}
