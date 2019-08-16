package com.ck.orangeblogservice.service.impl;

import com.ck.orangeblogdao.po.BlogDiscussant;
import com.ck.orangeblogdao.mapper.BlogDiscussantMapper;
import com.ck.orangeblogservice.service.IBlogDiscussantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客讨论表 服务实现类
 * </p>
 *
 * @author ck
 * @since 2019-08-16
 */
@Service
public class BlogDiscussantServiceImpl extends ServiceImpl<BlogDiscussantMapper, BlogDiscussant> implements IBlogDiscussantService {

}
