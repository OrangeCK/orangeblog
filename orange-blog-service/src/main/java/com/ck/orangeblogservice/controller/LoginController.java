package com.ck.orangeblogservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.JwtUtil;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.LoginVo;
import com.ck.orangeblogservice.service.FndPermissionService;
import com.ck.orangeblogservice.service.FndUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
     * @param loginVo 用户登录信息
     * @return
     */
    @ApiOperation(value = "登录接口", notes = "登录接口", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "loginVo", value = "用户信息的json字符串",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "LoginVo")
    @RequestMapping(value = "/goLogin", method = RequestMethod.POST)
    public ResultData login(@RequestBody LoginVo loginVo){
        // 登录名
        String loginName = loginVo.getLoginName();
        // 登录密码
        String password = loginVo.getPassword();
        FndUserPo employee = fndUserService.loginAccountByLoginName(loginName);
        if(employee == null){
            return ResultData.error("用户名错误");
        }else if(StringUtils.isEmpty(employee.getPassword()) || !employee.getPassword().equals(password)){
            return ResultData.error("密码错误");
        }else{
            Map<String, Object> map = new HashMap<>();
            try {
                // 查询当前用户的权限
                Set<String> permList = fndPermissionService.findAllPermissionsById(employee.getId());
                // 生成token凭据
                String authorization = JwtUtil.sign(loginName, employee.getPassword());
                // 向redis中缓存当前登录用户信息
                redisUtil.hset(authorization, LmEnum.USER_INFO.getName(), JSONObject.toJSONString(employee), LmEnum.LOGIN_EXPIRE.getNum());
                // 向redis中缓存当前登录用户权限信息
                redisUtil.hset(authorization, LmEnum.PERMISSIONS.getName(), permList, LmEnum.LOGIN_EXPIRE.getNum());
                map.put("Authorization", authorization);
                map.put("Refresh_Token", JwtUtil.refreshSign(loginName, employee.getPassword()));
                map.put("loginName",loginName);
                map.put("userName",employee.getUserName());
                map.put("id",employee.getId());
            } catch (Exception e) {
                logger.info("当前用户：" + employee.getLoginName() + "登录失败");
                e.printStackTrace();
                return ResultData.error("登录出错，请联系管理员");
            }
            logger.info("当前用户：" + employee.getLoginName() + "登录成功");
            return ResultData.ok(map);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "退出登录", notes = "退出登录", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping(value = "/quitLogin", method = RequestMethod.POST)
    public ResultData quitLogin(HttpServletRequest request){
        String token = request.getHeader(LmEnum.AUTHORIZATION.getName());
        if(StringUtils.isNotBlank(token)){
            logger.info("退出登录失败，无效token");
        }
        String loginName = null;
        try {
            loginName = JwtUtil.getLoginName(token, LmEnum.LOGIN_NAME.getName());
            redisUtil.del(token);
        } catch (Exception e) {
            logger.info(loginName + "退出登录失败");
        }
        logger.info("用户：" + loginName + "退出登录");
        return ResultData.ok();
    }

    @ApiOperation(value = "校验是否登录", notes = "校验是否登录", httpMethod = CommonConstant.HTTP_METHOD_GET)
    @RequestMapping(value = "/judgeLogin", method = RequestMethod.GET)
    public ResultData judgeLogin(HttpServletRequest request){
        String token = request.getHeader(LmEnum.AUTHORIZATION.getName());
        String loginName = JwtUtil.getLoginName(token, LmEnum.LOGIN_NAME.getName());
        if(StringUtils.isBlank(loginName)){
            return ResultData.error("身份认证失败，token格式不正确");
        }
        // 验证token是否失效
        if(!JwtUtil.verify(token, loginName)){
            return ResultData.error("身份认证失败，token失效");
        }
        // 验证此账户是否存在
        boolean employeeExist = Optional.ofNullable(redisUtil.hasKey(token))
                .map(s -> redisUtil.hHasKey(token, LmEnum.USER_INFO.getName()))
                .orElse(false);
        if(!employeeExist){
            return ResultData.error("请重新登录");
        }
        return  ResultData.ok();
    }

    /**
     * 测试
     */
    @RequestMapping(value = "/testCompnanyRedis", method = RequestMethod.POST)
    public JSONObject testCompnanyRedis(HttpServletRequest request){
        try {
            BufferedReader br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            logger.info("接收到的字符串:" + wholeStr);
            JSONArray jsonArray = JSON.parseArray(wholeStr);
            System.out.println("JSONArray转换:" + jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success","false");
        return jsonObject;
    }

    @RequestMapping(value = "/unAuthorization", method = RequestMethod.POST)
    public ResultData unAuthorization(){
        return ResultData.error("登录身份验证错误");
    }
}
