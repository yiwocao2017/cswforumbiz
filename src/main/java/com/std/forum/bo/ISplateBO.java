package com.std.forum.bo;

import java.util.List;

import com.std.forum.bo.base.IPaginableBO;
import com.std.forum.domain.Splate;
import com.std.forum.dto.res.XN610049Res;

public interface ISplateBO extends IPaginableBO<Splate> {

    public boolean isSplateExist(String code);

    public String saveSplate(String name, String parentCode, String pic,
            String orderNo, String userId, Integer isDetault,
            String companyCode, String status, String updater, String remark);

    public int removeSplate(String code);

    public int refreshSplate(String code, String name, String parentCode,
            String pic, String orderNo, String userId, Integer isDefault,
            String companyCode, String status, String updater, String remark,String description);

    public List<Splate> querySplateList(Splate condition);

    public List<Splate> querySplateList(String parentCode);

    public Splate getSplate(String code);

    public List<Splate> getPlateByUserId(String userId);

    public void defaultSplate(Splate splate, Integer isDetault, String updater);

    public List<Splate> queryIsDefaultSplateList(Integer code, String status,
            String companyCode);

    public List<XN610049Res> querySplateDetailList(Splate condition);
}
