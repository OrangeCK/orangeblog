package com.ck.orangeblogservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.RedisUtil;
import com.ck.orangeblogdao.mapper.FndUserMapper;
import com.ck.orangeblogdao.mapper.FndUserRoleMapper;
import com.ck.orangeblogdao.mapper.ImageBlogMapper;
import com.ck.orangeblogdao.po.FndRolePo;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.po.FndUserRolePo;
import com.ck.orangeblogdao.po.ImageBlogPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.ImageBlogVo;
import com.ck.orangeblogdao.vo.UserVo;
import com.ck.orangeblogservice.service.FndUserService;
import com.ck.orangeblogservice.service.ImageBlogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class ImageBlogServiceImpl extends ServiceImpl<ImageBlogMapper, ImageBlogPo> implements ImageBlogService {
    private final static Logger logger = LoggerFactory.getLogger(ImageBlogServiceImpl.class);

    @Autowired
    private ImageBlogMapper imageBlogMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultData imagePageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize) {
        Page<ImageBlogPo> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
        // 初始化查询条件
        this.initParameter(imageBlogVo, imageBlogPoQueryWrapper);
        imageBlogPoQueryWrapper.lambda().orderByDesc(ImageBlogPo::getsCt);
        IPage<ImageBlogPo> ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
        return ResultData.ok(ipage);
    }

    @Override
    public ResultData blogsPageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize, boolean searchFlag) {
        IPage<ImageBlogPo> ipage;
        if(searchFlag){
            Page<ImageBlogPo> page = new Page<>(pageIndex, pageSize);
            QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
            // 初始化查询条件
            this.initParameter(imageBlogVo, imageBlogPoQueryWrapper);
            // 设置需要查询的字段
            imageBlogPoQueryWrapper.lambda()
                    .eq(ImageBlogPo::getStatus, LmEnum.BLOG_STATUS_1.getCode())
                    .orderByDesc(ImageBlogPo::getPriorityNum, ImageBlogPo::getsCt)
                    .select(ImageBlogPo::getTitle,ImageBlogPo::getOutline,ImageBlogPo::getAuthorName,ImageBlogPo::getCategoryName,
                    ImageBlogPo::getParentCategoryName,ImageBlogPo::getImageUrl,ImageBlogPo::getStatusName,ImageBlogPo::getsCt,ImageBlogPo::getId,
                    ImageBlogPo::getBlogView, ImageBlogPo::getPraiseNum);
            ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
            setBlogRecordView(ipage.getRecords());
        }else{
            ipage = (IPage<ImageBlogPo>) redisUtil.get(LmEnum.INDEX_BLOGS.getName());
        }
        return ResultData.ok(ipage);
    }

    private void setBlogRecordView(List<ImageBlogPo> imageBlogPoList){
        if(!CollectionUtils.isEmpty(imageBlogPoList)){
            imageBlogPoList.parallelStream().forEach(r -> {
                synchronized (redisUtil){
                    JSONObject object = (JSONObject)redisUtil.hget(LmEnum.BLOG_RECORDS_VIEW.getName(), r.getId());
                    long praiseNum = object.getLongValue(LmEnum.PRAISE_NUM.getName());
                    long blogView = object.getLongValue(LmEnum.BLOG_VIEW.getName());
                    r.setBlogView(blogView);
                    r.setPraiseNum(praiseNum);
                }
            });
        }
    }

    private void initParameter(ImageBlogVo imageBlogVo, QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper){
        if(StringUtils.isNotBlank(imageBlogVo.getTitle())){
            imageBlogPoQueryWrapper.lambda().likeRight(ImageBlogPo::getTitle, imageBlogVo.getTitle());
        }
        if(StringUtils.isNotBlank(imageBlogVo.getCategoryId())){
            imageBlogPoQueryWrapper.lambda().eq(ImageBlogPo::getCategoryId, imageBlogVo.getCategoryId());
        }
        if(StringUtils.isNotBlank(imageBlogVo.getParentCategoryId())){
            imageBlogPoQueryWrapper.lambda().eq(ImageBlogPo::getParentCategoryId, imageBlogVo.getParentCategoryId());
        }
    }

    @Override
    public ResultData saveImageBlog(ImageBlogVo imageBlogVo, FndUserPo currentUser) {
        Date date = DateUtil.date();
        ImageBlogPo imageBlogPo = new ImageBlogPo();
        BeanUtils.copyProperties(imageBlogVo, imageBlogPo);
        if(StringUtils.isBlank(imageBlogPo.getId())){
            imageBlogPo.setsCid(currentUser.getId());
            imageBlogPo.setsUid(currentUser.getId());
            imageBlogPo.setAuthorName(currentUser.getUserName());
            imageBlogPo.setsCt(date);
            imageBlogPo.setsUt(date);
            imageBlogPo.setStatus(LmEnum.BLOG_STATUS_0.getCode());
            imageBlogPo.setStatusName(LmEnum.BLOG_STATUS_0.getName());
            imageBlogPo.setPraiseNum(LmEnum.INT_0.getNum().longValue());
            imageBlogPo.setBlogView(LmEnum.INT_0.getNum().longValue());
            imageBlogMapper.insert(imageBlogPo);
        }else{
            imageBlogPo.setsUid(currentUser.getId());
            imageBlogPo.setsUt(date);
            imageBlogMapper.updateById(imageBlogPo);
        }
        return ResultData.ok();
    }

    @Override
    public ResultData deleteImageBlog(ImageBlogVo imageBlogVo) {
        imageBlogMapper.deleteById(imageBlogVo.getId());
        return ResultData.ok();
    }

    @Override
    public ResultData updateImageBlogStatus(FndUserPo currentUser, String id) {
        ImageBlogPo imageBlogPo = new ImageBlogPo();
        imageBlogPo.setId(id);
        imageBlogPo.setStatus(LmEnum.BLOG_STATUS_1.getCode());
        imageBlogPo.setStatusName(LmEnum.BLOG_STATUS_1.getName());
        imageBlogPo.setsUid(currentUser.getId());
        imageBlogPo.setsUt(DateUtil.date());
        int count = imageBlogMapper.updateById(imageBlogPo);
        if(count > 0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(LmEnum.PRAISE_NUM.getName(), id);
            jsonObject.put(LmEnum.BLOG_VIEW.getName(), id);
            redisUtil.hset(LmEnum.BLOG_RECORDS_VIEW.getName(), id, jsonObject);
            return ResultData.ok();
        }else{
            return ResultData.error("更新失败");
        }
    }

    @Scheduled(cron = "0 0 * * * ?")
    @Override
    public void cacheImageBlogs() {
        Page<ImageBlogPo> page = new Page<>(Integer.parseInt(CommonConstant.DEFAULT_PAGE_INDEX), Integer.parseInt(CommonConstant.DEFAULT_PAGE_SIZE));
        QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
        imageBlogPoQueryWrapper.lambda()
                .orderByDesc(ImageBlogPo::getsCt)
                .eq(ImageBlogPo::getStatus, LmEnum.BLOG_STATUS_1.getCode())
                .select(ImageBlogPo::getTitle,ImageBlogPo::getOutline,ImageBlogPo::getAuthorName,ImageBlogPo::getCategoryName, ImageBlogPo::getParentCategoryName,
                        ImageBlogPo::getImageUrl,ImageBlogPo::getStatusName,ImageBlogPo::getsCt,ImageBlogPo::getId, ImageBlogPo::getBlogView, ImageBlogPo::getPraiseNum);
        IPage<ImageBlogPo> ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
        setBlogRecordView(ipage.getRecords());
        redisUtil.set(LmEnum.INDEX_BLOGS.getName(), ipage);
    }

    @Override
    public void cacheBlogsRecordView() {
        QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
        imageBlogPoQueryWrapper.lambda().eq(ImageBlogPo::getStatus, LmEnum.BLOG_STATUS_1.getCode());
        List<ImageBlogPo> imageBlogPoList = imageBlogMapper.selectList(imageBlogPoQueryWrapper);
        // 删除redis记录key
        redisUtil.del(LmEnum.BLOG_RECORDS_VIEW.getName());
        imageBlogPoList.parallelStream().forEach(i -> {
            synchronized (redisUtil){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(LmEnum.PRAISE_NUM.getName(), i.getPraiseNum());
                jsonObject.put(LmEnum.BLOG_VIEW.getName(), i.getBlogView());
                redisUtil.hset(LmEnum.BLOG_RECORDS_VIEW.getName(), i.getId(), jsonObject);
            }
        });
        logger.info("缓存的阅读量:{}", redisUtil.hmget(LmEnum.BLOG_RECORDS_VIEW.getName()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void updateBlogsRecordView() {
        Map<Object, Object> viewMap = redisUtil.hmget(LmEnum.BLOG_RECORDS_VIEW.getName());
        logger.info("{}持久数据库的blog数据:{}", DateUtil.date(), viewMap);
        Set set = viewMap.keySet();
        Iterator i = set.iterator();
        while (i.hasNext()){
            String id = (String)i.next();
            JSONObject object = (JSONObject) viewMap.get(id);
            // 赞的数量
            long praiseNum = object.getLongValue(LmEnum.PRAISE_NUM.getName());
            // 浏览量
            long blogView = object.getLongValue(LmEnum.BLOG_VIEW.getName());
            ImageBlogPo imageBlogPo = new ImageBlogPo(id, praiseNum, blogView);
            baseMapper.updateById(imageBlogPo);
        }
    }

    @Override
    public ResultData praiseBlog(String id) {
        JSONObject jsonObject = (JSONObject)redisUtil.hget(LmEnum.BLOG_RECORDS_VIEW.getName(), id);
        if(!ObjectUtils.isEmpty(jsonObject)){
            // 赞的数量
            long praiseNum = jsonObject.getLongValue(LmEnum.PRAISE_NUM.getName());
            jsonObject.put(LmEnum.PRAISE_NUM.getName(), ++praiseNum);
            redisUtil.hset(LmEnum.BLOG_RECORDS_VIEW.getName(),id, jsonObject);
        }
        return ResultData.ok();
    }

    @Override
    public ImageBlogPo getBlogDetailById(String id) {
        ImageBlogPo imageBlogPo = baseMapper.selectById(id);
        if(!ObjectUtils.isEmpty(imageBlogPo)){
            JSONObject jsonObject = (JSONObject)redisUtil.hget(LmEnum.BLOG_RECORDS_VIEW.getName(), id);
            long praiseNum = jsonObject.getLongValue(LmEnum.PRAISE_NUM.getName());
            long blogView = jsonObject.getLongValue(LmEnum.BLOG_VIEW.getName());
            imageBlogPo.setBlogView(blogView);
            imageBlogPo.setPraiseNum(praiseNum);
        }
        return imageBlogPo;
    }
}
