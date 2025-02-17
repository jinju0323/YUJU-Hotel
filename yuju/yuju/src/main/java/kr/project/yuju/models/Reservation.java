package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Reservation implements Serializable {
    private int reservationId;      // 예약 ID (PK)
    private int memberId;           // 회원 ID (members 테이블의 FK)
    private int roomId;             // 객실 ID (rooms 테이블의 FK)
    private LocalDate checkInDate;  // 체크인 날짜
    private LocalDate checkOutDate; // 체크아웃 날짜
    private int totalPrice;         // 총 금액
    private String status;          // 예약 상태 (PENDING, CONFIRMED, CANCELED)
    private LocalDateTime reservedAt; // 예약 생성 시간
    private LocalDateTime regDate;  // 등록 날짜
    private LocalDateTime editDate; // 수정 날짜

    // 리스트 페이지네이션을 위한 변수 추가
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
