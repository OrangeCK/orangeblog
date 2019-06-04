package com.ck.orangeblogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ck.orangeblogdao.po.FndRolePo;
import com.ck.orangeblogdao.po.FndUserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FndUserMapper extends BaseMapper<FndUserPo> {

    @Select("select fr.id AS id, fr.role_name AS roleName from m_fnd_role fr,b_user_role_relation br " +
            "where br.role_id = fr.id and br.user_id = #{id}")
    List<FndRolePo> getUserRoles(@Param("id") String id);
}
