package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610137Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/** 
 * 我收藏的帖子列表查询（）删
 * @author: zuixian 
 * @since: 2016年9月28日 下午1:52:51 
 * @history:
 */
public class XN610137 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610137Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return postAO.querySCPostList(req.getTalker());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610137Req.class);
        StringValidater.validateBlank(req.getTalker());
    }
}
