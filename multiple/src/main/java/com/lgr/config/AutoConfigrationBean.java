package com.lgr.config;

import com.lgr.domain.Student;
import com.lgr.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfigrationBean {


    @Bean
    public Student getStudent(User user){
        System.out.println(user.getName());
        return new Student();
    }

    @Bean
    public User getUser(){
        return new User("1234");
    }
}
