package com.ck.orangeblogdao.po;

import java.util.Date;

public class FndUploadPo{
	/*  */
    private Long id;

	/*  */
    private String refTable;

	/*  */
    private Long refId;

	/*  */
    private String fileName;

	/*  */
    private String fileType;

	/*  */
    private String fileSize;

	/*  */
    private String filePath;

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
    public String getRefTable(){

        return this.refTable;
    }
    public void setRefTable(String refTable){

        this.refTable = refTable;
    }
    public Long getRefId(){

        return this.refId;
    }
    public void setRefId(Long refId){

        this.refId = refId;
    }
    public String getFileName(){

        return this.fileName;
    }
    public void setFileName(String fileName){

        this.fileName = fileName;
    }
    public String getFileType(){

        return this.fileType;
    }
    public void setFileType(String fileType){

        this.fileType = fileType;
    }
    public String getFileSize(){

        return this.fileSize;
    }
    public void setFileSize(String fileSize){

        this.fileSize = fileSize;
    }
    public String getFilePath(){

        return this.filePath;
    }
    public void setFilePath(String filePath){

        this.filePath = filePath;
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
