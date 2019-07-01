package com.std.forum.ao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.ISYSConfigAO;
import com.std.forum.bo.ISYSConfigBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.SYSConfig;
import com.std.forum.exception.BizException;

/**
 * 系统参数
 * @author: xieyj 
 * @since: 2016年7月23日 下午3:50:20 
 * @history:
 */
@Service
public class SYSConfigAOImpl implements ISYSConfigAO {

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public Long addSYSConfig(SYSConfig data) {
        Long id = null;
        if (data != null) {
            if (StringUtils.isNotBlank(data.getCkey())) {
                SYSConfig condition = new SYSConfig();
                condition.setCkey(data.getCkey());
                if (sysConfigBO.getTotalCount(condition) > 0) {
                    throw new BizException("lh5030", "ckey不能重复");
                }
                sysConfigBO.saveSYSConfig(data);
                id = data.getId();
            }
        }
        return id;
    }

    @Override
    public int editSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null && sysConfigBO.isSYSConfigExist(data.getId())) {
            count = sysConfigBO.refreshSYSConfig(data);
        } else {
            throw new BizException("lh5031", "系统参数ID不存在！");
        }
        return count;
    }

    @Override
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition) {
        return sysConfigBO.getPaginable(start, limit, condition);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        return sysConfigBO.getConfig(id);
    }

    @Override
    public SYSConfig getConfigValue(String ckey) {
        return sysConfigBO.getConfigValue(ckey);
    }

}
