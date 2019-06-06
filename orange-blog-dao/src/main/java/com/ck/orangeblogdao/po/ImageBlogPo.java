package com.ck.orangeblogdao.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ck.orangeblogdao.pojo.BasePo;

import java.util.Date;

@TableName(value="image_blog")
public class ImageBlogPo extends BasePo {
	/*  */
    private String title;

	/*  */
    private String outline;

	/*  */
    private String content;

	/*  */
    private String markdownText;

	/*  */
    private String categoryId;

    /*  */
    private String categoryName;

    private String parentCategoryId;

    private String parentCategoryName;

    /*  */
    private String authorName;

	/*  */
    private String imageUrl;

    /*  */
    private String status;

    /*  */
    private String statusName;

	/*  */
    private String enableFlag;

	/*  */
    private String sCid;

	/*  */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sCt;

	/*  */
    private String sUid;

	/*  */
    private Date sUt;


    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public String getTitle(){

        return this.title;
    }
    public void setTitle(String title){

        this.title = title;
    }
    public String getOutline(){

        return this.outline;
    }
    public void setOutline(String outline){

        this.outline = outline;
    }
    public String getContent(){

        return this.content;
    }
    public void setContent(String content){

        this.content = content;
    }
    public String getMarkdownText(){

        return this.markdownText;
    }
    public void setMarkdownText(String markdownText){

        this.markdownText = markdownText;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImageUrl(){

        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl){

        this.imageUrl = imageUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
