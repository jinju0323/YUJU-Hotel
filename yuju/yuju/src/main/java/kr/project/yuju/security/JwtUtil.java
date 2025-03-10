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
                .add("userId", userId)  // ✅ 사용자 ID 저장 (JWT 표준 클레임 sub대신 userId로 명시시)
                .add(Claims.ISSUED_AT, new Date())  // ✅ 토큰 발급 시간 (iat)
                .add(Claims.EXPIRATION, new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // ✅ 만료 시간 (exp)
            .and()
            .signWith(key) // ✅ HMAC SHA-256 알고리즘을 사용하여 토큰 서명
            .compact(); // ✅ 최종적으로 JWT 문자열 생성 및 반환
    }

    /**
     * ✅ JWT 토큰 검증 및 사용자 ID 추출 메서드
     * 
     * - 클라이언트가 API 요청 시, Authorization 헤더에 포함한 JWT의 유효성을 검사한다.
     * - 서명이 유효하고 만료되지 않았다면, 토큰에서 "userId" 값을 추출하여 반환한다.
     * - 서명이 잘못되었거나 만료된 경우, `null`을 반환하여 인증 실패 처리한다.
     *
     * @param token 클라이언트가 요청 헤더에 포함하여 보낸 JWT
     * @return 유효한 경우 userId 반환, 유효하지 않으면 null 반환
     */
    public String validateToken(String token) {
        try {
            // ✅ JWT 파서를 생성하여 서명 검증을 진행
            Claims claims = Jwts.parser()  // JWT 파서를 생성
                    .verifyWith(key)  // SECRET_KEY를 이용하여 서명이 올바른지 검증
                    .build()  // 빌더 패턴을 사용하여 파서 객체 생성
                    .parseSignedClaims(token)  // 클라이언트가 보낸 JWT를 파싱하여 유효한 경우 Claims(페이로드) 반환
                    .getPayload();  // ✅ Claims에서 실제 페이로드(payload) 부분만 추출

            // ✅ "userId"라는 커스텀 클레임에서 값을 가져와 반환 (기본적으로 String 타입)
            return claims.get("userId", String.class); 

        } catch (ExpiredJwtException e) {
            // ⛔ 토큰이 만료된 경우 예외 발생 (exp 시간이 지남)
            System.out.println("JWT가 만료되었습니다: " + e.getMessage());
            return null;
        } catch (UnsupportedJwtException e) {
            // ⛔ 지원되지 않는 JWT 형식일 경우 예외 발생
            System.out.println("지원되지 않는 JWT 형식입니다: " + e.getMessage());
            return null;
        } catch (MalformedJwtException e) {
            // ⛔ JWT가 올바르게 구성되지 않은 경우 예외 발생 (구조 오류)
            System.out.println("JWT가 올바르지 않습니다: " + e.getMessage());
            return null;
        } catch (@SuppressWarnings("deprecation") SignatureException e) {
            // ⛔ 서명이 잘못되었거나 변조된 경우 예외 발생
            System.out.println("JWT 서명이 올바르지 않습니다: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            // ⛔ JWT가 비어있거나 유효하지 않은 경우 예외 발생
            System.out.println("JWT가 비어있거나 잘못된 값입니다: " + e.getMessage());
            return null;
        }
    }

    
}
