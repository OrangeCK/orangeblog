package com.ck.orangeblogservice.controller;

import com.ck.orangeblogcommon.annotation.CurrentUser;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import com.ck.orangeblogservice.service.ImageBlogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orangeblog/image")
@Api(description = "blog管理")
public class ImageBlogController {
    @Autowired
    private ImageBlogService imageBlogService;

    @RequestMapping(value = "/imageBlogPageList", method = RequestMethod.POST)
    @ApiOperation(value = "blog分页查询", notes = "blog分页查询", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageBlogVo", value = "blog信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "ImageBlogVo"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页数",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_INT),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_INT)
    })
    @ResponseBody
    public ResultData pageList(@RequestBody ImageBlogVo imageBlogVo,
                               @RequestParam(value = "pageIndex",defaultValue = CommonConstant.DEFAULT_PAGE_INDEX) int pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = CommonConstant.DEFAULT_PAGE_SIZE) int pageSize) {
        return imageBlogService.imagePageList(imageBlogVo, pageIndex, pageSize);
    }

    @RequestMapping(value = "/saveImageBlog", method = RequestMethod.POST)
    @ApiOperation(value = "保存blog", notes = "保存blog", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "userVo", value = "blog信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "ImageBlogVo")
    @ResponseBody
    public ResultData saveImageBlog(@ApiParam(hidden = true) @CurrentUser FndUserPo currentUser, @Validated @RequestBody ImageBlogVo imageBlogVo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return imageBlogService.saveImageBlog(imageBlogVo, currentUser);
    }

    @RequestMapping(value = "/deleteImageBlog", method = RequestMethod.POST)
    @ApiOperation(value = "删除blog", notes = "删除blog", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "id", value = "blog的id",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_STRING)
    @ResponseBody
    public ResultData deleteImageBlog(@RequestParam String id) {
        ImageBlogVo imageBlogVo = new ImageBlogVo();
        imageBlogVo.setId(id);
        return imageBlogService.deleteImageBlog(imageBlogVo);
    }

    @RequestMapping(value = "/updateImageBlogStatus", method = RequestMethod.POST)
    @ApiOperation(value = "更新blog状态", notes = "更新blog状态", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "id", value = "blog的id",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = CommonConstant.DATA_TYPE_STRING)
    @ResponseBody
    public ResultData updateImageBlogStatus(@ApiParam(hidden = true) @CurrentUser FndUserPo currentUser, @RequestParam String id) {
        return imageBlogService.updateImageBlogStatus(currentUser, id);
    }

    @RequestMapping(value = "/blogCategoryList", method = RequestMethod.POST)
    @ApiOperation(value = "博客类别list", notes = "博客类别list", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ResponseBody
    public ResultData blogCategoryList() {
        return ResultData.ok(imageBlogService.blogCategoryList());
    }
}
