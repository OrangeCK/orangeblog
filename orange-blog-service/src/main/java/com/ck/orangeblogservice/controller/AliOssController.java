package com.ck.orangeblogservice.controller;

import com.ck.orangeblogcommon.constant.LmEnum;
import com.ck.orangeblogcommon.utils.AliyunOssClientUtil;
import com.ck.orangeblogdao.pojo.ResultData;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2019/5/26 0026
 * Description : 阿里oos上传
 */
@Controller
@RequestMapping("/orangeblog/aliOss")
@Api(description = "阿里oss上传")
public class AliOssController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 图片上传接口
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/uploadToOss", method = RequestMethod.POST)
    @ResponseBody
    public ResultData uploadOss(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request){
        if(multipartFile.isEmpty()){
            return ResultData.error("上传文件NULL");
        }
        String contentType = multipartFile.getContentType();
        if(!contentType.contains("")){
            return ResultData.error("上传文件格式错误");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        if(StringUtils.isBlank(originalFilename)){
            return ResultData.error("上传文件名字NULL");
        }
        // 后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 获取时间戳
        long currTimestamp = System.currentTimeMillis();
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + currTimestamp + suffix;
        logger.info("上传图片：name={},type={}",fileName, contentType);
        try {

            AliyunOssClientUtil.invokeOssUpload(multipartFile, fileName);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error("文件上传失败" + sw.toString());
            return ResultData.error("上传失败");
        }
        return ResultData.ok("http://" + LmEnum.BACKET_NAME.getName() + "." + LmEnum.ENDPOINT.getName() + "/" + LmEnum.FOLDER.getName() + fileName);
    }

    @RequestMapping(value = "/deleteFromOss", method = RequestMethod.POST)
    @ResponseBody
    public ResultData deleteFromOss(@RequestParam String key){
        try {
            AliyunOssClientUtil.invokeOssDelete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.ok();
    }

}