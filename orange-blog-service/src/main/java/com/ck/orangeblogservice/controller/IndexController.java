package com.ck.orangeblogservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import com.ck.orangeblogservice.service.ImageBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 * @date 2019/5/22 0022
 * Description :
 */
@Controller
@RequestMapping("/index")
@Api(description = "主页")
public class IndexController {

    @Autowired
    private ImageBlogService imageBlogService;

    /**
     * 主页的blogs展示
     * @return
     */
    @ApiOperation(value = "主页展示信息", notes = "主页展示信息", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping(value = "/indexBlogs", method = RequestMethod.GET)
    public ModelAndView imagePageList(){
        ImageBlogVo imageBlogVo = new ImageBlogVo();
        ResultData resultData = imageBlogService.blogsPageList(imageBlogVo, 1, 5);
        ModelAndView mv = new ModelAndView("lmindex");
        mv.addObject("rows", ((IPage)resultData.getData()).getRecords());
        mv.addObject("total", ((IPage)resultData.getData()).getTotal());
        return mv;
    }

    @ApiOperation(value = "得到更多的blogs", notes = "得到更多的blogs", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping(value = "/indexBlogsMore/{pageIndex}/{pageSize}", method = RequestMethod.POST)
    @ResponseBody
    public ResultData indexBlogsMore(@PathVariable("pageIndex") int pIndex, @PathVariable("pageSize") int pSize){
        ImageBlogVo imageBlogVo = new ImageBlogVo();
        ResultData resultData = imageBlogService.blogsPageList(imageBlogVo, pIndex, pSize);
        return ResultData.ok(((IPage)resultData.getData()).getRecords());
    }
}
