package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IKeywordAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Keyword;
import com.std.forum.dto.req.XN610005Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/** 
 * 分页查询关键字
 * @author: zuixian 
 * @since: 2016年9月28日 下午1:53:50 
 * @history:
 */
public class XN610005 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN610005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Keyword condition = new Keyword();
        condition.setWordForQuery(req.getWord());
        condition.setLevel(req.getLevel());
        condition.setReaction(req.getReaction());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IKeywordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return keywordAO.queryKeywordPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610005Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
