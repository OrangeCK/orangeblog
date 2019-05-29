package com.ck.orangeblogdao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 登录model
 */
@ApiModel(value="LoginVo",description="登录model")
public class LoginVo {

    @ApiModelProperty(value = "登录名", example = "chenkang")
    private String loginName;

    @ApiModelProperty(value = "登录密码", example = "123456")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
