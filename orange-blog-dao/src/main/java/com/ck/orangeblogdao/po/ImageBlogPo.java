package com.ck.orangeblogdao.po;

import java.util.Date;

public class ImageBlogPo{
	/*  */
    private Long id;

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
    private String enableFlag;

	/*  */
    private Long sCid;

	/*  */
    private Date sCt;

	/*  */
    private Long sUid;

	/*  */
    private Date sUt;

    public Long getId(){

        return this.id;
    }
    public void setId(Long id){

        this.id = id;
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
    public String getCategory(){

        return this.category;
    }
    public void setCategory(String category){

        this.category = category;
    }
    public String getEnableFlag(){

        return this.enableFlag;
    }
    public void setEnableFlag(String enableFlag){

        this.enableFlag = enableFlag;
    }
    public Long getSCid(){

        return this.sCid;
    }
    public void setSCid(Long sCid){

        this.sCid = sCid;
    }
    public Date getSCt(){

        return this.sCt;
    }
    public void setSCt(Date sCt){

        this.sCt = sCt;
    }
    public Long getSUid(){

        return this.sUid;
    }
    public void setSUid(Long sUid){

        this.sUid = sUid;
    }
    public Date getSUt(){

        return this.sUt;
    }
    public void setSUt(Date sUt){

        this.sUt = sUt;
    }
}
