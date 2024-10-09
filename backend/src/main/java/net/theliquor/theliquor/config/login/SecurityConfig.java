package net.theliquor.theliquor.config.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${ADMIN_MAGIC_WORD}")
    private String magic_word;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/" + magic_word + "/login")
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler()))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler()))
                .csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, accessDeniedException) -> {
            // Response 객체 생성
            ResponseDTO result = new ResponseDTO();
            result.setResult(Responses.Result.SUCCESS.ordinal());

            // 응답 설정
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");

            // Response 객체를 JSON으로 변환
            String jsonResponse = objectMapper.writeValueAsString(result);

            // JSON 응답 전송
            PrintWriter writer = response.getWriter();
            writer.write(jsonResponse);
            writer.flush();
        };
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, accessDeniedException) -> {
            // Response 객체 생성
            ResponseDTO result = new ResponseDTO();
            List<String> errors = new ArrayList<>();
            errors.add("Login Fail");
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);

            // 응답 설정
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            // Response 객체를 JSON으로 변환
            String jsonResponse = objectMapper.writeValueAsString(result);

            // JSON 응답 전송
            PrintWriter writer = response.getWriter();
            writer.write(jsonResponse);
            writer.flush();
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, accessDeniedException) -> {
            // Response 객체 생성
            ResponseDTO result = new ResponseDTO();
            result.setResult(Responses.Result.SUCCESS.ordinal());

            // 응답 설정
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");

            // Response 객체를 JSON으로 변환
            String jsonResponse = objectMapper.writeValueAsString(result);

            // JSON 응답 전송
            PrintWriter writer = response.getWriter();
            writer.write(jsonResponse);
            writer.flush();
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            // Response 객체 생성
            ResponseDTO result = new ResponseDTO();
            List<String> errors = new ArrayList<>();
            errors.add("Unauthorized");
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);

            // 응답 설정
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            // Response 객체를 JSON으로 변환
            String jsonResponse = objectMapper.writeValueAsString(result);

            // JSON 응답 전송
            PrintWriter writer = response.getWriter();
            writer.write(jsonResponse);
            writer.flush();
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // Response 객체 생성
            ResponseDTO result = new ResponseDTO();
            List<String> errors = new ArrayList<>();
            errors.add("Forbidden");
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);

            // 응답 설정
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");

            // Response 객체를 JSON으로 변환
            String jsonResponse = objectMapper.writeValueAsString(result);

            // JSON 응답 전송
            PrintWriter writer = response.getWriter();
            writer.write(jsonResponse);
            writer.flush();
        };
    }
}
