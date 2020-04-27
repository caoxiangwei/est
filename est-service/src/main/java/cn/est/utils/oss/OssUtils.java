package cn.est.utils.oss;

import cn.est.utils.UUIDUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;

/**
 * @Description 阿里云OSS对象存储的工具类
 * @Date 2019-08-16 10:32
 * @Author Liujx
 * Version 1.0
 **/
@Component
public class OssUtils {

    private final Logger log = LoggerFactory.getLogger(OssUtils.class);

    @Autowired
    private OssConfig ossConfig;

    /**
     * 获取阿里云OSS客户端对象
     * */
    public final OSS getOSSClient() {
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndPoint(), ossConfig.getAccessKeyId()
                , ossConfig.getAccessKeySecret());
        return ossClient;
    }


    /**
     * 文件上传
     * 根据文件全路径名称使用输入流的方式上传文件到oss
     * @param file
     * @param fileName est/brand/huawei/huawei.png
     * @return
     */
    public String uploadByStream(File file, String fileName) {
        String resultStr = null;
        try {
            InputStream is = new FileInputStream(file);
            resultStr = uploadByStream(is, fileName);
        }catch (Exception e){
            log.error("上传阿里云OSS服务器异常:{}", e.getMessage());
        }
        return resultStr;
    }


    /**
     * 文件上传
     * @param is
     * @param fileName est/brand/huawei/huawei.png
     * @return
     */
    public String uploadByStream(InputStream is, String fileName) {
        String resultStr = null;
        OSS ossClient = null;
        try {
            ossClient = getOSSClient();
            PutObjectResult putObjectResult = ossClient.putObject(ossConfig.getBucketName(), fileName, is);
            resultStr = putObjectResult.getETag();
        }catch (Exception e){
            log.error("输入流方式上传阿里云OSS服务器异常:{}", e.getMessage());
        }finally {
            ossClient.shutdown();
        }
        return resultStr;
    }

    /**
     *  向阿里云的OSS存储中存储文件 old
     * @param file 附件
     * @param diskName fieldid
     * @return
     */
    public final String uploadObject2OSS(File file, String diskName, String fileName) {
        OSS client = getOSSClient();
        String resultStr = null;
        try {
            InputStream is = new FileInputStream(file);
            //String fileName = file.getName();
            Long fileSize = file.length();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件
            PutObjectResult putResult = client.putObject(ossConfig.getBucketName(), diskName + fileName, is, metadata);
            // 解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常.", e.getMessage());
        }
        return resultStr;
    }

    /**
     * 根据key获取OSS服务器上的文件输入流
     * @param diskName fieldid
     * @param fileName 文件名称
     * @return
     */
    public final InputStream getOSS2InputStream(String diskName, String fileName) {
        OSS client = getOSSClient();
        OSSObject ossObj = client.getObject(ossConfig.getBucketName(), diskName + "/" + fileName);
        return ossObj.getObjectContent();
    }

    /**
     *  根据key删除OSS服务器上的文件
     * @param diskName fieldid
     * @param fileName 文件名称
     */
    public void deleteFile(String diskName, String fileName) {
        deleteFile(diskName + fileName);
    }

    /**
     *  根据key删除OSS服务器上的文件
     * @param fileName 文件名称
     */
    public void deleteFile(String fileName) {
        OSS client = getOSSClient();
        client.deleteObject(ossConfig.getBucketName(), fileName);
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName
     *            文件名
     * @return 文件的contentType
     */
    public final String getContentType(String fileName) {
        File f = new File(fileName);
        return new MimetypesFileTypeMap().getContentType(f);
    }

    public void testUploadObject2OSS() {
        String flilePathName = "C:\\Users\\jinxin.liu\\Desktop\\工作\\二手兔\\img\\brand\\vivo.png";
        File file = new File(flilePathName);
        String diskName = UUIDUtils.getUUID32() + "/";
        String md5key = uploadObject2OSS(file, diskName,"vivo.png");
        log.info("上传后的文件MD5数字唯一签名:" + md5key);
    }

    public void testUploadByStream() {
        String flilePathName = "C:\\Users\\jinxin.liu\\Desktop\\工作\\二手兔\\img\\brand\\huawei.png";
        File file = new File(flilePathName);
        String fileName = UUIDUtils.getUUID32() + ".png";
        String filePath = "est/brand/huawei2/";
        String md5key = uploadByStream(file, filePath + fileName);
        log.info("上传后的文件MD5数字唯一签名:" + md5key);
    }

    public void testGetOSS2InputStream() { // 获取文件
        try {
//			http://project-fqh.oss-cn-shanghai.aliyuncs.com/80C6F7C37F663C89CC7440EA9166CE58/%E6%8A%A5%E8%A1%A8%E7%BB%9F%E8%AE%A1.xls
            BufferedInputStream bis = new BufferedInputStream(
                    getOSS2InputStream("80C6F7C37F663C89CC7440EA9166CE58", "1538029037739报表统计.xls"));
            String resfile = "C:/Users/xml/Desktop/zwyl/picture.xls";
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(resfile)));
            int itemp = 0;
            while ((itemp = bis.read()) != -1) {
                bos.write(itemp);
            }
			log.info("文件获取成功");
            bis.close();
            bos.close();
        } catch (Exception e) {
            log.error("从OSS获取文件失败:" + e.getMessage(), e);
        }
    }
    public void testDeleteFile() {
        deleteFile("3b966ddd2a2441889381c4bcf3618f64/", "image.png");
    }
}