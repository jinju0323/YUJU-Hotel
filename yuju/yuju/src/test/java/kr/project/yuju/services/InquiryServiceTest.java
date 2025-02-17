package kr.project.yuju.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.yuju.models.Inquiry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class InquiryServiceTest {
    
    @Autowired
    private InquiryService inquiryService;

    @Test
    @DisplayName("문의 추가 서비스 테스트")
    void addItemTest() throws Exception {
        Inquiry input = new Inquiry();
        input.setMemberId(3);
        input.setSubject("테스트 문의");
        input.setMessage("테스트 문의 내용입니다.");
        input.setStatus("대기중");
        input.setReply(null);
        
        Inquiry output = inquiryService.addItem(input);
        assertNotNull(output);
        log.debug("추가된 문의: {}", output);
    }

    @Test
    @DisplayName("문의 수정 서비스 테스트")
    void editItemTest() throws Exception {
        Inquiry input = new Inquiry();
        input.setInquiryId(3);
        input.setSubject("수정된 문의 제목");
        input.setMessage("수정된 문의 내용입니다.");
        input.setStatus("답변완료");
        input.setReply("관리자가 답변을 남겼습니다.");
        
        Inquiry output = inquiryService.editItem(input);
        assertNotNull(output);
        assertEquals("답변완료", output.getStatus());
        log.debug("수정된 문의: {}", output);
    }

    @Test
    @DisplayName("문의 삭제 서비스 테스트")
    void deleteItemTest() throws Exception {
        Inquiry input = new Inquiry();
        input.setInquiryId(1);
        
        int output = inquiryService.deleteItem(input);
        assertEquals(1, output);
        log.debug("삭제된 문의 ID: {}", input.getInquiryId());
    }

    @Test
    @DisplayName("문의 단일 조회 서비스 테스트")
    void getItemTest() throws Exception {
        Inquiry input = new Inquiry();
        input.setInquiryId(1);
        
        Inquiry output = inquiryService.getItem(input);
        assertNotNull(output);
        log.debug("조회된 문의: {}", output);
    }

    @Test
    @DisplayName("문의 목록 조회 서비스 테스트")
    void getListTest() throws Exception {
        List<Inquiry> output = inquiryService.getList(null);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        log.debug("문의 목록: {}", output);
    }
    
    @Test
    @DisplayName("문의 수 조회 서비스 테스트")
    void getCountTest() throws Exception {
        int output = inquiryService.getCount(null);
        assertTrue(output >= 0);
        log.debug("문의 수: {}", output);
    }
}
