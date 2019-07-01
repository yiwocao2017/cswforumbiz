package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.ICommentAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Comment;
import com.std.forum.dto.req.XN610202Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 评论分页查询(oss)
 * @author: xieyj 
 * @since: 2016年10月13日 下午2:10:14 
 * @history:
 */
public class XN610202 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN610202Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        condition.setPostCode(req.getPostCode());
        condition.setContent(req.getContent());
        condition.setStatus(req.getStatus());
        condition.setCompanyCode(req.getCompanyCode());
        String orderColumn = req.getOrderColumn();

        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICommentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commentAO.queryOSSCommentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610202Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
