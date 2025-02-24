package kr.project.yuju.services.impl;

import kr.project.yuju.mappers.MemberMapper;
import kr.project.yuju.models.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ✅ 사용자 인증 정보를 제공하는 서비스 구현체
 * - 로그인 시 userId를 기반으로 DB에서 사용자 정보를 조회함
 * - 조회된 정보를 Spring Security의 `UserDetails` 객체로 변환하여 반환
 */
@Service // ✅ Spring Bean으로 등록 (이거 없으면 SecurityConfig에서 못 찾음!)
public class UserDetailsServiceImpl implements UserDetailsService { // ✅ Spring Security의 UserDetailsService 구현

    private final MemberMapper memberMapper; // ✅ 회원 정보를 조회할 MyBatis Mapper

    /**
     * ✅ 생성자 주입 (Spring이 `MemberMapper`를 자동으로 주입함)
     */
    public UserDetailsServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /**
     * ✅ 로그인 시 사용자의 인증 정보를 조회하는 메서드
     * @param userId - 사용자가 입력한 아이디
     * @return UserDetails - Spring Security에서 사용자의 인증 정보를 담는 객체
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // ✅ [1] DB에서 userId로 사용자 조회
        Member input = new Member();
        input.setUserId(userId);
        Member member = memberMapper.selectItem(input);

        // ⛔ [2] 사용자가 존재하지 않으면 예외 발생
        if (member == null) {
            throw new UsernameNotFoundException("❌ 사용자를 찾을 수 없습니다: " + userId);
        }

        // ✅ [3] 사용자를 Spring Security의 UserDetails 객체로 변환하여 반환
        return User.builder()
                .username(member.getUserId())  // ✅ 사용자 ID 설정
                .password(member.getUserPw())  // ✅ 해시된 비밀번호 설정 (로그인 시 검증)
                .roles("USER")  // ✅ 기본 권한 설정
                .build();
    }
}
