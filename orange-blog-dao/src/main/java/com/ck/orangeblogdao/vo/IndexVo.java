package com.ck.orangeblogdao.vo;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value="IndexVo",description="主页对象")
public class IndexVo {

    private String id;

	/*  */
    private String title;

	/*  */
    private String outline;

	/*  */
    private String content;

	/*  */
    private String markdownText;

	/*  */
    private String category;

	/*  */
    private String defaultImageFlag;

	/*  */
    private String imageUrl;

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
    public String getCategory(){

        return this.category;
    }
    public void setCategory(String category){

        this.category = category;
    }
    public String getDefaultImageFlag(){

        return this.defaultImageFlag;
    }
    public void setDefaultImageFlag(String defaultImageFlag){

        this.defaultImageFlag = defaultImageFlag;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
