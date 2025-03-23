package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Breakfast implements Serializable {

    private int breakfastId;        // 조식 ID (PK)
    private int breakfastPrice;     // 조식 가격
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