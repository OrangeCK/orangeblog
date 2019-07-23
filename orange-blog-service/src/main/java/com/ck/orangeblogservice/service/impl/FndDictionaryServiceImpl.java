package com.ck.orangeblogservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogdao.mapper.FndDictionaryMapper;
import com.ck.orangeblogdao.po.FndDictionaryPo;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.FndDictionaryVo;
import com.ck.orangeblogservice.service.FndDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FndDictionaryServiceImpl extends ServiceImpl<FndDictionaryMapper, FndDictionaryPo> implements FndDictionaryService {

    @Override
    public ResultData dictionaryPageList(FndDictionaryVo fndDictionaryVo) {
        Page<FndDictionaryPo> page = new Page(fndDictionaryVo.getPageIndex(), fndDictionaryVo.getPageSize());
        QueryWrapper<FndDictionaryPo> fndDictionaryPoQueryWrapper = new QueryWrapper<>();
        this.initSearchPropertites(fndDictionaryVo, fndDictionaryPoQueryWrapper);
        IPage<FndDictionaryPo> iPage = baseMapper.selectPage(page, fndDictionaryPoQueryWrapper);
        return ResultData.ok(iPage);
    }

    private void initSearchPropertites(FndDictionaryVo fndDictionaryVo, QueryWrapper<FndDictionaryPo> fndDictionaryPoQueryWrapper){
        if(StringUtils.isNotBlank(fndDictionaryVo.getDicCode())){
            fndDictionaryPoQueryWrapper.lambda().eq(FndDictionaryPo::getDicCode, fndDictionaryVo.getDicCode());
        }
        if(StringUtils.isNotBlank(fndDictionaryVo.getDicValue())){
            fndDictionaryPoQueryWrapper.lambda().likeRight(FndDictionaryPo::getDicValue, fndDictionaryVo.getDicValue());
        }
    }

    @Override
    public List<FndDictionaryPo> getAllDictionaryList() {
        QueryWrapper<FndDictionaryPo> fndDictionaryPoQueryWrapper = new QueryWrapper<>();
        fndDictionaryPoQueryWrapper.lambda()
                .eq(FndDictionaryPo::getStatus, LmEnum.STATUS_1.getCode());
        return baseMapper.selectList(fndDictionaryPoQueryWrapper);
    }

    @Override
    public List<FndDictionaryPo> getDictionaryListByType(String dicType) {
        QueryWrapper<FndDictionaryPo> fndDictionaryPoQueryWrapper = new QueryWrapper<>();
        fndDictionaryPoQueryWrapper.lambda()
                .eq(FndDictionaryPo::getDicType, dicType)
                .eq(FndDictionaryPo::getStatus, LmEnum.STATUS_1.getCode());
        return baseMapper.selectList(fndDictionaryPoQueryWrapper);
    }

    @Override
    public ResultData saveDictionary(FndUserPo currentUser, FndDictionaryVo fndDictionaryVo) {
        Date date = DateUtil.date();
        FndDictionaryPo fndDictionaryPo = new FndDictionaryPo();
        fndDictionaryPo.setDicCode(fndDictionaryVo.getDicCode());
        fndDictionaryPo.setDicValue(fndDictionaryVo.getDicValue());
        fndDictionaryPo.setDicDesc(fndDictionaryVo.getDicDesc());
        fndDictionaryPo.setDicType(fndDictionaryVo.getDicType());
        if(StringUtils.isNotBlank(fndDictionaryVo.getId())){
            fndDictionaryPo.setId(fndDictionaryVo.getId());
            fndDictionaryPo.setSUid(currentUser.getId());
            fndDictionaryPo.setSUt(date);
            int count = baseMapper.updateById(fndDictionaryPo);
            if(count < 1){
                return ResultData.error("更新失败");
            }
        }else{
            fndDictionaryPo.setStatus(LmEnum.STATUS_1.getCode());
            fndDictionaryPo.setSCid(currentUser.getId());
            fndDictionaryPo.setSCt(date);
            fndDictionaryPo.setSUid(currentUser.getId());
            fndDictionaryPo.setSUt(date);
            baseMapper.insert(fndDictionaryPo);
        }
        return ResultData.ok();
    }

    @Override
    public ResultData deleteDictionary(String id) {
        int count = baseMapper.deleteById(id);
        if(count > 0){
            return ResultData.ok();
        }else{
            return ResultData.error("删除失败");
        }
    }
}
