package com.ck.orangeblogservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogdao.mapper.FndUserMapper;
import com.ck.orangeblogdao.mapper.FndUserRoleMapper;
import com.ck.orangeblogdao.po.FndRolePo;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.po.FndUserRolePo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.UserVo;
import com.ck.orangeblogservice.service.FndUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FndUserServiceImpl extends ServiceImpl<FndUserMapper, FndUserPo> implements FndUserService {

    @Autowired
    private FndUserMapper fndUserMapper;
    @Autowired
    private FndUserRoleMapper fndUserRoleMapper;

    @Override
    public ResultData getUserPage(UserVo userVo, int pageIndex, int pageSize) {
        Page<FndUserPo> page = new Page(pageIndex, pageSize);
        QueryWrapper<FndUserPo> fndUserPoQueryWrapper = new QueryWrapper<>();
        IPage<FndUserPo> iPage = fndUserMapper.selectPage(page, fndUserPoQueryWrapper);
        return ResultData.ok(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultData saveUser(UserVo userVo) {
        Date date = DateUtil.date();
        FndUserPo fndUserPo = new FndUserPo();
        BeanUtils.copyProperties(userVo, fndUserPo);
        if(fndUserPo.getId() != null){
            fndUserPo.setsUt(date);
            fndUserMapper.updateById(fndUserPo);
            QueryWrapper<FndUserRolePo> fndUserRolePoQueryWrapper = new QueryWrapper<>();
            fndUserRolePoQueryWrapper.eq("user_id", fndUserPo.getId());
            fndUserRoleMapper.delete(fndUserRolePoQueryWrapper);
        }else{
            fndUserPo.setsCt(date);
            fndUserPo.setsUt(date);
            fndUserMapper.insert(fndUserPo);
        }
        for(FndRolePo rolePo : userVo.getRolePoList()){
            FndUserRolePo fndUserRolePo = new FndUserRolePo();
            fndUserRolePo.setUserId(fndUserPo.getId());
            fndUserRolePo.setRoleId(rolePo.getId());
            fndUserRolePo.setsCt(date);
            fndUserRolePo.setsUt(date);
            fndUserRoleMapper.insert(fndUserRolePo);
        }
        return ResultData.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultData deleteUser(UserVo userVo) {
        QueryWrapper<FndUserRolePo> fndUserRolePoQueryWrapper = new QueryWrapper<>();
        fndUserRolePoQueryWrapper.eq("user_id", userVo.getId());
        fndUserRoleMapper.delete(fndUserRolePoQueryWrapper);
        fndUserMapper.deleteById(userVo.getId());
        return ResultData.ok();
    }
}
