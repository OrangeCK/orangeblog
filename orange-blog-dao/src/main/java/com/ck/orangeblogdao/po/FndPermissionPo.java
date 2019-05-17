package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value="m_fnd_permission")
public class FndPermissionPo{
	/*  */
    private Long id;

	/*  */
    private String permCode;

	/*  */
    private String permDesc;

	/*  */
    private String resources;

	/*  */
    private String enableFlag;

	/*  */
    private Long sCid;

	/*  */
    private Date sCt;

	/*  */
    private Long sUid;

	/*  */
    private Date sUt;

    public Long getId(){

        return this.id;
    }
    public void setId(Long id){

        this.id = id;
    }
    public String getPermCode(){

        return this.permCode;
    }
    public void setPermCode(String permCode){

        this.permCode = permCode;
    }
    public String getPermDesc(){

        return this.permDesc;
    }
    public void setPermDesc(String permDesc){

        this.permDesc = permDesc;
    }
    public String getResources(){

        return this.resources;
    }
    public void setResources(String resources){

        this.resources = resources;
    }
    public String getEnableFlag(){

        return this.enableFlag;
    }
    public void setEnableFlag(String enableFlag){

        this.enableFlag = enableFlag;
    }
    public Long getSCid(){

        return this.sCid;
    }
    public void setSCid(Long sCid){

        this.sCid = sCid;
    }
    public Date getSCt(){

        return this.sCt;
    }
    public void setSCt(Date sCt){

        this.sCt = sCt;
    }
    public Long getSUid(){

        return this.sUid;
    }
    public void setSUid(Long sUid){

        this.sUid = sUid;
    }
    public Date getSUt(){

        return this.sUt;
    }
    public void setSUt(Date sUt){

        this.sUt = sUt;
    }
}
