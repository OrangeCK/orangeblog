package com.ck.orangeblogservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogdao.mapper.FndDictionaryValueMapper;
import com.ck.orangeblogdao.po.FndDictionaryValuePo;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.FndDictionaryValueVo;
import com.ck.orangeblogservice.service.FndDictionaryValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FndDictionaryValueServiceImpl extends ServiceImpl<FndDictionaryValueMapper, FndDictionaryValuePo> implements FndDictionaryValueService {

    @Override
    public ResultData dictionaryValuePageList(FndDictionaryValueVo fndDictionaryValueVo) {
        Page<FndDictionaryValuePo> page = new Page(fndDictionaryValueVo.getPageIndex(), fndDictionaryValueVo.getPageSize());
        QueryWrapper<FndDictionaryValuePo> fndDictionaryValuePoQueryWrapper = new QueryWrapper<>();
        this.initSearchPropertites(fndDictionaryValueVo, fndDictionaryValuePoQueryWrapper);
        IPage<FndDictionaryValuePo> iPage = baseMapper.selectPage(page, fndDictionaryValuePoQueryWrapper);
        return ResultData.ok(iPage);
    }

    private void initSearchPropertites(FndDictionaryValueVo fndDictionaryValueVo, QueryWrapper<FndDictionaryValuePo> fndDictionaryValuePoQueryWrapper){
        if(StringUtils.isNotBlank(fndDictionaryValueVo.getDicCode())){
            fndDictionaryValuePoQueryWrapper.lambda().eq(FndDictionaryValuePo::getDicCode, fndDictionaryValueVo.getDicCode());
        }
        if(StringUtils.isNotBlank(fndDictionaryValueVo.getDicValCode())){
            fndDictionaryValuePoQueryWrapper.lambda().eq(FndDictionaryValuePo::getDicValCode, fndDictionaryValueVo.getDicValCode());
        }
        if(StringUtils.isNotBlank(fndDictionaryValueVo.getDicValValue())){
            fndDictionaryValuePoQueryWrapper.lambda().likeRight(FndDictionaryValuePo::getDicValValue, fndDictionaryValueVo.getDicValValue());
        }
    }

    @Override
    public ResultData saveDictionaryValue(FndUserPo currentUser, FndDictionaryValueVo fndDictionaryValueVo) {
        Date date = DateUtil.date();
        FndDictionaryValuePo fndDictionaryValuePo = new FndDictionaryValuePo();
        fndDictionaryValuePo.setDicCode(fndDictionaryValueVo.getDicCode());
        fndDictionaryValuePo.setDicValCode(fndDictionaryValueVo.getDicValCode());
        fndDictionaryValuePo.setDicValDesc(fndDictionaryValueVo.getDicValDesc());
        fndDictionaryValuePo.setDicValValue(fndDictionaryValueVo.getDicValValue());
        if(StringUtils.isNotBlank(fndDictionaryValueVo.getId())){
            fndDictionaryValuePo.setId(fndDictionaryValueVo.getId());
            fndDictionaryValuePo.setSUid(currentUser.getId());
            fndDictionaryValuePo.setSUt(date);
            int count = baseMapper.updateById(fndDictionaryValuePo);
            if(count < 1){
                return ResultData.error("更新失败");
            }
        }else{
            fndDictionaryValuePo.setStatus(LmEnum.STATUS_1.getCode());
            fndDictionaryValuePo.setSCid(currentUser.getId());
            fndDictionaryValuePo.setSCt(date);
            fndDictionaryValuePo.setSUid(currentUser.getId());
            fndDictionaryValuePo.setSUt(date);
            baseMapper.insert(fndDictionaryValuePo);
        }
        return ResultData.ok();
    }

    @Override
    public List<FndDictionaryValuePo> getDictionaryValueListByCode(String dicCode) {
        QueryWrapper<FndDictionaryValuePo> fndDictionaryValuePoQueryWrapper = new QueryWrapper<>();
        fndDictionaryValuePoQueryWrapper.lambda()
                .eq(FndDictionaryValuePo::getDicCode, dicCode)
                .eq(FndDictionaryValuePo::getStatus, LmEnum.STATUS_1.getCode());
        return baseMapper.selectList(fndDictionaryValuePoQueryWrapper);
    }

    @Override
    public FndDictionaryValuePo getDictionaryValueByCode(String dicCode, String dicValCode) {
        QueryWrapper<FndDictionaryValuePo> fndDictionaryValuePoQueryWrapper = new QueryWrapper<>();
        fndDictionaryValuePoQueryWrapper.lambda()
                .eq(FndDictionaryValuePo::getDicCode, dicCode)
                .eq(FndDictionaryValuePo::getDicValCode, dicValCode)
                .eq(FndDictionaryValuePo::getStatus, LmEnum.STATUS_1.getCode());
        return baseMapper.selectOne(fndDictionaryValuePoQueryWrapper);
    }

    @Override
    public ResultData deleteDictionaryValue(String id) {
        int count = baseMapper.deleteById(id);
        if(count > 0){
            return ResultData.ok();
        }else{
            return ResultData.error("删除失败");
        }
    }
}
