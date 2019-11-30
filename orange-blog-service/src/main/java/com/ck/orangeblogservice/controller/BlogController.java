package com.ck.orangeblogservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ck.orangeblogcommon.annotation.RecordBlogView;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.po.ImageBlogPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.BlogDiscussantVo;
import com.ck.orangeblogservice.service.IBlogDiscussantService;
import com.ck.orangeblogservice.service.ImageBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2019/5/22 0022
 * Description :
 */
@Controller
@RequestMapping("/blog")
@Api(description = "blog信息")
public class BlogController {

    @Autowired
    private ImageBlogService imageBlogService;
    @Autowired
    private IBlogDiscussantService iBlogDiscussantService;

    /**
     * 主页的blogs展示
     * @return
     */
    @RecordBlogView
    @RequestMapping(value = "/blogDetail/{blogId}", method = RequestMethod.POST)
    @ApiOperation(value = "blog的详情", notes = "blog的详情", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ResponseBody
    public ResultData blogDetail(@PathVariable("blogId") String id){
        ImageBlogPo imageBlogPo = imageBlogService.getById(id);
        if (imageBlogPo == null) {
            return ResultData.error("抱歉，丢失了！");
        } else {
            return ResultData.ok(imageBlogPo);
        }
    }

    @ApiOperation(value = "赞", notes = "赞", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping("/praiseBlog/{blogId}")
    @ResponseBody
    public ResultData praiseBlog(@PathVariable("blogId") String id, HttpServletRequest request){
        return imageBlogService.praiseBlog(id, request);
    }

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
    @ApiOperation(value = "留言列表", notes = "留言列表", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "blogDiscussantVo", value = "留言信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "BlogDiscussantVo")
    @ResponseBody
    public ResultData blogDiscussantPageList(@RequestBody BlogDiscussantVo blogDiscussantVo) {
        return iBlogDiscussantService.blogDiscussantPageList(blogDiscussantVo);
    }
}
