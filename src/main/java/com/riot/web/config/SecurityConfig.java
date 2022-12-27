package com.riot.web.config;

import com.riot.web.entity.User;
import com.riot.web.service.CustomedOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig{


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomedOAuth2UserService customOAuth2UserService) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests()// URL별 권한 권리
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/admin").hasRole(User.Role.ADMIN.name())
                    .antMatchers("/oauth2/**", "/login/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint() // oauth2 로그인 성공 후 가져올 때의 설정들
                    // 소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
                    .userService(customOAuth2UserService); // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시

        return http.build();
    }
}
