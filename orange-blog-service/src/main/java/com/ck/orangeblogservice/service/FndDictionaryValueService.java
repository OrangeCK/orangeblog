package com.ck.orangeblogservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ck.orangeblogdao.po.FndDictionaryValuePo;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.FndDictionaryValueVo;

import java.util.List;

public interface FndDictionaryValueService extends IService<FndDictionaryValuePo> {

    ResultData saveDictionaryValue(FndUserPo currentUser, FndDictionaryValueVo fndDictionaryValueVo);

    List<FndDictionaryValuePo> getDictionaryValueListByCode(String dicCode);

    FndDictionaryValuePo getDictionaryValueByCode(String dicCode, String dicValCode);

    ResultData dictionaryValuePageList(FndDictionaryValueVo fndDictionaryValueVo);

    ResultData deleteDictionaryValue(String id);
}
