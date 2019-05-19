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

}
