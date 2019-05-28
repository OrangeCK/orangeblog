package com.ck.orangeblogservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.JwtUtil;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogservice.service.FndPermissionService;
import com.ck.orangeblogservice.service.FndUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/login")
@Api(description = "登陆入口")
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private FndUserService fndUserService;
    @Autowired
    private FndPermissionService fndPermissionService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     * @param jsonStr 用户信息的json字符串
     * @return
     */
    @RequestMapping(value = "/goLogin", method = RequestMethod.POST)
    public ResultData login(@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        // 登录名
        String loginName = jsonObject.getString("loginName");
        // 登录密码
        String password = jsonObject.getString("password");
        FndUserPo employee = fndUserService.loginAccountByLoginName(loginName);
        if(employee == null){
            return ResultData.error("用户名错误");
        }else if(StringUtils.isEmpty(employee.getPassword()) || !employee.getPassword().equals(password)){
            return ResultData.error("密码错误");
        }else{
            Set<String> permList = fndPermissionService.findAllPermissionsById(employee.getId());
            String[] arr = new String[permList.size()];
            permList.toArray(arr);
            String authorization = JwtUtil.sign(loginName, arr);
            redisUtil.hset(authorization, LmEnum.USER_INFO.getName(), JSONObject.toJSONString(employee), LmEnum.LOGIN_INFO_EXPIRE.getNum());
            redisUtil.hset(authorization, LmEnum.PERMISSIONS.getName(), permList, LmEnum.LOGIN_INFO_EXPIRE.getNum());
            Map<String, Object> map = new HashMap<>();
            map.put("Authorization", authorization);
            map.put("Refresh_Token", JwtUtil.refreshSign(loginName, employee.getPassword()));
            map.put("loginName",loginName);
            map.put("id",employee.getId());
            return new ResultData(map);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/quitLogin", method = RequestMethod.POST)
    public ResultData quitLogin(){
        return new ResultData();
    }

    @RequestMapping(value = "/unAuthorization", method = RequestMethod.POST)
    public ResultData unAuthorization(){
        return ResultData.error("登录身份验证错误");
    }
}
