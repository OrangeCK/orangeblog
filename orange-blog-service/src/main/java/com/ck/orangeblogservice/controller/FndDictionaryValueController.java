package com.ck.orangeblogservice.controller;

import com.ck.orangeblogcommon.annotation.CurrentUser;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.FndDictionaryValueVo;
import com.ck.orangeblogservice.service.FndDictionaryValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orangeblog/dictionaryValue")
@Api(description = "字典值管理")
public class FndDictionaryValueController {

    @Autowired
    private FndDictionaryValueService fndDictionaryValueService;

    @RequestMapping(value = "/saveDictionaryValue", method = RequestMethod.POST)
    @ApiOperation(value = "保存字典值", notes = "保存字典值", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "fndDictionaryValueVo", value = "字典值信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "FndDictionaryValueVo")
    @ResponseBody
    public ResultData saveDictionaryValue(@ApiParam(hidden = true) @CurrentUser FndUserPo currentUser, @Validated @RequestBody FndDictionaryValueVo fndDictionaryValueVo,
                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return fndDictionaryValueService.saveDictionaryValue(currentUser, fndDictionaryValueVo);
    }

    @RequestMapping(value = "/dictionaryValuePageList", method = RequestMethod.POST)
    @ApiOperation(value = "字典值分页查询", notes = "字典值分页查询", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "fndDictionaryValueVo", value = "字典值信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "FndDictionaryValueVo")
    @ResponseBody
    public ResultData dictionaryValuePageList(@RequestBody FndDictionaryValueVo fndDictionaryValueVo) {
        return fndDictionaryValueService.dictionaryValuePageList(fndDictionaryValueVo);
    }

    @RequestMapping(value = "/getDictionaryTypeList", method = RequestMethod.POST)
    @ApiOperation(value = "得到所有的字典的类型", notes = "得到所有的字典的类型", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ResponseBody
    public ResultData getDictionaryTypeList() {
        return ResultData.ok(fndDictionaryValueService.getDictionaryValueListByCode("dic_type"));
    }

    @RequestMapping(value = "/deleteDictionaryValue", method = RequestMethod.POST)
    @ApiOperation(value = "删除字典值", notes = "删除字典值", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "id", value = "字典值id",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = "String")
    @ResponseBody
    public ResultData deleteDictionaryValue(@RequestParam String id) {
        return fndDictionaryValueService.deleteDictionaryValue(id);
    }
}
