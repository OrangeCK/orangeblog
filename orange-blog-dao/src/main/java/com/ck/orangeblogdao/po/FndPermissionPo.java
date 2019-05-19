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
    private String sCid;

	/*  */
    private Date sCt;

	/*  */
    private String sUid;

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

    public String getsCid() {
        return sCid;
    }

    public void setsCid(String sCid) {
        this.sCid = sCid;
    }

    public Date getsCt() {
        return sCt;
    }

    public void setsCt(Date sCt) {
        this.sCt = sCt;
    }

    public String getsUid() {
        return sUid;
    }

    public void setsUid(String sUid) {
        this.sUid = sUid;
    }

    public Date getsUt() {
        return sUt;
    }

    public void setsUt(Date sUt) {
        this.sUt = sUt;
    }
}
