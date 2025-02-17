package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Payment implements Serializable {
    private int paymentId;         // 결제 ID (PK)
    private int reservationId;     // 예약 ID (reservations 테이블의 FK)
    private int memberId;          // 회원 ID (members 테이블의 FK)
    private int amount;            // 결제 금액
    private String status;         // 결제 상태 (PENDING, COMPLETED, CANCELED)
    private LocalDateTime paymentDate; // 결제 시간
    private LocalDateTime regDate; // 등록 날짜
    private LocalDateTime editDate;// 수정 날짜

    // 리스트 페이지네이션을 위한 변수 추가
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
