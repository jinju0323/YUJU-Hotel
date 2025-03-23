package kr.project.yuju.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.MemberMapper;
import kr.project.yuju.models.Member;
import kr.project.yuju.security.JwtUtil;
import kr.project.yuju.services.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    // ✅ 비밀번호 암호화 적용
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ JWT 토큰 생성 및 검증 유틸 클래스
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 회원 데이터를 저장한다.
     */
    @Override
    public Member addItem(Member input) throws Exception {
        int rows = 0;

        try {
            // ✅ 비밀번호 암호화 후 저장
            input.setUserPw(passwordEncoder.encode(input.getUserPw()));
            
            rows = memberMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return memberMapper.selectItem(input);
    }

    /**
     * 회원 데이터를 수정한다.
     */
    @Override
    public Member editItem(Member input) throws Exception {
        int rows = 0;

        try {
            rows = memberMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return memberMapper.selectItem(input);
    }

    /**
     * 회원 데이터를 삭제한다.
     */
    @Override
    public int deleteItem(Member input) throws Exception {
        int rows = 0;

        try {
            rows = memberMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    /**
     * 회원 데이터를 단일 조회한다.
     */
    @Override
    public Member getItem(Member input) throws Exception {
        Member output = null;

        try {
            output = memberMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 회원 데이터를 목록 조회한다.
     */
    @Override
    public List<Member> getList(Member input) throws Exception {
        List<Member> output = null;

        try {
            output = memberMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 회원 데이터를 수를 조회한다.
     */
    @Override
    public int getCount(Member input) throws Exception {
        int output = 0;

        try {
            output = memberMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 아이디(이메일) 중복검사를 수행한다.
     */
    @Override
    public void idCheck(String userId) throws Exception {
        Member input = new Member();
        input.setUserId(userId);

        int output = 0;

        try {
            output = memberMapper.selectCount(input);

            if (output > 0) {
                throw new Exception("사용할 수 없는 아이디(이메일) 입니다.");
            }
        } catch (Exception e) {
            log.error("아이디(이메일) 중복검사에 실패했습니다.", e);
            throw e;
        }
    }

    /**
     * ✅ 로그인 처리 (JWT 발급)
     * - userId로 DB 조회
     * - 입력된 비밀번호와 DB에 저장된 해시된 비밀번호를 비교
     * - 로그인 성공 시 JWT 토큰과 userId를 함께 반환
     */
    @Override
    public Map<String, Object> login(String userId, String userPw) {
        Member input = new Member();
        input.setUserId(userId);

        // ✅ [1] DB에서 userId 조회
        Member member = memberMapper.selectItem(input);
        if (member == null) {
            log.warn("❌ 존재하지 않는 계정: {}", userId);
            throw new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // ✅ [2] 비밀번호 검증 (BCrypt 해싱 비교)
        boolean isPasswordMatch = passwordEncoder.matches(userPw, member.getUserPw());
        log.debug("🔐 비밀번호 검증 결과: {}", isPasswordMatch ? "✅ 일치" : "❌ 불일치");

        if (!isPasswordMatch) {
            log.warn("❌ 비밀번호 불일치 - userId: {}", userId);
            throw new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // ✅ [3] 로그인 성공 → JWT 발급
        String token = jwtUtil.generateToken(userId);

        // ✅ [4] 로그인 결과 반환 (토큰 + userId)
        return Map.of(
            "token", token,
            "userId", userId
        );
    }

    /**
     * ✅ 회원 탈퇴 처리
     * - 비밀번호 확인 후 탈퇴 상태 업데이트
     */
    @Override
    public boolean deleteMember(String userId, String currentPassword, String confirmPassword) throws Exception {
        try {
            // 비밀번호와 비밀번호 확인 일치 여부 체크
            if (!currentPassword.equals(confirmPassword)) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }

            // 비밀번호가 일치하는지 암호화된 비밀번호 비교
            Member member = new Member();
            member.setUserId(userId);

            // DB에서 사용자 비밀번호 가져오기
            Member dbMember = memberMapper.selectItem(member);
            if (dbMember == null) {
                throw new Exception("회원 정보가 존재하지 않습니다.");
            }

            // 비밀번호 검증 (암호화된 비밀번호 비교)
            if (!passwordEncoder.matches(currentPassword, dbMember.getUserPw())) {
                throw new Exception("비밀번호가 유효하지 않습니다.");
            }

            // 탈퇴 처리 (is_out = 'Y')
            int rowsUpdated = memberMapper.updateMemberToOut(userId);

            if (rowsUpdated > 0) {
                log.info("회원 탈퇴 처리 완료: {}", userId);
                return true;
            } else {
                throw new Exception("탈퇴 처리에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("회원 탈퇴 처리 중 오류 발생: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 탈퇴 상태인 회원 목록 조회 후 DB에서 삭제
     */
    @Override
    public List<Member> deleteOutMembers() throws Exception {
        List<Member> outMembers = null;

        try {
            // 1. 삭제 대상 조회
            outMembers = memberMapper.selectOutMembers();

            if (outMembers == null || outMembers.isEmpty()) {
                return List.of(); // 빈 리스트 반환
            }

            // 2. DB에서 삭제
            int rows = memberMapper.deleteOutMembers();

            if (rows == 0) {
                throw new Exception("탈퇴 회원 삭제 실패");
            }

        } catch (Exception e) {
            log.error("❌ 실제 탈퇴 회원 삭제 처리 중 예외 발생", e);
            throw e;
        }

        return outMembers;
    }

}