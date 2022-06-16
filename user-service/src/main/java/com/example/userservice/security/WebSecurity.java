package com.example.userservice.security;

import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private Environment env;

    @Autowired
    public WebSecurity(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    /* 권한 관련 메소드 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.authorizeHttpRequests().antMatchers("/users/**").permitAll(); // 인증 없이 사용 가능
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress("10.131.153.2") // 해당 IP 로부터 요청이 왔다면 접근을 허용
                .and()
                .addFilter(getAuthenticationFilter()); // 이 필터를 통과시킨 데이터에 한해서만 권한을 부여하고 작업 진행시킴

        http.headers().frameOptions().disable(); // 이게 없으면 h2-console 접근 안됨
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(); // 인증처리
        authenticationFilter.setAuthenticationManager(authenticationManager()); // 인증처리를 하기 위한 시큐리티 매니저

        return authenticationFilter;
    }

    /* 인증 관련 메소드 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
