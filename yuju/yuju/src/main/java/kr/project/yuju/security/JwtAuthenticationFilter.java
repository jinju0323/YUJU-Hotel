package kr.project.yuju.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * ✅ JWT 인증 필터
 * - Spring Security의 OncePerRequestFilter를 상속받아 요청마다 한 번씩 실행됨
 * - JWT 토큰을 검사하여 인증을 수행하는 역할
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    /**
     * ✅ JwtUtil 주입
     * - JWT 토큰을 검증하고 userId를 추출하는 역할을 하는 JwtUtil을 주입받음
     * @param jwtUtil - JWT 관련 유틸리티 클래스
     */
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * ✅ 요청마다 실행되는 필터
     * - JWT 토큰을 검사하고, 인증 정보가 유효하면 SecurityContext에 저장함
     * - 만약 토큰이 없거나 유효하지 않으면 필터를 그냥 통과시킴
     * 
     * @param request - HTTP 요청 객체
     * @param response - HTTP 응답 객체
     * @param filterChain - 필터 체인 객체
     */
    @Override
    protected void doFilterInternal(
        @SuppressWarnings("null") @NonNull HttpServletRequest request,
        @SuppressWarnings("null") @NonNull HttpServletResponse response,
        @SuppressWarnings("null") @NonNull FilterChain filterChain) throws ServletException, IOException {

        // ✅ [1] 요청 헤더에서 JWT 토큰 추출
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // ✅ [2] "Bearer " 접두사가 없거나, 헤더가 없으면 다음 필터로 진행
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.debug("🔹 JWT가 없는 요청 - 필터 통과");
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ [3] "Bearer " 이후의 토큰 값만 추출
        String token = authorizationHeader.substring(7);

        try {
            // ✅ [4] JWT 검증 및 userId 추출
            String userId = jwtUtil.validateToken(token);

            if (userId != null) {
                // ✅ [5] Spring Security 인증 객체 생성
                UserDetails userDetails = User.withUsername(userId) // 사용자 ID
                        .password("") // 비밀번호는 필요하지 않음
                        .authorities("USER") // 권한 부여 (기본적으로 "USER" 역할)
                        .build();

                // ✅ [6] Spring Security 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // ✅ [7] SecurityContext에 인증 객체 저장 (로그인 처리 완료)
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("✅ JWT 인증 성공 - userId: {}", userId);
            }
        } catch (JwtException e) {
            // ⛔ 유효하지 않은 토큰 (로그 남기기)
            log.error("⛔ JWT 검증 실패: {}", e.getMessage());
        }

        // ✅ [8] 다음 필터로 요청 전달 (JWT 검증이 실패해도 요청은 계속 진행됨)
        filterChain.doFilter(request, response);
    }
}
