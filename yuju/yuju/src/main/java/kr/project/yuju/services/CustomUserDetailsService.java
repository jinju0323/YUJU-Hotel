package kr.project.yuju.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * ✅ 사용자 인증 정보를 제공하는 서비스 인터페이스
 */
public interface CustomUserDetailsService {
    /**
     * ✅ 사용자 정보를 로드하는 메서드
     * 
     * @param userId - 사용자가 입력한 아이디
     * @return UserDetails - Spring Security에서 사용자의 인증 정보를 담는 객체
     * @throws UsernameNotFoundException - 사용자를 찾지 못했을 경우 예외 발생
     */
    UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;
}
