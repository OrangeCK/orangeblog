package com.ck.orangeblogservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogdao.mapper.FndPermissionMapper;
import com.ck.orangeblogdao.po.FndPermissionPo;
import com.ck.orangeblogservice.service.FndPermissionService;
import org.springframework.stereotype.Service;

@Service
public class FndPermissionServiceImpl extends ServiceImpl<FndPermissionMapper, FndPermissionPo> implements FndPermissionService {
}
