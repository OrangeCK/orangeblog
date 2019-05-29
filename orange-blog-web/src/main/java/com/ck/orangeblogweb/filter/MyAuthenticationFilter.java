package com.ck.orangeblogweb.filter;

import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.JwtUtil;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogcommon.utils.SpringContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 登录验证
 */
public class MyAuthenticationFilter extends AccessControlFilter {
    private final static Logger logger = LoggerFactory.getLogger(MyAuthenticationFilter.class);
    private RedisUtil redisUtil;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 得到header中的token
        String token = httpServletRequest.getHeader(LmEnum.AUTHORIZATION.getName());
        // 如果存在token,就进入executeLogin方法检查token是否正确
        if(token != null){
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                WebUtils.toHttp(response).sendError(401, e.getMessage());
            }
        }else{
            WebUtils.toHttp(response).sendError(401, "身份认证失败，认证凭据token为空");
        }
        return false;
    }

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 得到header中的token
        String token = httpServletRequest.getHeader(LmEnum.AUTHORIZATION.getName());
        // 获取用户名和密码
        String loginName = JwtUtil.getLoginName(token, LmEnum.LOGIN_NAME.getName());
        if(loginName == null){
            throw new AuthenticationException("身份认证失败，token格式不正确");
        }
        // 验证token是否失效
        if(!JwtUtil.verify(token, loginName)){
            throw new IncorrectCredentialsException("身份认证失败，token失效");
        }
        if(redisUtil == null){
            redisUtil = (RedisUtil)SpringContextUtil.getBean("redisUtil");
        }
        // 验证此账户是否存在
        boolean employeeExist = Optional.ofNullable(redisUtil.hasKey(token))
                .map(s -> redisUtil.hHasKey(token, LmEnum.USER_INFO.getName()))
                .orElse(false);
        if(!employeeExist){
            throw new UnknownAccountException("请重新登录");
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
