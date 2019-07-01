package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IPostTalkAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.PostTalk;
import com.std.forum.dto.req.XN610141Req;
import com.std.forum.enums.ETalkType;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 我收到的赞（分页查询）
 * @author: xieyj 
 * @since: 2016年11月16日 下午2:57:34 
 * @history:
 */
public class XN610141 extends AProcessor {

    private IPostTalkAO postTalkAO = SpringContextHolder
        .getBean(IPostTalkAO.class);

    private XN610141Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PostTalk condition = new PostTalk();
        condition.setPublisher(req.getUserId());
        condition.setPostCode(req.getPostCode());
        condition.setType(ETalkType.DZ.getCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPostTalkAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return postTalkAO.queryPostTalkPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610141Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
