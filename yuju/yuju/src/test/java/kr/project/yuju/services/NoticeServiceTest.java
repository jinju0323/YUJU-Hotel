package kr.project.yuju.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.yuju.models.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class NoticeServiceTest {
    
    @Autowired
    private NoticeService noticeService;

    @Test
    @DisplayName("공지사항 추가 서비스 테스트")
    void addItemTest() throws Exception {
        Notice input = new Notice();
        input.setMemberId(1);
        input.setTitle("테스트 공지사항");
        input.setContent("이것은 공지사항 내용입니다.");
        
        Notice output = noticeService.addItem(input);
        assertNotNull(output);
        log.debug("추가된 공지사항: {}", output);
    }

    @Test
    @DisplayName("공지사항 수정 서비스 테스트")
    void editItemTest() throws Exception {
        Notice input = new Notice();
        input.setNoticeId(1);
        input.setTitle("수정된 공지사항 제목");
        input.setContent("수정된 공지사항 내용입니다.");
        
        Notice output = noticeService.editItem(input);
        assertNotNull(output);
        assertEquals("수정된 공지사항 제목", output.getTitle());
        log.debug("수정된 공지사항: {}", output);
    }

    @Test
    @DisplayName("공지사항 삭제 서비스 테스트")
    void deleteItemTest() throws Exception {
        Notice input = new Notice();
        input.setNoticeId(1);
        
        int output = noticeService.deleteItem(input);
        assertEquals(1, output);
        log.debug("삭제된 공지사항 ID: {}", input.getNoticeId());
    }

    @Test
    @DisplayName("공지사항 단일 조회 서비스 테스트")
    void getItemTest() throws Exception {
        Notice input = new Notice();
        input.setNoticeId(1);
        
        Notice output = noticeService.getItem(input);
        assertNotNull(output);
        log.debug("조회된 공지사항: {}", output);
    }

    @Test
    @DisplayName("공지사항 목록 조회 서비스 테스트")
    void getListTest() throws Exception {
        List<Notice> output = noticeService.getList(null);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        log.debug("공지사항 목록: {}", output);
    }
    
    @Test
    @DisplayName("공지사항 수 조회 서비스 테스트")
    void getCountTest() throws Exception {
        int output = noticeService.getCount(null);
        assertTrue(output >= 0);
        log.debug("공지사항 수: {}", output);
    }
}
