package com.fastdfs.controller;


import com.fastdfs.config.FastDFSClientWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lgr
 * @date 2021-12-22 20:02:00
 */

@RestController
@RequestMapping("/fastdfs")
public class FastDFSController {

    @Resource
    private FastDFSClientWrapper dfsClient;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String fileUrl = dfsClient.uploadFile(file);
        map.put("file_url", fileUrl);
        return ResponseEntity.ok(map);
    }

    /**
     * 带缩略图
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadAndCrtThumbImage(MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String fileUrl = dfsClient.uploadFileAndCrtThumbImage(file);
        map.put("file_url", fileUrl);
        return ResponseEntity.ok(map);
    }

    /**
     *
     * @param path group1/M00/00/00/CgAMDGHEKR2AfiUyAAIFEUSoKr4708.jpg
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Map<String, Object>> delete(String path) throws Exception {
        Map<String, Object> map = new HashMap<>();
       dfsClient.deleteFile(path);
        return ResponseEntity.ok(map);
    }
}