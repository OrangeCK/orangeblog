package com.ck.orangeblogweb.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.JwtUtil;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogcommon.utils.SpringContextUtil;
import com.ck.orangeblogdao.po.FndUserPo;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 权限验证
 */
public class MyAuthorizaionFilter extends AccessControlFilter {
    private final static Logger logger = LoggerFactory.getLogger(MyAuthorizaionFilter.class);
    private RedisUtil redisUtil;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(redisUtil == null){
            redisUtil = (RedisUtil)SpringContextUtil.getBean("redisUtil");
        }
        // 得到header中的token
        String token = httpServletRequest.getHeader(LmEnum.AUTHORIZATION.getName());
        boolean permSetExist = Optional.ofNullable(redisUtil.hasKey(token))
                .map(s -> redisUtil.hHasKey(token, LmEnum.PERMISSIONS.getName()))
                .orElse(false);
        if(permSetExist){
            String servletPath = httpServletRequest.getServletPath();
//        String[] permArray = JwtUtil.getValue(token, "permList");
            FndUserPo emp = JSONObject.parseObject(redisUtil.hget(token, LmEnum.USER_INFO.getName()).toString(), new TypeReference<FndUserPo>(){});
            Set<String> permissionSet = JSONObject.parseObject(JSON.toJSONString(redisUtil.hget(token, LmEnum.PERMISSIONS.getName())), new TypeReference<Set<String>>(){});
//            Set<String> permissionSet = new HashSet<>(Arrays.asList(JSON.toJSONString(redisUtil.hget(token, LmEnum.PERMISSIONS.getName()))));
//            Set<String> permissionSet = new HashSet<>(Arrays.asList(permArray));
            if(!permissionSet.contains(servletPath)){
                WebUtils.toHttp(servletResponse).sendError(403, "无接口访问权限");
                return false;
            }
        }else{
            WebUtils.toHttp(servletResponse).sendError(403, "无接口访问权限");
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
