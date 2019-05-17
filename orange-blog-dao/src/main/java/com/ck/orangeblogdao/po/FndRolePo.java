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
