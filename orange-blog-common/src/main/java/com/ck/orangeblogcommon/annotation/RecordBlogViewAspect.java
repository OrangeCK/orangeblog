package com.ck.orangeblogcommon.annotation;

import com.alibaba.fastjson.JSONObject;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class RecordBlogViewAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(RecordBlogView)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            String id = (String) args[0];
            redisUtil.hset(LmEnum.BLOG_RECORDS_VIEW.getName(),id, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint){
        System.out.println("结束"+redisUtil.get("ceshikey"));
    }
}
