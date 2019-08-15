package com.ck.orangeblogservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ck.orangeblogcommon.annotation.RecordBlogView;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.EsUtil;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import com.ck.orangeblogservice.service.ImageBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private EsUtil esUtil;

    /**
     * 主页的blogs展示
     * @return
     */
    @ApiOperation(value = "主页展示信息", notes = "主页展示信息", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping(value = "/indexBlogs", method = RequestMethod.GET)
    public ModelAndView imagePageList(){
//        ImageBlogVo imageBlogVo = new ImageBlogVo();
//        ResultData resultData = imageBlogService.blogsPageList(imageBlogVo, Integer.parseInt(CommonConstant.DEFAULT_PAGE_INDEX),
//                Integer.parseInt(CommonConstant.DEFAULT_PAGE_INDEX), false);
        ModelAndView mv = new ModelAndView("lmindex");
//        mv.addObject("rows", ((IPage)resultData.getData()).getRecords());
//        mv.addObject("total", ((IPage)resultData.getData()).getTotal());
        return mv;
    }

    /**
     * 主页的blogs展示
     * @return
     */
//    @ApiOperation(value = "搜索跳转", notes = "搜索跳转", httpMethod = CommonConstant.HTTP_METHOD_POST)
//    @RequestMapping(value = "/indexSearch/{searchStr}", method = RequestMethod.GET)
//    public ModelAndView indexSearch(@PathVariable("searchStr") String searchStr){
//        ModelAndView mv = new ModelAndView("lmindex");
//        return mv;
//    }

    @ApiOperation(value = "得到更多的blogs", notes = "得到更多的blogs", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping(value = "/indexBlogsMore", method = RequestMethod.POST)
    @ResponseBody
    public ResultData indexBlogsMore(@RequestBody ImageBlogVo imageBlogVo){
        boolean searchFlag = true;
        if(StringUtils.isNotBlank(imageBlogVo.getParentCategoryId())){
            searchFlag = false;
        }
        if(StringUtils.isNotBlank(imageBlogVo.getCategoryId())){
            searchFlag = false;
        }
        if(imageBlogVo.getPageIndex() == 1 && searchFlag){
            return imageBlogService.blogsPageList(imageBlogVo, imageBlogVo.getPageIndex(), imageBlogVo.getPageSize(), false);
        }else{
            return imageBlogService.blogsPageList(imageBlogVo, imageBlogVo.getPageIndex(), imageBlogVo.getPageSize(), true);
        }
    }

    @ApiOperation(value = "搜索Blogs", notes = "搜索Blogs", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @RequestMapping(value = "/searchBlogs", method = RequestMethod.POST)
    @ResponseBody
    public ResultData searchBlogs(@RequestParam String searchStr){
        String[] fields = {"title","outline","categoryName","markdownText"};
        JSONArray jsonArray = esUtil.searchData(LmEnum.ES_INDEX_LMORANGE.getName(), LmEnum.ES_TYPE_BLOG.getName(), fields, searchStr);
        return ResultData.ok(jsonArray);
    }
}
