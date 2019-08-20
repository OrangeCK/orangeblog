package com.ck.orangeblogservice.controller;


import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.BlogDiscussantVo;
import com.ck.orangeblogservice.service.IBlogDiscussantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 博客讨论表 前端控制器
 * </p>
 *
 * @author ck
 * @since 2019-08-16
 */
@Controller
@RequestMapping("/orangeblog/blog-discussant")
@Api(description = "博客讨论模块")
public class BlogDiscussantController {

    @Autowired
    private IBlogDiscussantService iBlogDiscussantService;

    @RequestMapping(value = "/saveBlogDiscussant", method = RequestMethod.POST)
    @ApiOperation(value = "发表观点", notes = "发表观点", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "blogDiscussantVo", value = "讨论信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "BlogDiscussantVo")
    @ResponseBody
    public ResultData saveBlogDiscussant(@Validated @RequestBody BlogDiscussantVo blogDiscussantVo,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return iBlogDiscussantService.saveBlogDiscussant(blogDiscussantVo);
    }

    @RequestMapping(value = "/blogDiscussantPageList", method = RequestMethod.POST)
    @ApiOperation(value = "讨论信息分页查询", notes = "讨论信息分页查询", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "blogDiscussantVo", value = "讨论信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "BlogDiscussantVo")
    @ResponseBody
    public ResultData blogDiscussantPageList(@RequestBody BlogDiscussantVo blogDiscussantVo) {
        return iBlogDiscussantService.blogDiscussantPageList(blogDiscussantVo);
    }
}
