package com.ck.orangeblogdao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 登录model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="BlogDiscussantVo",description="留言model")
public class BlogDiscussantVo {
    /**
     * 博客id
     */
    @ApiModelProperty(value = "博客id", example = "111")
    @NotBlank(message = "博客id不能为空")
    private String blogId;
    /**
     * 讨论者
     */
    @ApiModelProperty(value = "讨论者", example = "Kang")
    @NotBlank(message = "讨论者名称不能为空")
    private String discussant;

    /**
     * 讨论者邮箱
     */
    @ApiModelProperty(value = "讨论者邮箱", example = "457897@qq.com")
    @NotBlank(message = "讨论者邮箱不能为空")
    private String discussantEmail;

    /**
     * 讨论者观点
     */
    @ApiModelProperty(value = "讨论者观点", example = "This is great!")
    @NotBlank(message = "讨论者观点不能为空")
    private String discussantOpinion;

    /**
     * 性别 0-女 1-男
     */
    @ApiModelProperty(value = "性别 0-女 1-男", example = "1")
    @NotBlank(message = "性别不能为空")
    private String discussantSex;

    /**
     * 状态 0-不通过 1-通过
     */
    @ApiModelProperty(value = "状态 0-不通过 1-通过", example = "1")
    private String status;

    /**
     * 赞的数量
     */
    @ApiModelProperty(value = "赞的数量", example = "12")
    private Long praiseNum;

    /**
     * 讨论id
     */
    @ApiModelProperty(value = "讨论id", example = "1")
    private String blogDiscussantId;

    @ApiModelProperty(value = "头像标识", example = "1")
    private String portrait;

    @ApiModelProperty(value = "当前页数", example = "1")
    private int pageIndex;

    @ApiModelProperty(value = "每页显示条数", example = "10")
    private int pageSize;
}
