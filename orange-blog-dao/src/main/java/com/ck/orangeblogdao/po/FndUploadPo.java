package com.ck.orangeblogdao.po;

import com.ck.orangeblogdao.pojo.BasePo;

import java.util.Date;

public class FndUploadPo extends BasePo {
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
    private String sCid;

	/*  */
    private Date sCt;

	/*  */
    private String sUid;

	/*  */
    private Date sUt;

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
}
