package cn.est.controller;

import cn.est.utils.ResultUtils;
import cn.est.utils.FileUtils;
import cn.est.utils.oss.OssConfig;
import cn.est.utils.oss.OssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Description 测试代码控制
 * @Date 2019-09-04 17:32
 * @Author Liujx
 * Version 1.0
 **/
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private OssUtils ossUtils;

    // 测试文件上传
    @PostMapping("/uploadFile")
    public Object uploadFile(MultipartFile file) throws IOException {
        log.info("上传文件");
        log.info("size:{}", file.getSize());
        log.info("type:{}", file.getContentType());
        String fileName = file.getOriginalFilename();
        String filePath = "est/brand/huawei2/";
        File fileInfo = FileUtils.MultipartFileToFile(file);
        ossUtils.uploadByStream(fileInfo, filePath + fileName);
        return ResultUtils.returnDataSuccess(filePath + fileName);
    }
    // 测试文件删除
    @PostMapping("/deleteFile")
    public Object deleteFile(String filePath, String fileName) throws IOException {
        log.info("删除文件:{}", filePath + fileName);
        ossUtils.deleteFile(filePath, fileName);
        return ResultUtils.returnSuccess();
    }


    public static void inputStreamToFile(InputStream ins, File file) {

        /*if (file.equals("") || file.getSize() <= 0) {
            file = null;
        }else {
            InputStream ins = null;
            ins = file.getInputStream();
            fileInfo = new File(file.getOriginalFilename());
            inputStreamToFile(ins, fileInfo);
            ins.close();
        }*/
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

