package com.springsecury.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/get")
    public String getHello() {
        return "你好老刘";
    }

    /**
     * 获取当前用户信息，直接在参数中注入 Principal 对象
     * 此对象是登录后自动写入 UsernamePasswordAuthenticationToken 类中
     *
     * @param principal
     * @return
     */
    @GetMapping("userInfo")
    public Principal getUserInfo(Principal principal) {
        return principal;
    }

    /**
     * SecurityContextHolder.getContext()获取安全上下文对象
     * 就是那个保存在 ThreadLocal 里面的安全上下文对象
     * 总是不为 null(如果不存在，则创建一个 authentication 属性为 null 的 empty 安全上下文对象)
     * 获取当前认证了的 principal(当事人),或者 request token (令牌)
     * 如果没有认证，会是 null,该例子是认证之后的情况
     */
    @GetMapping("userInfo2")
    public Object getUserInfo2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities();

    }
}