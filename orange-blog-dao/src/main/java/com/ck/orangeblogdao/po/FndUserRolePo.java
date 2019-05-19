package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.pojo.BasePo;

import java.util.Date;

@TableName(value="b_user_role_relation")
public class FndUserRolePo extends BasePo {

	/*  */
    private String userId;

	/*  */
    private Long roleId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId(){

        return this.roleId;
    }
    public void setRoleId(Long roleId){

        this.roleId = roleId;
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
