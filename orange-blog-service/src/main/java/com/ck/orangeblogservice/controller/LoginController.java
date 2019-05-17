package com.ck.orangeblogservice.controller;

import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogservice.service.FndUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
@Api(description = "二程订单")
public class LoginController {

    @Autowired
    private FndUserService fndUserService;

    /**
     * 测试
     * @param id
     * @return
     */
    @RequestMapping(value = "/orderToBsp", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "id", value = "测试",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_STRING)
    @ResponseBody
    public ResultData orderToBsp(@RequestParam String id) {
        return fndUserService.testMethod();
    }
}
