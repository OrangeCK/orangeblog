package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value="b_role_perm_relation")
public class FndRolePermPo{
	/*  */
    private String id;

	/*  */
    private Long roleId;

	/*  */
    private Long permId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRoleId(){

        return this.roleId;
    }
    public void setRoleId(Long roleId){

        this.roleId = roleId;
    }
    public Long getPermId(){

        return this.permId;
    }
    public void setPermId(Long permId){

        this.permId = permId;
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
