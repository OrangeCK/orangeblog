package com.ck.orangeblogservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.ck.orangeblogcommon.annotation.CurrentUser;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.UserVo;
import com.ck.orangeblogservice.service.FndUserService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orangeblog/employee")
@Api(description = "用户管理")
public class FndUserController {
    @Autowired
    private FndUserService fndUserService;

    @RequestMapping(value = "/employeePageList", method = RequestMethod.POST)
    @ApiOperation(value = "用户分页查询", notes = "用户分页查询", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "用户信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "UserVo"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页数",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_INT),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_INT)
    })
    @ResponseBody
    public ResultData pageList(@RequestBody UserVo userVo,
                               @RequestParam(value = "pageIndex",defaultValue = CommonConstant.DEFAULT_PAGE_INDEX) int pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = CommonConstant.DEFAULT_PAGE_SIZE) int pageSize) {
        return fndUserService.getUserPage(userVo, pageIndex, pageSize);
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    @ApiOperation(value = "保存用户", notes = "保存用户", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "userVo", value = "用户信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "UserVo")
    @ResponseBody
    public ResultData saveUser(@ApiParam(hidden = true) @CurrentUser FndUserPo currentUser, @Validated @RequestBody UserVo userVo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return fndUserService.saveUser(userVo, currentUser);
    }

    @RequestMapping(value = "/disableEmployee", method = RequestMethod.POST)
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "id", value = "用户id",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_STRING)
    @ResponseBody
    public ResultData disableEmployee(@RequestParam String id) {
        UserVo userVo = new UserVo();
        userVo.setId(id);
        return fndUserService.deleteUser(userVo);
    }
}
