package com.example.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 권한 관련 메소드 */
        http.csrf().disable();
        //http.authorizeHttpRequests().antMatchers("/users/**").permitAll(); // 인증 없이 사용 가능
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress("10.131.153.2") // 해당 IP 로부터 요청이 왔다면 접근을 허용
                .and()
                .addFilter(getAuthenticationFilter()); // 필터 적용

        http.headers().frameOptions().disable(); // 이게 없으면 h2-console 접근 안됨
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
