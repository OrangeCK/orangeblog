package com.ck.orangeblogservice.controller;

import com.ck.orangeblogcommon.annotation.CurrentUser;
import com.ck.orangeblogcommon.constant.CommonConstant;
import com.ck.orangeblogdao.po.FndUserPo;
import com.ck.orangeblogdao.pojo.ResultData;
import com.ck.orangeblogdao.vo.FndDictionaryVo;
import com.ck.orangeblogservice.service.FndDictionaryService;
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
@RequestMapping("/orangeblog/dictionary")
@Api(description = "字典管理")
public class FndDictionaryController {

    @Autowired
    private FndDictionaryService fndDictionaryService;

    @RequestMapping(value = "/saveDictionary", method = RequestMethod.POST)
    @ApiOperation(value = "保存字典", notes = "保存字典", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "fndDictionaryVo", value = "字典信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "FndDictionaryVo")
    @ResponseBody
    public ResultData saveDictionary(@ApiParam(hidden = true) @CurrentUser FndUserPo currentUser, @Validated @RequestBody FndDictionaryVo fndDictionaryVo,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return fndDictionaryService.saveDictionary(currentUser, fndDictionaryVo);
    }

    @RequestMapping(value = "/dictionaryPageList", method = RequestMethod.POST)
    @ApiOperation(value = "字典分页查询", notes = "字典分页查询", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "fndDictionaryVo", value = "字典信息",paramType = CommonConstant.PARAM_TYPE_BODY, dataType = "FndDictionaryVo")
    @ResponseBody
    public ResultData dictionaryPageList(@RequestBody FndDictionaryVo fndDictionaryVo) {
        return fndDictionaryService.dictionaryPageList(fndDictionaryVo);
    }

    @RequestMapping(value = "/getAllDictionaryList", method = RequestMethod.POST)
    @ApiOperation(value = "得到所有的字典", notes = "得到所有的字典", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ResponseBody
    public ResultData getAllDictionaryList() {
        return ResultData.ok(fndDictionaryService.getAllDictionaryList());
    }

    @RequestMapping(value = "/deleteDictionary", method = RequestMethod.POST)
    @ApiOperation(value = "删除字典", notes = "删除字典", httpMethod = CommonConstant.HTTP_METHOD_POST)
    @ApiImplicitParam(name = "id", value = "字典id",paramType = CommonConstant.PARAM_TYPE_QUERY, dataType = "String")
    @ResponseBody
    public ResultData deleteDictionary(@RequestParam String id) {
        return fndDictionaryService.deleteDictionary(id);
    }
}
