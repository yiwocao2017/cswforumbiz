package com.std.forum.api.impl;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.dto.req.XN610408Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 查询统计情况 帖子数 PV数 用户数
 * @author William
 * @since  2017年5月15日 下午4:56:51
 * @history
 */
public class XN610408 extends AProcessor {
    private IPageViewAO pageViewAO = SpringContextHolder
        .getBean(IPageViewAO.class);

    private XN610408Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return pageViewAO.queryNum(req.getCompanyCode(), req.getDateStart(),
            req.getDateEnd());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610408Req.class);
    }
}
