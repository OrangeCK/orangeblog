package com.ck.orangeblogservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ck.orangeblogdao.po.FndDictionaryPo;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.FndDictionaryVo;

import java.util.List;

public interface FndDictionaryService extends IService<FndDictionaryPo> {

    ResultData saveDictionary(FndUserPo currentUser, FndDictionaryVo fndDictionaryVo);

    ResultData dictionaryPageList(FndDictionaryVo fndDictionaryVo);

    List<FndDictionaryPo> getAllDictionaryList();

    List<FndDictionaryPo> getDictionaryListByType(String dicType);

    ResultData deleteDictionary(String id);

}
