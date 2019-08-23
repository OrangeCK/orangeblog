package com.ck.orangeblogdao.po;

import com.ck.orangeblogdao.pojo.BasePo;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客讨论表
 * </p>
 *
 * @author ck
 * @since 2019-08-16
 */
@Data
// 此注解会生成equals(Object other) 和 hashCode()方法(callSuper = true,表示可以调用父类中的方法)
@EqualsAndHashCode(callSuper = true)
// chain 一个布尔值。如果为真，产生的setter返回的this而不是void。默认是假。如果fluent=true，那么chain默认为真
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDiscussant extends BasePo {

    /**
     * 博客id
     */
    private String blogId;

    /**
     * 讨论者
     */
    private String discussant;

    /**
     * 讨论者邮箱
     */
    private String discussantEmail;

    /**
     * 讨论者观点
     */
    private String discussantOpinion;

    /**
     * 性别 0-女 1-男
     */
    private String discussantSex;

    /**
     * 状态 0-不通过 1-通过
     */
    private String status;

    /**
     * 状态 0-不通过 1-通过
     */
    private String statusName;

    /**
     * 赞的数量
     */
    private Long praiseNum;

    /**
     * 讨论id
     */
    private String blogDiscussantId;

    /**
     * 头像标识
     */
    private String portrait;

    /**
     * 是否有效标志
     */
    private String enableFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;


}
