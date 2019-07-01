/**
 * @Title SYSDictBOImpl.java 
 * @Package com.xnjr.moom.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:50:06 
 * @version V1.0   
 */
package com.std.forum.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.ISYSDictBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.dao.ISYSDictDAO;
import com.std.forum.domain.SYSDict;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:50:06 
 * @history:
 */
@Component
public class SYSDictBOImpl extends PaginableBOImpl<SYSDict> implements
        ISYSDictBO {
    @Autowired
    private ISYSDictDAO sysDictDAO;

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#saveSYSDict(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public Long saveSYSDict(SYSDict data) {
        Long id = null;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            sysDictDAO.insert(data);
            id = data.getId();
        }
        return id;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#removeSYSDict(java.lang.Long)
     */
    @Override
    public int removeSYSDict(Long id) {
        int count = 0;
        if (id != null) {
            SYSDict data = new SYSDict();
            data.setId(id);
            count = sysDictDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#refreshSYSDict(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public int refreshSYSDict(SYSDict data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = sysDictDAO.update(data);
        }
        return count;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#getSYSDict(java.lang.Long)
     */
    @Override
    public SYSDict getSYSDict(Long id) {
        SYSDict sysDict = null;
        if (id != null) {
            SYSDict data = new SYSDict();
            data.setId(id);
            sysDict = sysDictDAO.select(data);
        }
        return sysDict;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#querySYSDictList(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public List<SYSDict> querySYSDictList(SYSDict condition) {
        return sysDictDAO.selectList(condition);
    }

}
