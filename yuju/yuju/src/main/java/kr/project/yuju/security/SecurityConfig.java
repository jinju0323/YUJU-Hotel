package kr.project.yuju.security;

import kr.project.yuju.services.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ✅ Spring Security 설정 클래스 (JWT 기반 인증 적용)
 */
@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * ✅ 생성자 주입 방식으로 JwtUtil 및 UserDetailsServiceImpl 주입
     */
    public SecurityConfig(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * ✅ Spring Security 필터 체인 설정
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // ✅ CSRF 보호 비활성화 (JWT 사용 시 필요 없음)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ 세션 사용 안 함 (Stateless 방식)
            .authorizeHttpRequests(auth -> auth
                // ✅ 로그인 & 회원가입 API는 인증 없이 허용
                .requestMatchers("/api/account/login", "/api/account/join").permitAll()
                
                // ✅ 정적 리소스 및 기본 페이지 접근 허용
                .requestMatchers("/", "/index.html", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

                // ✅ 모든 페이지 접속 가능하도록 변경
                .requestMatchers("/**").permitAll()

                // ✅ API 요청은 JWT 인증 필요
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // ✅ JWT 필터 추가
            .build();
    }

    /**
     * ✅ 비밀번호 암호화 설정
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ✅ AuthenticationManager 설정 (로그인 시 사용)
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // ✅ UserDetailsServiceImpl 사용
        provider.setPasswordEncoder(passwordEncoder()); // ✅ 비밀번호 검증 적용
        return new ProviderManager(provider);
    }

    /**
     * ✅ JWT 인증 필터 Bean 등록
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil);
    }
}
