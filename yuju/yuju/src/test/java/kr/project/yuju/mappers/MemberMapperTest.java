package kr.project.yuju.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.yuju.models.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberMapperTest {
    
    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("회원 추가 테스트")
    void insertMember() {
        Member input = new Member();
        input.setUserName("이수아");
        input.setUserId("sua.lee@test.com");
        input.setUserPw("pass123!");
        input.setIsAdmin("N");
        
        int output = memberMapper.insert(input);
        assertTrue(output > 0);
        assertNotNull(input.getMemberId());
        
        log.debug("output: {}", output);
        log.debug("new id: {}", input.getMemberId());
    }

    @Test
    @DisplayName("회원 수정 테스트")
    void updateMember() {
        Member input = new Member();
        input.setMemberId(4);
        input.setUserName("이수아 (수정)");
        input.setUserPw("newPass123!");
        input.setIsAdmin("N");
        
        int output = memberMapper.update(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    void deleteMember() {
        Member input = new Member();
        input.setMemberId(2);
        
        int output = memberMapper.delete(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("회원 단일 조회 테스트")
    void selectOneMember() {
        Member input = new Member();
        input.setMemberId(4);
        
        Member output = memberMapper.selectItem(input);
        assertNotNull(output);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("회원 목록 조회 테스트")
    void selectListMember() {
        List<Member> output = memberMapper.selectList(null);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        
        for (Member item : output) {
            log.debug("output: {}", item);
        }
    }
    
    @Test
    @DisplayName("회원 수 조회 테스트")
    void selectCountMember() {
        int output = memberMapper.selectCount(null);
        assertTrue(output >= 0);
        log.debug("회원 수: {}", output);
    }

    @Test
    @DisplayName("회원 탈퇴 상태 업데이트 테스트")
    void updateMemberToOutTest() {
        // 테스트용 데이터
        String userId = "testuser@test.com";

        // 탈퇴 상태 업데이트
        int rowsUpdated = memberMapper.updateMemberToOut(userId);

        // 결과 검증
        assertTrue(rowsUpdated > 0, "회원 탈퇴 상태 업데이트 실패");
        log.debug("탈퇴 상태로 업데이트된 회원 수: {}", rowsUpdated);
    }

    @Test
    @DisplayName("탈퇴 회원 목록 조회 테스트")
    void selectOutMembersTest() {
        // 탈퇴 회원 목록 조회
        List<Member> outMembers = memberMapper.selectOutMembers();

        // 결과 검증
        assertNotNull(outMembers, "탈퇴 회원 목록 조회 실패");
        log.debug("탈퇴 회원 목록: {}", outMembers);

        if (!outMembers.isEmpty()) {
            log.debug("총 {}명의 탈퇴 회원이 조회되었습니다.", outMembers.size());
        } else {
            log.debug("조회된 탈퇴 회원이 없습니다.");
        }
    }

    @Test
    @DisplayName("탈퇴 회원 삭제 테스트")
    void deleteOutMembersTest() {
        // 탈퇴 회원 삭제
        int rowsDeleted = memberMapper.deleteOutMembers();

        // 결과 검증
        assertTrue(rowsDeleted >= 0, "탈퇴 회원 삭제 실패");
        log.debug("삭제된 탈퇴 회원 수: {}", rowsDeleted);
    }
}
