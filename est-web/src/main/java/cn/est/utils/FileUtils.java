package cn.est.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Description 文件处理工具类
 * @Date 2019-09-04 19:46
 * @Author Liujx
 * Version 1.0
 **/
public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * MultipartFile 转File
     * @param file
     * @return
     */
    public static File MultipartFileToFile(MultipartFile file){
        File resultFile = null;
        InputStream ins = null;
        OutputStream os = null;
        try{
            if (!file.equals("") && file.getSize() > 0) {

                ins = file.getInputStream();
                resultFile = new File(file.getOriginalFilename());
                os = new FileOutputStream(resultFile);
                int bytesRead = 0;
                byte[] buffer = new byte[2048];
                while ((bytesRead = ins.read(buffer, 0, 2048)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        }catch (IOException e){
            log.error("MultipartFile转File失败");
        }finally {
            try {
                if(os != null){
                    os.close();
                }
            } catch (IOException e) {
                log.error("MultipartFileToFile关闭输出流失败，{}", e);
            }
            try {
                if(ins != null){
                    ins.close();
                }
            } catch (IOException e) {
                log.error("MultipartFileToFile关闭输入流失败，{}", e);
            }
        }
        return resultFile;
    }


    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[2048];
            while ((bytesRead = ins.read(buffer, 0, 2048)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            log.error("MultipartFile转File失败");
        }
    }
}
