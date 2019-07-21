package com.ck.orangeblogservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.po.ImageBlogPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.ImageBlogVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ImageBlogService extends IService<ImageBlogPo> {

    ResultData imagePageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize);

    ResultData blogsPageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize, boolean searchFlag);

    ResultData saveImageBlog(ImageBlogVo imageBlogVo, FndUserPo currentUser);

    ResultData deleteImageBlog(ImageBlogVo imageBlogVo);

    ResultData updateImageBlogStatus(FndUserPo currentUser, String id);

    void cacheImageBlogs();

    void cacheBlogsRecordView();

    void updateBlogsRecordView();

    ResultData praiseBlog(String id, HttpServletRequest request);

    ImageBlogPo getBlogDetailById(String id);

    JSONArray blogCategoryList();
}
