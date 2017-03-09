package com.jadyer.seed.scs.util;

import com.jadyer.seed.comm.util.LogUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 玄玉<https://jadyer.github.io/> on 2017/3/10 5:35.
 */
@Component
public class ScsHelper {
    @Value("${app.upload}")
    private String appUpload;

    public String getFilePath(boolean isWangEditor){
        return (appUpload.endsWith("/") ? appUpload : appUpload+"/") + (isWangEditor ? "wangEditor/" : "");
    }

    /**
     * 构建上传文件的存储路径
     */
    public String buildUploadFilePath(String originalFilename, boolean isWangEditor){
        String fileExtension = FilenameUtils.getExtension(originalFilename);
        String fileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomStringUtils.randomNumeric(6);
        String filePath = this.getFilePath(isWangEditor) + fileName + (isWangEditor?"w":"") + "." + fileExtension;
        LogUtil.getLogger().info("originalFilename=[{}]，filePath=[{}]", originalFilename, filePath);
        return filePath;
    }
}