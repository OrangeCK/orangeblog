package com.ck.orangeblogweb.config;

import com.ck.orangeblogservice.service.ImageBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动加载
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private ImageBlogService imageBlogService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 缓存主页
        imageBlogService.cacheImageBlogs();
        // 缓存blog的阅读量
        imageBlogService.cacheBlogsRecordView();
    }
}
