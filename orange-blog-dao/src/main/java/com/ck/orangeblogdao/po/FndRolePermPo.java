package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value="b_role_perm_relation")
public class FndRolePermPo{
	/*  */
    private Long id;

	/*  */
    private Long roleId;

	/*  */
    private Long permId;

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
