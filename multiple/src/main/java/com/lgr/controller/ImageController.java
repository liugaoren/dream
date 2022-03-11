package com.lgr.controller;

import com.lgr.image.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class ImageController {


    //生成验证码
    @GetMapping("/imgCode")
    public void creaeCode(HttpServletRequest request, HttpServletResponse response) {
        //图形验证码工具类
        ImageCode imagecode = new ImageCode();
        BufferedImage img = imagecode.getImage();
        try {
            //从前端传codeId
            String codeId = request.getParameter("r");
            //验证码
            String code = imagecode.getValidateCode();
            System.out.println(codeId + "-------------------" + code);
            response.setContentType("image/jpeg");
            imagecode.saveImage(img, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
