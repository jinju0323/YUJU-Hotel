package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Notice {
    private int noticeId;    // 공지사항 ID
    private int memberId;    // 회원 ID
    private String title;    // 제목
    private String content;  // 내용
    private String regDate;  // 등록 일자
    private String editDate; // 수정 일자

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
