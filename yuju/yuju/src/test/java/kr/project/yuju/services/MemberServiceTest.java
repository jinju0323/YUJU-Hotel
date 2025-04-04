package kr.project.yuju.services;

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
public class MemberServiceTest {
    
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 추가 서비스 테스트")
    void addItemTest() throws Exception {
        Member input = new Member();
        input.setUserName("박지민");
        input.setUserId("jimin.park@test.com");
        input.setUserPw("testPass!123");
        input.setIsAdmin("N");
        
        Member output = memberService.addItem(input);
        assertNotNull(output);
        log.debug("추가된 회원: {}", output);
    }

    @Test
    @DisplayName("회원 수정 서비스 테스트")
    void editItemTest() throws Exception {
        Member input = new Member();
        input.setMemberId(1);
        input.setUserName("박지민 (수정)");
        input.setUserPw("updatedPass!456");
        input.setIsAdmin("N");
        
        Member output = memberService.editItem(input);
        assertNotNull(output);
        assertEquals("박지민 (수정)", output.getUserName());
        log.debug("수정된 회원: {}", output);
    }

    @Test
    @DisplayName("회원 삭제 서비스 테스트")
    void deleteItemTest() throws Exception {
        Member input = new Member();
        input.setMemberId(1);
        
        int output = memberService.deleteItem(input);
        assertEquals(1, output);
        log.debug("삭제된 회원 ID: {}", input.getMemberId());
    }

    @Test
    @DisplayName("회원 단일 조회 서비스 테스트")
    void getItemTest() throws Exception {
        Member input = new Member();
        input.setMemberId(3);
        
        Member output = memberService.getItem(input);
        assertNotNull(output);
        log.debug("조회된 회원: {}", output);
    }

    @Test
    @DisplayName("회원 목록 조회 서비스 테스트")
    void getListTest() throws Exception {
        List<Member> output = memberService.getList(null);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        log.debug("회원 목록: {}", output);
    }
    
    @Test
    @DisplayName("회원 수 조회 서비스 테스트")
    void getCountTest() throws Exception {
        int output = memberService.getCount(null);
        assertTrue(output >= 0);
        log.debug("회원 수: {}", output);
    }

    @Test
    @DisplayName("회원 탈퇴 서비스 테스트")
    void deleteMemberTest() throws Exception {
        // 테스트용 데이터
        String userId = "testuser@test.com";
        String currentPassword = "testPass!123";
        String confirmPassword = "testPass!123";

        try {
            // 회원 탈퇴 처리
            boolean result = memberService.deleteMember(userId, currentPassword, confirmPassword);

            // 결과 검증
            assertTrue(result, "회원 탈퇴가 정상적으로 처리되지 않았습니다.");
            log.debug("회원 탈퇴 처리 성공: {}", userId);
        } catch (Exception e) {
            log.error("회원 탈퇴 테스트 중 오류 발생: {}", e.getMessage());
            fail("회원 탈퇴 테스트 실패: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("탈퇴 회원 삭제 서비스 테스트")
    void deleteOutMembersTest() throws Exception {
        try {
            // 탈퇴 회원 삭제 처리
            List<Member> deletedMembers = memberService.deleteOutMembers();

            // 결과 검증
            assertNotNull(deletedMembers, "삭제된 회원 목록이 null입니다.");
            log.debug("삭제된 탈퇴 회원 목록: {}", deletedMembers);

            if (!deletedMembers.isEmpty()) {
                log.debug("총 {}명의 탈퇴 회원이 삭제되었습니다.", deletedMembers.size());
            } else {
                log.debug("삭제할 탈퇴 회원이 없습니다.");
            }
        } catch (Exception e) {
            log.error("탈퇴 회원 삭제 테스트 중 오류 발생: {}", e.getMessage());
            fail("탈퇴 회원 삭제 테스트 실패: " + e.getMessage());
        }
    }


}