package kr.project.yuju.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RoomImg implements Serializable {
    private int roomImgId;         // 객실 이미지 ID (PK)
    private int roomId;            // 객실 ID (rooms 테이블의 FK)
    private String imgUrl;         // 이미지 경로
    private LocalDateTime regDate; // 등록 날짜
    private LocalDateTime editDate;// 수정 날짜

    // 리스트 페이지네이션을 위한 변수 추가
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;

    public RoomImg() {}
    
    public RoomImg(int roomId, String imgUrl) {
        this.roomId = roomId;
        this.imgUrl = imgUrl;
    }
}
