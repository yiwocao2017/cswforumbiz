package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610117Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 设置/取消帖子置顶,精华和头条
 * @author: xieyj 
 * @since: 2016年10月13日 下午1:02:45 
 * @history:
 */
public class XN610117 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610117Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        postAO.setPostLocation(req.getCode(), req.getLocation(),
            StringValidater.toInteger(req.getOrderNo()), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610117Req.class);
        StringValidater.validateBlank(req.getCode(), req.getLocation(),
            req.getUpdater());
    }
}
