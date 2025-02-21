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
}