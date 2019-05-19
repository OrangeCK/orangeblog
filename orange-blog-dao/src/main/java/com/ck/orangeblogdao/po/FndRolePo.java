package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value="m_fnd_role")
public class FndRolePo{
	/*  */
    private Long id;

	/*  */
    private String roleCode;

	/*  */
    private String roleName;

	/*  */
    private String roleDesc;

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
    public String getRoleCode(){

        return this.roleCode;
    }
    public void setRoleCode(String roleCode){

        this.roleCode = roleCode;
    }
    public String getRoleName(){

        return this.roleName;
    }
    public void setRoleName(String roleName){

        this.roleName = roleName;
    }
    public String getRoleDesc(){

        return this.roleDesc;
    }
    public void setRoleDesc(String roleDesc){

        this.roleDesc = roleDesc;
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
