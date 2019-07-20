package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.pojo.BasePo;

import java.util.Date;

@TableName(value="m_fnd_dictionary_value")
public class FndDictionaryValuePo extends BasePo {
	/*  */
    private String dicCode;

	/*  */
    private String dicValCode;

	/*  */
    private String dicValValue;

	/*  */
    private String dicValDesc;

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
