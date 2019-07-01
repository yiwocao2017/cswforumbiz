package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610132Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 获取帖子详情(oss)
 * @author: xieyj 
 * @since: 2016年9月30日 下午10:24:28 
 * @history:
 */
public class XN610201 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610132Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return postAO.getPost(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610132Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
