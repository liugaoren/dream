package com.springsecury.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @Author 杨不易呀
 * 自定义登录成功的处理器
 * 返回json
 */
@Configuration
public class AuthenticateSuccess implements AuthenticationSuccessHandler {


    /**
     * 登陆成功后执行的处理器
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功了");
        // 把json串写出去
        response.setContentType("application/json;charset=utf-8");
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("code", 200);
        map.put("msg", "登录成功");
        // 把用户信息返回给前端 让前端可以保存起来
        map.put("data", authentication);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        // 写出去
        PrintWriter writer = response.getWriter();
        writer.write(s);
        // 刷新流 关闭流
        writer.flush();
        writer.close();
    }
}


