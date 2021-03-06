package com.ck.orangeblogservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.UserVo;

public interface FndUserService extends IService<FndUserPo> {

    ResultData getUserPage(UserVo userVo, int pageIndex, int pageSize);

    ResultData saveUser(UserVo userVo, FndUserPo currentUser);

    ResultData deleteUser(UserVo userVo);

    FndUserPo loginAccountByLoginName(String name);
}
