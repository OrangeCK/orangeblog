package com.ck.orangeblogservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogdao.mapper.FndRoleMapper;
import com.ck.orangeblogdao.po.FndRolePo;
import com.ck.orangeblogservice.service.FndRoleService;
import org.springframework.stereotype.Service;

@Service
public class FndRoleServiceImpl extends ServiceImpl<FndRoleMapper, FndRolePo> implements FndRoleService {
}
