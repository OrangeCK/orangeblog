package com.ck.orangeblogservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.NumUtil;
import com.ck.orangeblogdao.po.BlogDiscussant;
import com.ck.orangeblogdao.mapper.BlogDiscussantMapper;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.BlogDiscussantVo;
import com.ck.orangeblogservice.service.IBlogDiscussantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public ResultData saveBlogDiscussant(BlogDiscussantVo blogDiscussantVo) {
        Date date = new Date();
        BlogDiscussant blogDiscussant = BlogDiscussant.builder()
                .blogId(blogDiscussantVo.getBlogId())
                .discussant(blogDiscussantVo.getDiscussant())
                .discussantEmail(blogDiscussantVo.getDiscussantEmail())
                .discussantOpinion(blogDiscussantVo.getDiscussantOpinion())
                .discussantSex(blogDiscussantVo.getDiscussantSex())
                .status(LmEnum.STATUS_1.getCode())
                .statusName(LmEnum.STATUS_1.getName())
                .praiseNum(0L)
                .blogDiscussantId(blogDiscussantVo.getBlogDiscussantId())
                .portrait(String.valueOf(NumUtil.getNum0From9()))
                .createTime(date)
                .updateTime(date)
                .build();
        int count = baseMapper.insert(blogDiscussant);
        if(count > 0){
            return ResultData.ok();
        }else{
            return ResultData.error("发表观点失败");
        }
    }

    @Override
    public ResultData blogDiscussantPageList(BlogDiscussantVo blogDiscussantVo) {
        Page<BlogDiscussant> page = new Page(blogDiscussantVo.getPageIndex(), blogDiscussantVo.getPageSize());
        QueryWrapper<BlogDiscussant> blogDiscussantQueryWrapper = new QueryWrapper<>();
        this.initSearchPropertites(blogDiscussantVo, blogDiscussantQueryWrapper);
        IPage<BlogDiscussant> iPage = baseMapper.selectPage(page, blogDiscussantQueryWrapper);
        return ResultData.ok(iPage);
    }

    private void initSearchPropertites(BlogDiscussantVo blogDiscussantVo, QueryWrapper<BlogDiscussant> blogDiscussantQueryWrapper){
        if(StringUtils.isNotBlank(blogDiscussantVo.getDiscussant())){
            blogDiscussantQueryWrapper.lambda().eq(BlogDiscussant::getDiscussant, blogDiscussantVo.getDiscussant());
        }
    }

}
