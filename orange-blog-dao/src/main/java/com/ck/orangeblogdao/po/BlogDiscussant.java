package com.ck.orangeblogdao.po;

import com.ck.orangeblogdao.pojo.BasePo;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;


}
