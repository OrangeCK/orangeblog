package com.ck.orangeblogcommon.annotation;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.IpUtil;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@Aspect
@Component
public class RecordBlogViewAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(RecordBlogView)")
    public void annotationPointcut() {
    }

    @AfterReturning("annotationPointcut()")
    public void afterReturningPointcut(JoinPoint joinPoint){
        HttpServletRequest request = currentRequest();
        String ip = IpUtil.getRealIp(request);
        boolean incrViewTime = false;
        long nowTime = System.currentTimeMillis();
        try {
            Object[] args = joinPoint.getArgs();
            String id = (String) args[0];
            // 此ip是否在限制时间内访问过
            if(redisUtil.hHasKey(id, LmEnum.BLOG_IP_PREFIX.getName() + ip)){
                Object obj = redisUtil.hget(id, LmEnum.BLOG_IP_PREFIX.getName() + ip);
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(obj), new TypeReference<JSONObject>(){});
                long expireTime = jsonObject.getLongValue(LmEnum.IP_EXPIRE_TIME.getName());
                if((nowTime-expireTime) > LmEnum.IP_EXPIRE.getNum()*1000){
                    incrViewTime = true;
                    jsonObject.put(LmEnum.IP_EXPIRE_TIME.getName(), nowTime);
                    redisUtil.hset(id, LmEnum.BLOG_IP_PREFIX.getName() + ip, jsonObject);
                }else{
                    return;
                }
            }else{
                incrViewTime = true;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(LmEnum.IP_EXPIRE_TIME.getName(), nowTime);
                redisUtil.hset(id, LmEnum.BLOG_IP_PREFIX.getName() + ip, jsonObject);
            }
            // 浏览量加1
            if(incrViewTime && redisUtil.hHasKey(LmEnum.BLOG_RECORDS_VIEW.getName(), id)){
                Object viewCount = redisUtil.hget(LmEnum.BLOG_RECORDS_VIEW.getName(), id);
                if(viewCount == null){
                    redisUtil.hset(LmEnum.BLOG_RECORDS_VIEW.getName(),id, 1L);
                }else{
                    long count = Long.valueOf(String.valueOf(viewCount)).longValue();
                    redisUtil.hset(LmEnum.BLOG_RECORDS_VIEW.getName(),id, ++count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpServletRequest currentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }
}
