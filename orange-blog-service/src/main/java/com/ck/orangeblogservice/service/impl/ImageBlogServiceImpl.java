package com.ck.orangeblogservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ImageBlogServiceImpl extends ServiceImpl<ImageBlogMapper, ImageBlogPo> implements ImageBlogService {
    @Autowired
    private ImageBlogMapper imageBlogMapper;

    @Override
    public ResultData imagePageList(ImageBlogVo imageBlogVo, int pageIndex, int pageSize) {
        Page<ImageBlogPo> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<ImageBlogPo> imageBlogPoQueryWrapper = new QueryWrapper<>();
        IPage<ImageBlogPo> ipage = imageBlogMapper.selectPage(page, imageBlogPoQueryWrapper);
        return ResultData.ok(ipage);
    }

    @Override
    public ResultData saveImageBlog(ImageBlogVo imageBlogVo, FndUserPo currentUser) {
        Date date = DateUtil.date();
        ImageBlogPo imageBlogPo = new ImageBlogPo();
        BeanUtils.copyProperties(imageBlogVo, imageBlogPo);
        if(StringUtils.isNotBlank(imageBlogPo.getId())){
            imageBlogPo.setsCid(currentUser.getId());
            imageBlogPo.setsUid(currentUser.getId());
            imageBlogPo.setsCt(date);
            imageBlogPo.setsUt(date);
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
}
