package com.ck.orangeblogdao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.pojo.BasePo;

import java.util.Date;

@TableName(value="m_fnd_user")
public class FndUserPo extends BasePo {

	/*  */
    private String loginName;

	/*  */
    private String userName;

	/*  */
    private String password;

	/*  */
    private String jobNumber;

	/*  */
    private int age;

	/*  */
    private String sex;

	/*  */
    private String userPhone;

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

    public String getLoginName(){

        return this.loginName;
    }
    public void setLoginName(String loginName){

        this.loginName = loginName;
    }
    public String getUserName(){

        return this.userName;
    }
    public void setUserName(String userName){

        this.userName = userName;
    }
    public String getPassword(){

        return this.password;
    }
    public void setPassword(String password){

        this.password = password;
    }
    public String getJobNumber(){

        return this.jobNumber;
    }
    public void setJobNumber(String jobNumber){

        this.jobNumber = jobNumber;
    }
    public int getAge(){

        return this.age;
    }
    public void setAge(int age){

        this.age = age;
    }
    public String getSex(){

        return this.sex;
    }
    public void setSex(String sex){

        this.sex = sex;
    }
    public String getUserPhone(){

        return this.userPhone;
    }
    public void setUserPhone(String userPhone){

        this.userPhone = userPhone;
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
