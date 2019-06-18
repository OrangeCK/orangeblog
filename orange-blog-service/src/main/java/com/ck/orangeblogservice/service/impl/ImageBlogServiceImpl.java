package com.ck.orangeblogservice.service.impl;

import cn.hutool.core.date.DateUtil;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ImageBlogServiceImpl extends ServiceImpl<ImageBlogMapper, ImageBlogPo> implements ImageBlogService {
    @Autowired
    private ImageBlogMapper imageBlogMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultData imagePageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize) {
        Page<ImageBlogPo> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(imageBlogVo.getTitle())){
            imageBlogPoQueryWrapper.lambda().likeRight(ImageBlogPo::getTitle, imageBlogVo.getTitle());
        }
        if(StringUtils.isNotBlank(imageBlogVo.getCategoryId())){
            imageBlogPoQueryWrapper.lambda().eq(ImageBlogPo::getCategoryId, imageBlogVo.getCategoryId());
        }
        imageBlogPoQueryWrapper.lambda().orderByDesc(ImageBlogPo::getsCt);
        IPage<ImageBlogPo> ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
        return ResultData.ok(ipage);
    }

    @Override
    public ResultData blogsPageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize) {
        Page<ImageBlogPo> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
        boolean searchFlag = false;
        IPage<ImageBlogPo> ipage;
        if(searchFlag){
            // 设置需要查询的字段
            imageBlogPoQueryWrapper.lambda().select(ImageBlogPo::getTitle,ImageBlogPo::getOutline,ImageBlogPo::getAuthorName,ImageBlogPo::getCategoryName,
                    ImageBlogPo::getParentCategoryName,ImageBlogPo::getImageUrl,ImageBlogPo::getStatusName,ImageBlogPo::getsCt,ImageBlogPo::getId);
            ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
        }else{
            ipage = (IPage<ImageBlogPo>) redisUtil.get(LmEnum.INDEX_BLOGS.getName());
        }
        return ResultData.ok(ipage);
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
                .select(ImageBlogPo::getTitle,ImageBlogPo::getOutline,ImageBlogPo::getAuthorName,ImageBlogPo::getCategoryName,
                ImageBlogPo::getParentCategoryName,ImageBlogPo::getImageUrl,ImageBlogPo::getStatusName,ImageBlogPo::getsCt,ImageBlogPo::getId);
        IPage<ImageBlogPo> ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
        redisUtil.set(LmEnum.INDEX_BLOGS.getName(), ipage);
    }
}
