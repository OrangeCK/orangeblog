package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.pojo.BasePo;

import java.util.Date;

@TableName(value="m_fnd_dictionary")
public class FndDictionaryPo extends BasePo {
	/*  */
    private String dicCode;

	/*  */
    private String dicValue;

	/*  */
    private String dicDesc;

    /**
     * 字典类型
     */
    private String dicType;

	/*  */
    private String status;

	/*  */
    private String sCid;

	/*  */
    private Date sCt;

	/*  */
    private String sUid;

	/*  */
    private Date sUt;

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSCid(){

        return this.sCid;
    }
    public void setSCid(String sCid){

        this.sCid = sCid;
    }
    public Date getSCt(){

        return this.sCt;
    }
    public void setSCt(Date sCt){

        this.sCt = sCt;
    }
    public String getSUid(){

        return this.sUid;
    }
    public void setSUid(String sUid){

        this.sUid = sUid;
    }
    public Date getSUt(){

        return this.sUt;
    }
    public void setSUt(Date sUt){

        this.sUt = sUt;
    }
}
