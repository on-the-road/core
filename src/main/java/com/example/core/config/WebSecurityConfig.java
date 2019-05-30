package com.example.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 *  @EnableWebSecurity的作用：是一个组合注解
 *  @EnableWebSecurity完成的工作便是加载了WebSecurityConfiguration，
 *  AuthenticationConfiguration这两个核心配置类，
 *  也就此将spring security的职责划分为了配置安全信息，配置认证信息两部分
 *
 *  springSecurityFilterChain -- spring security核心过滤器,是整个认证的入口
 *  AuthenticationConfiguration的主要任务，便是负责生成全局的身份认证管理者AuthenticationManager
 *
 *  WebSecurityConfigurerAdapter
 *  适配器模式在spring中被广泛的使用，在配置中使用Adapter的好处便是，
 *  我们可以选择性的配置想要修改的那一部分配置，而不用覆盖其他不相关的配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 核心配置解读：
         * 1. 除了“/”,"/home"(首页),"/login"(登录),"/logout"(注销),之外，其他路径都需要认证
         * 2. 指定“/login”该路径为登录页面，当未认证的用户尝试访问任何受保护的资源时，都会跳
         *    转到“/login”
         * 3. 默认指定“/logout”为注销页面
         * 4. 防止CSRF攻击
         * 5. Session Fixation protection(防止别人篡改sessionId)
         * 6. Security Header(添加一系列和Header相关的控制)
         * 7. 为servlet API集成了新的方法
         */

        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and().
                formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中配置一个用户
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("USER");

    }
}
