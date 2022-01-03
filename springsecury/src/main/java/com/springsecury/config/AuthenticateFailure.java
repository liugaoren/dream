package com.springsecury.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class AuthenticateFailure implements AuthenticationFailureHandler {


    /**
     * 登陆成功后执行的处理器
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println(exception);
        // 有很多登录失败的异常
        HashMap map = new HashMap<>(4);
        map.put("code", 401);
        // instanceof 判断左右是否是右边的 一个实例  这里的exception已经是一个具体的错误了
        if (exception instanceof LockedException) {
            map.put("msg", "账户被锁定，登陆失败！");
        } else if (exception instanceof BadCredentialsException) {
            map.put("msg", "账户或者密码错误，登陆失败！");
        } else if (exception instanceof DisabledException) {
            map.put("msg", "账户被禁用，登陆失败！");
        } else if (exception instanceof AccountExpiredException) {
            map.put("msg", "账户已过期，登陆失败！");
        } else if (exception instanceof CredentialsExpiredException) {
            map.put("msg", "密码已过期，登陆失败！");
        } else {
            map.put("msg", "登陆失败！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.flush();
        writer.close();
    }
}


