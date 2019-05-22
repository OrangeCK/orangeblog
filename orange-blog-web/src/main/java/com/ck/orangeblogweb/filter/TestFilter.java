package com.ck.orangeblogweb.filter;

import com.ck.orangeblogcommon.exception.SysException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        throw new SysException("操作授权失败，授权异常");
    }

}
