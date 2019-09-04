package com.ck.orangeblogservice.service;

import com.ck.orangeblogdao.po.BlogDiscussant;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.BlogDiscussantVo;

/**
 * <p>
 * 博客讨论表 服务类
 * </p>
 *
 * @author ck
 * @since 2019-08-16
 */
public interface IBlogDiscussantService extends IService<BlogDiscussant> {

    ResultData saveBlogDiscussant(BlogDiscussantVo blogDiscussantVo);

    ResultData updateBlogDiscussant(BlogDiscussantVo blogDiscussantVo);

    ResultData blogDiscussantPageList(BlogDiscussantVo blogDiscussantVo);
}
