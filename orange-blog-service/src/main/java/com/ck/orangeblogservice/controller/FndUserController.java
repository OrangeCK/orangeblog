package com.ck.orangeblogservice.controller;

import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.UserVo;
import com.ck.orangeblogservice.service.FndUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fndUser")
@Api(description = "用户管理")
public class FndUserController {
    @Autowired
    private FndUserService fndUserService;

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ApiOperation(value = "用户分页查询", notes = "用户分页查询", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo", value = "用户信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "UserVo"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页数",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_INT),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_INT)
    })
    @ResponseBody
    public ResultData pageList(@RequestBody UserVo userVo,
                               @RequestParam(value = "pageIndex",defaultValue = "1") int pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return fndUserService.getUserPage(userVo, pageIndex, pageSize);
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ApiOperation(value = "保存用户", notes = "保存用户", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "userVo", value = "用户信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "UserVo")
    @ResponseBody
    public ResultData saveUser(@Validated @RequestBody UserVo userVo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return fndUserService.saveUser(userVo);
    }
}
