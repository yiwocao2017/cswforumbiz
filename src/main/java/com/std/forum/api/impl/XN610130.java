package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.DateUtil;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Post;
import com.std.forum.dto.req.XN610130Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 分页查询帖子(front)(根据发布时间)
 * @author: xieyj 
 * @since: 2016年9月27日 下午7:49:54 
 * @history:
 */
public class XN610130 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610130Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Post condition = new Post();
        condition.setUserId(req.getUserId());
        condition.setTitle(req.getTitle());
        condition.setKeyword(req.getKeyword());
        condition.setPublisher(req.getPublisher());
        condition.setStatus(req.getStatus());
        condition.setIsLock(req.getIsLock());
        condition.setLocation(req.getLocation());
        condition.setPlateCode(req.getPlateCode());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setPublishDatetimeStart(DateUtil.strToDate(
            req.getDateStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setPublishDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPostAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return postAO.queryPostPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610130Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
