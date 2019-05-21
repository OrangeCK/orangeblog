package com.ck.orangeblogservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ck.orangeblogdao.po.FndPermissionPo;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface FndPermissionService extends IService<FndPermissionPo> {

    Set<String> findAllPermissionsById(String id);
}
