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
        input.setAdmin("N");
        
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
        input.setAdmin("N");
        
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
}
