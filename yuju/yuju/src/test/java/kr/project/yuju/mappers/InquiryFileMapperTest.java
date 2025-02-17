package kr.project.yuju.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.yuju.models.InquiryFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class InquiryFileMapperTest {
    
    @Autowired
    private InquiryFileMapper inquiryFileMapper;

    @Test
    @DisplayName("문의 파일 추가 테스트")
    void insertInquiryFile() {
        InquiryFile input = new InquiryFile();
        input.setInquiryId(1);
        input.setFileName("test_file.jpg");
        input.setFilePath("/upload/inquiry_files/test_file.jpg");
        
        int output = inquiryFileMapper.insert(input);
        assertTrue(output > 0);
        assertNotNull(input.getFileId());
        
        log.debug("output: {}", output);
        log.debug("new file id: {}", input.getFileId());
    }

    @Test
    @DisplayName("문의 파일 수정 테스트")
    void updateInquiryFile() {
        InquiryFile input = new InquiryFile();
        input.setFileId(1);
        input.setFileName("updated_file.jpg");
        input.setFilePath("/upload/inquiry_files/updated_file.jpg");
        
        int output = inquiryFileMapper.update(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("문의 파일 삭제 테스트")
    void deleteInquiryFile() {
        InquiryFile input = new InquiryFile();
        input.setFileId(1);
        
        int output = inquiryFileMapper.delete(input);
        assertTrue(output > 0);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("문의 파일 단일 조회 테스트")
    void selectOneInquiryFile() {
        InquiryFile input = new InquiryFile();
        input.setFileId(1);
        
        InquiryFile output = inquiryFileMapper.selectItem(input);
        assertNotNull(output);
        log.debug("output: {}", output);
    }

    @Test
    @DisplayName("문의 파일 목록 조회 테스트")
    void selectListInquiryFile() {
        InquiryFile input = new InquiryFile();
        input.setInquiryId(1);
        
        List<InquiryFile> output = inquiryFileMapper.selectList(input);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        
        for (InquiryFile item : output) {
            log.debug("output: {}", item);
        }
    }
    
    @Test
    @DisplayName("문의 파일 수 조회 테스트")
    void selectCountInquiryFile() {
        int output = inquiryFileMapper.selectCount(null);
        assertTrue(output >= 0);
        log.debug("문의 파일 수: {}", output);
    }
}
