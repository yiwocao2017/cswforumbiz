package com.std.forum.api.impl;

import com.std.forum.ao.IMenuAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610080Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 总部修改菜单
 * @author: asus 
 * @since: 2017年3月21日 下午1:35:24 
 * @history:
 */
public class XN610080 extends AProcessor {
    private IMenuAO menuAO = SpringContextHolder.getBean(IMenuAO.class);

    private XN610080Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        menuAO.editMenuByGlobal(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610080Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getOrderNo(), req.getPic(), req.getBelong());
    }

}
