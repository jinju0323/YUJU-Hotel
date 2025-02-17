package kr.project.yuju.models; 

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Room implements Serializable {
    private int roomId;              // 객실 ID (PK)
    private String roomType;         // 객실 유형 (Standard, Deluxe, Suite)
    private String roomCategory;     // 객실 카테고리 (A, B, C)
    private int pricePerNight;       // 1박당 가격
    private int capacity;            // 최대 수용 인원
    private String description;      // 객실 설명
    private boolean isAvailable;     // 객실 사용 가능 여부 (true = 사용 가능, false = 사용 불가능)
    private LocalDateTime regDate;   // 등록 날짜
    private LocalDateTime editDate;  // 수정 날짜

    // 리스트 페이지네이션을 위한 변수 추가
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;

}
