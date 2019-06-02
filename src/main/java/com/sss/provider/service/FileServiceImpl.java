package com.sss.provider.service;

import com.sss.interfaces.service.FileService;
import org.aspectj.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

//使用前请务必把上传下载的路径修改为服务器的路径！！！
@Service("FileService")
@Transactional
public class FileServiceImpl implements FileService {
    // 1为有错误，0为正常返回
    public int FileUpload(MultipartFile file) {
        if (!file.isEmpty()){
            String storePath= "D:\\test";//存放我们上传的文件路径
            String fileName = file.getOriginalFilename();
            java.io.File filepath = new java.io.File(storePath, fileName);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
            }
            try {
                file.transferTo(new java.io.File(storePath+ java.io.File.separator+fileName));//把文件写入目标文件地址
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
            return 0;
        }
        return 1;
    }
    //使用最新的Firefox、Chrome、Opera、Safari则都可以正常下载文件名为中文的文件了
    //没考虑文件名不存在或错误
    public ResponseEntity<byte[]> FileDownload(String filename) {
        ResponseEntity<byte[]> res = null;
        String downloadFilePath = "D:\\test";
        File file = new File(downloadFilePath+File.separator+filename);
        HttpHeaders headers = new HttpHeaders();
        try {
            String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
            headers.setContentDispositionFormData("attachment" , downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            res = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file) , headers, HttpStatus.OK);
        }catch (IOException e){
            return res;
        }
        return res;
    }
}
