package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Member {
    private int memberId;    // 회원ID
    private String userName; // 이름
    private String userId;   // 아이디
    private String userPw;   // 비밀번호
    private String isOut;    // 탈퇴 여부 (Y/N)
    private String isAdmin;  // 관리자 여부 (Y/N)
    private String loginDate;// 최종 로그인 일자
    private String regDate;  // 등록 일자
    private String editDate; // 수정 일자

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}