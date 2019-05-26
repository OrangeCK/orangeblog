package com.ck.orangeblogservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import com.ck.orangeblogservice.service.ImageBlogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/indexBlogs", method = RequestMethod.GET)
    public ModelAndView imagePageList(){
        ImageBlogVo imageBlogVo = new ImageBlogVo();
        ResultData resultData = imageBlogService.imagePageList(imageBlogVo, 1, 10);
        ModelAndView mv = new ModelAndView("lmindex");
        mv.addObject("rows", ((IPage)resultData.getData()).getRecords());
        mv.addObject("total", ((IPage)resultData.getData()).getTotal());
        return mv;
    }
}
