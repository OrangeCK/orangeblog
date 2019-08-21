package com.ck.orangeblogcommon.constant;

/**
 * @author ck
 * @date 2018/11/5 16:12
 * Description  : 枚举类
 */
public enum LmEnum {
    /**
     * 全局异常的提示
     */
    SYSTEM_EXCEPTION("系统繁忙，请稍后再试"),
    /**
     * 性别男、女
     */
    SEX_MALE("男", "1"),SEX_FEMALE("女", "0"),
    /**
     * 状态
     */
    BLOG_STATUS_0("草稿", "0"),BLOG_STATUS_1("已发布", "1"),STATUS_0("失效", "0"),STATUS_1("有效", "1"),
    /**
     * 返回json的code
     */
    RETURN_NUM_200(200),RETURN_NUM_201(201),RETURN_NUM_202(202),RETURN_NUM_203(203),RETURN_NUM_401(401),
    /**
     * Redis的key常量
     */
    MODULE_EMPLOYEE("LM:employee"),INDEX_BLOGS("indexBLogs"),BLOG_RECORDS_VIEW("blogRecordsView"),BLOG_IP_PREFIX("blogIpPrefix_"),IP_EXPIRE_TIME("ipExpireTime"),
    PRAISE_NUM("praiseNum"),BLOG_VIEW("blogView"),IP_PRAISE_EXPIRE_TIME("ipPraiseExpireTime"),
    /**
     * Redis的缓存时间
     */
    LOGIN_INFO_EXPIRE(300),LOGIN_EXPIRE(3600),IP_EXPIRE(60*60),
    /**
     * ES的常量
     */
    ES_INDEX_LMORANGE("lmorange"),ES_TYPE_BLOG("blog"),
    /**
     * 请求类型
     */
    HTTP_METHOD_GET("GET"),HTTP_METHOD_POST("POST"),PARAM_TYPE_BODY("body"),PARAM_TYPE_QUERY("query"),DATA_TYPE_STRING("string"),
    /**
     * 常量名
     */
    AUTHORIZATION("Authorization"),REFRESH_TOKEN("Refresh_Token"),LOGIN_NAME("loginName"),PASSWORD("password"),USER_INFO("userInfo"),ROLES("roles"),PERMISSIONS("permissions"),
    /**
     * 业务表名
     */
    IMAGE_BLOG("image_blog"),
    /**
     * 基础常量
     */
    INT_0(0),INT_1(1),
    /**
     * 阿里OSS访问域名
     */
    ENDPOINT("oss-cn-shanghai.aliyuncs.com"),ACCESS_KEY_ID("LTAIebGfCOUJ8IJp"),ACCESS_KEY_SECRET("R9E47aBiFQz98ywLrGG5tFT9o7O2hS"),BACKET_NAME("lm-oss-bucket"),FOLDER("lmsystem/"),
    ENDPOINT_INTERNAL("oss-cn-shanghai-internal.aliyuncs.com"),WEBSITE_LMORANGE("www.lmorange.com/oss-image");
    private Integer num;
    /**
     * name
     */
    private String name;
    /**
     * code
     */
    private String code;
    private LmEnum(Integer num){
        this.num = num;
    }
    private LmEnum(String name){
        this.name = name;
    }
    private LmEnum(String name, String code){
        this.name = name;
        this.code = code;
    }

    /**
     * 通过name值获取code值
     * @param name
     * @return
     */
    public static String getCode(String name){
        for(LmEnum lmEnum : LmEnum.values()){
            if(lmEnum.getName().equals(name)){
                return lmEnum.getCode();
            }
        }
        return null;
    }

    /**
     * 生成redis的key
     * @param args
     * @return
     */
    public String join(Object... args){
        StringBuilder sb = new StringBuilder(name);
        for(Object arg : args){
            sb.append(":").append(arg);
        }
        return sb.toString();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
