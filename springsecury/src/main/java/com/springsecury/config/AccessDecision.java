package com.springsecury.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class AccessDecision implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //按照系统自定义结构返回授权失败
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(4);
        stringObjectHashMap.put("code", 403);
        stringObjectHashMap.put("msg", "权限不足");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(stringObjectHashMap);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(s);
        writer.flush();
        writer.close();
    }
}