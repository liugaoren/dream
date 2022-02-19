package com.fastdfs.controller;

import com.fastdfs.util.FastDFSClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @RequestMapping("/upload")
    public String uploadFile(MultipartFile file){
        String s = FastDFSClient.uploadFile(file);
        return FastDFSClient.getResAccessUrl(s);
    }

    @RequestMapping("/upload1")
    public String uploadFile1(MultipartFile file){
        String s = FastDFSClient.uploadImageAndCrtThumbImage(file);
        return FastDFSClient.getResAccessUrl(s);
    }

    @DeleteMapping("/delete")
    public String uploadFile1(String path){
        FastDFSClient.deleteFile(path);
        return "成功";
    }
}
