package kr.project.yuju.mappers;

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
public class InquiryMapperTest {
    
    @Autowired
    private InquiryMapper inquiryMapper;

    @Test
    @DisplayName("문의 추가 테스트")
    void insertInquiry() {
        Inquiry input = new Inquiry();
        input.setMemberId(4);
        input.setSubject("회원4의 문의 제목");
        input.setMessage("문의 내용입니다.");
        input.setStatus("대기중");
        input.setReply(null);
        
        int output = inquiryMapper.insert(input);
        assertTrue(output > 0);
        assertNotNull(input.getInquiryId());
        
        log.debug("output: {}", output);
        log.debug("new id: {}", input.getInquiryId());
    }

    @Test
    @DisplayName("문의 수정 테스트")
    void updateInquiry() {
        Inquiry input = new Inquiry();
        input.setInquiryId(4);
        input.setSubject("회원4의 수정된 문의 제목");
        input.setMessage("수정된 문의 내용입니다.");
        input.setStatus("답변완료");
        input.setReply("관리자가 답변을 남겼습니다.");
        
        int output = inquiryMapper.update(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("문의 삭제 테스트")
    void deleteInquiry() {
        Inquiry input = new Inquiry();
        input.setInquiryId(1);
        
        int output = inquiryMapper.delete(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("문의 단일 조회 테스트")
    void selectOneInquiry() {
        Inquiry input = new Inquiry();
        input.setInquiryId(2);
        
        Inquiry output = inquiryMapper.selectItem(input);
        assertNotNull(output);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("문의 목록 조회 테스트")
    void selectListInquiry() {
        List<Inquiry> output = inquiryMapper.selectList(null);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        
        for (Inquiry item : output) {
            log.debug("output: {}", item);
        }
    }
    
    @Test
    @DisplayName("문의 수 조회 테스트")
    void selectCountInquiry() {
        int output = inquiryMapper.selectCount(null);
        assertTrue(output >= 0);
        log.debug("문의 수: {}", output);
    }
}
