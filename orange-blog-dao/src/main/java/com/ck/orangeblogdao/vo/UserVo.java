package com.ck.orangeblogdao.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.po.FndRolePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value="UserVo",description="用户对象")
public class UserVo {
	/*  */
    @ApiModelProperty(value = "id", example = "1")
    private String id;

	/*  */
    @NotBlank(message = "登录名不能为空")
    private String loginName;

	/*  */
    @NotBlank(message = "用户名不能为空")
    private String userName;

	/*  */
    @NotBlank(message = "密码不能为空")
    private String password;

	/*  */
    private String jobNumber;

	/*  */
    private int age;

	/*  */
    private String sex;

	/*  */
    @NotBlank(message = "电话不能为空")
    private String userPhone;

	/*  */
    private String enableFlag;

    private List<FndRolePo> rolePoList = new ArrayList<>();

    public List<FndRolePo> getRolePoList() {
        return rolePoList;
    }

    public void setRolePoList(List<FndRolePo> rolePoList) {
        this.rolePoList = rolePoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

}
