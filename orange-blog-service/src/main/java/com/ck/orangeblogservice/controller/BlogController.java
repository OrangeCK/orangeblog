package com.ck.orangeblogservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ck.orangeblogcommon.annotation.RecordBlogView;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.po.ImageBlogPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogservice.service.ImageBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * 主页的blogs展示
     * @return
     */
    @RecordBlogView
    @GetMapping(value = "/blogDetail/{blogId}")
    public ModelAndView blogDetail(@PathVariable("blogId") String id){
        ImageBlogPo imageBlogPo = imageBlogService.getById(id);
        ModelAndView mv = new ModelAndView("lmblog");
        mv.addObject("blog", imageBlogPo);
        return mv;
    }

    @ApiOperation(value = "赞", notes = "赞", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping("/praiseBlog/{blogId}")
    @ResponseBody
    public ResultData praiseBlog(@PathVariable("blogId") String id, HttpServletRequest request){
        return imageBlogService.praiseBlog(id, request);
    }
}
