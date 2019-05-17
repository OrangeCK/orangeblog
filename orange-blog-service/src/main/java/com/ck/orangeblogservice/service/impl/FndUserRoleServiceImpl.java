package com.ck.orangeblogservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogdao.mapper.FndRoleMapper;
import com.ck.orangeblogdao.mapper.FndUserRoleMapper;
import com.ck.orangeblogdao.po.FndRolePo;
import com.ck.orangeblogdao.po.FndUserRolePo;
import com.ck.orangeblogservice.service.FndRoleService;
import com.ck.orangeblogservice.service.FndUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class FndUserRoleServiceImpl extends ServiceImpl<FndUserRoleMapper, FndUserRolePo> implements FndUserRoleService {
}
