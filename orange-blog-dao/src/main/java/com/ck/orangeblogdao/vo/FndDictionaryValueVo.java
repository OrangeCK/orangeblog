package com.ck.orangeblogdao.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel(value="FndDictionaryValueVo",description="字典值对象")
public class FndDictionaryValueVo {
	/*  */
    private String id;

	/*  */
    @ApiModelProperty(value = "字典编码", example = "")
    @NotBlank(message = "字典编码不能为空")
    private String dicCode;

	/*  */
    @ApiModelProperty(value = "字典值编码", example = "")
    @NotBlank(message = "字典值编码不能为空")
    private String dicValCode;

	/*  */
    @ApiModelProperty(value = "字典值", example = "")
    @NotBlank(message = "字典值不能为空")
    private String dicValValue;

	/*  */
    @ApiModelProperty(value = "字典值描述", example = "")
    @NotBlank(message = "字典值描述不能为空")
    private String dicValDesc;

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
    public String getDicValCode(){

        return this.dicValCode;
    }
    public void setDicValCode(String dicValCode){

        this.dicValCode = dicValCode;
    }
    public String getDicValValue(){

        return this.dicValValue;
    }
    public void setDicValValue(String dicValValue){

        this.dicValValue = dicValValue;
    }
    public String getDicValDesc(){

        return this.dicValDesc;
    }
    public void setDicValDesc(String dicValDesc){

        this.dicValDesc = dicValDesc;
    }

}
