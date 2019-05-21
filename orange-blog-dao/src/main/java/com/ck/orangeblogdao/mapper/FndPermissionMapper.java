package com.ck.orangeblogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ck.orangeblogdao.po.FndPermissionPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FndPermissionMapper extends BaseMapper<FndPermissionPo> {

    Set<String> findAllPermissionsById(@Param("id") String id);
}
