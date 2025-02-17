package kr.project.yuju.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.yuju.models.Notice;
import kr.project.yuju.models.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class NoticeMapperTest {
    
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private MemberMapper memberMapper;

    private boolean isAdmin(int memberId) {
        Member member = new Member();
        member.setMemberId(memberId);
        Member output = memberMapper.selectItem(member);
        return output != null && "Y".equals(output.getAdmin());
    }

    @Test
    @DisplayName("공지사항 추가 테스트 (관리자만 가능)")
    void insertNotice() {
        int adminId = 1;
        assertTrue(isAdmin(adminId), "관리자가 아닙니다.");
        
        Notice input = new Notice();
        input.setMemberId(adminId);
        input.setTitle("테스트 공지사항");
        input.setContent("이것은 공지사항 내용입니다.");
        
        int output = noticeMapper.insert(input);
        assertTrue(output > 0);
        assertNotNull(input.getNoticeId());
        
        log.debug("output: {}", output);
        log.debug("new notice id: {}", input.getNoticeId());
    }

    @Test
    @DisplayName("공지사항 수정 테스트 (관리자만 가능)")
    void updateNotice() {
        int adminId = 1;
        assertTrue(isAdmin(adminId), "관리자가 아닙니다.");
        
        Notice input = new Notice();
        input.setNoticeId(1);
        input.setTitle("수정된 공지사항 제목");
        input.setContent("수정된 공지사항 내용입니다.");
        
        int output = noticeMapper.update(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("공지사항 삭제 테스트 (관리자만 가능)")
    void deleteNotice() {
        int adminId = 1;
        assertTrue(isAdmin(adminId), "관리자가 아닙니다.");
        
        Notice input = new Notice();
        input.setNoticeId(1);
        
        int output = noticeMapper.delete(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("하나의 공지사항 조회 테스트")
    void selectOneNotice() {
        Notice input = new Notice();
        input.setNoticeId(1);
        
        Notice output = noticeMapper.selectItem(input);
        assertNotNull(output);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("공지사항 목록 조회 테스트")
    void selectListNotice() {
        List<Notice> output = noticeMapper.selectList(null);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        
        for (Notice item : output) {
            log.debug("output: {}", item);
        }
    }
    
    @Test
    @DisplayName("공지사항 수 조회 테스트")
    void selectCountNotice() {
        int output = noticeMapper.selectCount(null);
        assertTrue(output >= 0);
        log.debug("공지사항 수: {}", output);
    }
}
