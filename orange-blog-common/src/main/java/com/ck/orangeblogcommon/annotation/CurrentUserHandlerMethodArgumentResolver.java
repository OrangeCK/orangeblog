package com.ck.orangeblogcommon.annotation;

import com.alibaba.fastjson.JSONObject;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogdao.po.FndUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 对当前用户参数注解的实现
 */
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader(LmEnum.AUTHORIZATION.getName());
        FndUserPo fndUserPo = null;
        try {
            fndUserPo = JSONObject.parseObject(redisUtil.hget(token, LmEnum.USER_INFO.getName()).toString(), FndUserPo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fndUserPo;
    }
}
