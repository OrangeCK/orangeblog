package com.ck.orangeblogservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogdao.mapper.FndPermissionMapper;
import com.ck.orangeblogdao.mapper.FndRolePermMapper;
import com.ck.orangeblogdao.po.FndPermissionPo;
import com.ck.orangeblogdao.po.FndRolePermPo;
import com.ck.orangeblogservice.service.FndPermissionService;
import com.ck.orangeblogservice.service.FndRolePermService;
import org.springframework.stereotype.Service;

@Service
public class FndRolePermServiceImpl extends ServiceImpl<FndRolePermMapper, FndRolePermPo> implements FndRolePermService {
}
