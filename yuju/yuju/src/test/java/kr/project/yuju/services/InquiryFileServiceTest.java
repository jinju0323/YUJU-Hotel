package kr.project.yuju.services;

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
public class InquiryFileServiceTest {
    
    @Autowired
    private InquiryFileService inquiryFileService;

    @Test
    @DisplayName("문의 파일 추가 서비스 테스트")
    void addItemTest() throws Exception {
        InquiryFile input = new InquiryFile();
        input.setInquiryId(1);
        input.setFileName("test_file.jpg");
        input.setFilePath("/upload/inquiry_files/test_file123.jpg");
        
        InquiryFile output = inquiryFileService.addItem(input);
        assertNotNull(output);
        log.debug("추가된 문의 파일: {}", output);
    }

    @Test
    @DisplayName("문의 파일 수정 서비스 테스트")
    void editItemTest() throws Exception {
        InquiryFile input = new InquiryFile();
        input.setFileId(1);
        input.setFileName("updated_file.jpg");
        input.setFilePath("/upload/inquiry_files/updated_file456.jpg");
        
        InquiryFile output = inquiryFileService.editItem(input);
        assertNotNull(output);
        assertEquals("updated_file.jpg", output.getFileName());
        log.debug("수정된 문의 파일: {}", output);
    }

    @Test
    @DisplayName("문의 파일 삭제 서비스 테스트")
    void deleteItemTest() throws Exception {
        InquiryFile input = new InquiryFile();
        input.setFileId(1);
        
        int output = inquiryFileService.deleteItem(input);
        assertEquals(1, output);
        log.debug("삭제된 문의 파일 ID: {}", input.getFileId());
    }

    @Test
    @DisplayName("문의 파일 단일 조회 서비스 테스트")
    void getItemTest() throws Exception {
        InquiryFile input = new InquiryFile();
        input.setFileId(1);
        
        InquiryFile output = inquiryFileService.getItem(input);
        assertNotNull(output);
        log.debug("조회된 문의 파일: {}", output);
    }

    @Test
    @DisplayName("문의 파일 목록 조회 서비스 테스트")
    void getListTest() throws Exception {
        InquiryFile input = new InquiryFile();
        input.setInquiryId(1);
        
        List<InquiryFile> output = inquiryFileService.getList(input);
        assertNotNull(output);
        assertFalse(output.isEmpty());
        log.debug("문의 파일 목록: {}", output);
    }
    
    @Test
    @DisplayName("문의 파일 수 조회 서비스 테스트")
    void getCountTest() throws Exception {
        int output = inquiryFileService.getCount(null);
        assertTrue(output >= 0);
        log.debug("문의 파일 수: {}", output);
    }
}
