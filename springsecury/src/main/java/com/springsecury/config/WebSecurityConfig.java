package com.springsecury.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Author 杨不易呀
 * web 安全的配置类
 * <p>
 * WebSecurityConfigurerAdapter   web安全配置的适配器
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    /**
     * 将自定义的拒绝访问处理器注入进来
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 配置 http 请求验证等
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 给一个表单登陆 就是我们的登录页面,登录成功或者失败后走我们的 url
//        http.formLogin()
//                .successForwardUrl("/welcome") // 登录成功走的url
//                .failureForwardUrl("/fail") // 登录失败走的url
//                .permitAll();
        http.csrf().disable();
        // 自定义403请求返回json
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        // 这里使用了前后端分离的模式 实现我们的登录成功和失败返回json
        http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);


        // 匹配哪些 url，需要哪些权限才可以访问 当然我们也可以使用链式编程的方式
//        http.authorizeRequests()
//                .antMatchers("/query").hasAuthority("sys:query") // 表示这个用户有这个权限标识才能访问
//                .antMatchers("/add").hasAuthority("sys:add")
//                .antMatchers("/delete").hasAuthority("sys:delete")
//                .antMatchers("/update").hasAuthority("sys:update")
//                .antMatchers("/admin/**").hasRole("ADMIN") // 表示这个用户有这个角色才能访问
        ; // 其他所有的请求都需要登录才能进行
        // 所有的请求都需要认证才可以访问

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    /**
     * 配置认证(用户)管理 模拟内存用户数据
     * 重点说明：
     * 在开发中，我们一般只针对权限，很少去使用角色
     * 后面的讲解中我们以权限为主也就是 authorities 这里面的东西
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中创建了两个用户
        // 注意点： 我们添加了安全配置类，那么我们在 yml 里面的用户密码配置就失效了
        auth.inMemoryAuthentication()
                .withUser("yby") // 用户名
                .password(passwordEncoder().encode("yby")) // 密码
                .roles("ADMIN_yby") // 给了一个角色
                .authorities("sys:add", "sys:update", "sys:delete", "sys:select")
                // 注意点：给yby用户四个权限 如果权限和角色都给了 那么角色就失效了
                .and()
                .withUser("test")
                .password(passwordEncoder().encode("test"))
                .roles("TEST")
                .authorities("sys:select") // 加了一个权限
        ;
    }
    /**
     * 给容器中放一个加密器 springSecurity5.x之后推荐使用加密
     * 也可以不给加密
     * new NoOpPasswordEncoder()
     * 这个加密器对同一个值加密后 会得到不同的结果
     * 只要是用同一个加密器加密的 解密也是一样的
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
