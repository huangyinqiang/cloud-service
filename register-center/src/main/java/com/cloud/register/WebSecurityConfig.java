package com.cloud.register;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author HuangYinQiang
 * @version 1.0
 * @Package com.cloud.register
 * @Description: TODO
 * @date 2019/2/26 0:37
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //关闭打开的csrf保护
        http.csrf().disable();
        //简单指定了如何保护http请求，以及客户端认证用户的方案．
        http.authorizeRequests()
                //要求所有进入应用的HTTP请求都要进行认证
                .anyRequest().authenticated()
                //的认证
                .and().httpBasic();
    }
}
