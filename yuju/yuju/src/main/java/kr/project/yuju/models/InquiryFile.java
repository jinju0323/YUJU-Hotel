package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InquiryFile {
    private int fileId;      // 문의 파일 ID
    private int inquiryId;   // 문의 ID
    private String fileName; // 파일명
    private String filePath; // 파일 경로
    private String regDate;  // 등록 일자
    private String editDate; // 수정 일자

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}