package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Inquiry {
    private int inquiryId;    // 문의 ID
    private Integer memberId; // 회원 ID (NULL 허용)
    private String subject;   // 제목
    private String message;   // 내용
    private String status;    // 상태 (PENDING, ANSWERED)
    private String reply;     // 답변
    private String regDate;   // 등록 일자
    private String editDate;  // 수정 일자

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}