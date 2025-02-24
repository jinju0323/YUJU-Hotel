package kr.project.yuju.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;

/**
 * ✅ JWT 토큰을 생성하고 검증하는 유틸 클래스
 * - 로그인 시 JWT를 생성하여 클라이언트에게 전달
 * - API 요청 시, 클라이언트가 보낸 JWT를 검증하여 유효한지 확인
 */
@Component
public class JwtUtil {
    // ✅ 256-bit 이상의 강력한 암호화 키 사용 (토큰 서명에 사용됨)
    private static final String SECRET_KEY = "your-256-bit-secret-key-your-256-bit-secret-key";

    // ✅ JWT 만료 시간 (1시간)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // ✅ 암호화 키 생성 (HMAC SHA-256 알고리즘을 사용)
    // ✅ 이 키를 사용하여 JWT를 서명하고 검증함 (모든 메서드에서 공통으로 사용)
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * ✅ JWT 토큰 생성 (로그인 성공 시 실행)
     * - 클라이언트가 로그인하면 JWT를 발급하여 반환
     * - JWT에는 `userId` 정보와 만료 시간이 포함됨
     *
     * @param userId - DB에 저장된 사용자 ID (JWT의 subject 값으로 사용됨)
     * @return JWT 토큰 문자열
     */
    public String generateToken(String userId) {
        return Jwts.builder()
            .header() // ✅ JWT 헤더 설정
                .add("alg", "HS256") // ✅ 서명 알고리즘을 명시적으로 지정 (HMAC SHA-256)
            .and()
            .claims() // ✅ JWT에 저장할 클레임(Claim) 설정
                .add("sub", userId)  // ✅ "sub" (subject) 필드에 사용자 ID 저장 (JWT 표준 클레임)
                .add(Claims.ISSUED_AT, new Date())  // ✅ 토큰 발급 시간 (iat)
                .add(Claims.EXPIRATION, new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // ✅ 만료 시간 (exp)
            .and()
            .signWith(key) // ✅ HMAC SHA-256 알고리즘을 사용하여 토큰 서명
            .compact(); // ✅ 최종적으로 JWT 문자열 생성 및 반환
    }

    /**
     * ✅ JWT 토큰 검증 (API 요청 시 실행)
     * - 클라이언트가 보낸 JWT가 유효한지 확인하고, 사용자 ID를 반환
     * - 서명이 유효하지 않거나 만료된 경우 `null` 반환
     *
     * @param token - 클라이언트가 요청 헤더에 포함하여 보낸 JWT
     * @return 유효한 경우 userId 반환, 유효하지 않으면 null 반환
     */
    public String validateToken(String token) {
        try {
            return Jwts.parser()  // ✅ JWT 파서 객체 생성
                    .verifyWith(key) // ✅ HMAC SHA-256 키를 사용하여 서명 검증
                    .build()
                    .parseSignedClaims(token) // ✅ 토큰을 파싱하고, 서명이 유효한 경우 Claims(페이로드) 반환
                    .getPayload()
                    .getSubject(); // ✅ "sub" 필드에 저장된 사용자 ID를 반환
        } catch (JwtException e) {
            return null;  // ⛔ 유효하지 않은 토큰이면 null 반환 (유효하지 않은 서명, 만료 등)
        }
    }
}
