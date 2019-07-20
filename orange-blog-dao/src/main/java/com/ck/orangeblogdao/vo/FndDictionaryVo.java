package com.ck.orangeblogdao.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.pojo.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value="FndDictionaryVo",description="字典对象")
public class FndDictionaryVo {
	/*  */
    private String id;

	/*  */
    @ApiModelProperty(value = "字典编码", example = "")
    @NotBlank(message = "字典编码不能为空")
    private String dicCode;

	/*  */
    @ApiModelProperty(value = "字典", example = "")
    @NotBlank(message = "字典不能为空")
    private String dicValue;

	/*  */
    @ApiModelProperty(value = "字典描述", example = "")
    @NotBlank(message = "字典描述不能为空")
    private String dicDesc;

    @ApiModelProperty(value = "当前页数", example = "1")
    private int pageIndex;

    @ApiModelProperty(value = "每页显示条数", example = "10")
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getId(){

        return this.id;
    }
    public void setId(String id){

        this.id = id;
    }
    public String getDicCode(){

        return this.dicCode;
    }
    public void setDicCode(String dicCode){

        this.dicCode = dicCode;
    }
    public String getDicValue(){

        return this.dicValue;
    }
    public void setDicValue(String dicValue){

        this.dicValue = dicValue;
    }
    public String getDicDesc(){

        return this.dicDesc;
    }
    public void setDicDesc(String dicDesc){

        this.dicDesc = dicDesc;
    }

}
