package kr.project.yuju.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.RoomImg;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RoomImgServiceTest {

    @Autowired
    private RoomImgService roomImgService;

    /** ✅ 객실 이미지 추가 테스트 */
    @Test
    @DisplayName("객실 이미지 추가 테스트")
    void insertRoomImg() throws Exception {
        RoomImg input = new RoomImg(2, "/rooms/deluxe/deluxe1.jpg");

        RoomImg output = roomImgService.addRoomImg(input);
        log.debug("객실 이미지 추가 결과: {}", output);
    }

    /** ✅ 객실 이미지 여러 개 추가 테스트 */
    @Test
    @DisplayName("객실 이미지 여러 개 추가 테스트")
    void insertMultiRoomImg() throws Exception {
        List<RoomImg> roomImgList = Arrays.asList(
            new RoomImg(1, "/rooms/stand/stand1.jpg"),
            new RoomImg(1, "/rooms/stand/stand2.jpg"),
            new RoomImg(1, "/rooms/stand/stand3.jpg")
        );

        int output = roomImgService.addMultipleRoomImgs(roomImgList);
        log.debug("{}개의 객실 이미지 추가 완료", output);
    }

    /** ✅ 특정 객실의 모든 이미지 조회 테스트 */
    @Test
    @DisplayName("객실 이미지 조회 테스트")
    void selectRoomImgs() throws Exception {
        List<RoomImg> images = roomImgService.getRoomImgsByRoomId(1); 
        log.debug("객실 이미지 리스트: {}", images);
    }

    /** ✅ 특정 객실의 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 특정 이미지 삭제 테스트")
    void deleteRoomImg() throws Exception {
        RoomImg input = new RoomImg(2, "/rooms/deluxe/deluxe1.jpg");

        int output = roomImgService.deleteRoomImg(input);
        log.debug("객실 이미지 삭제 완료: {}", output);
    }

    /** ✅ 특정 객실의 모든 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 모든 이미지 삭제 테스트")
    void deleteMultiRoomImg() throws Exception {
        int output = roomImgService.deleteMultiRoomImgs(1); 
        log.debug("객실의 모든 이미지 삭제 완료: {}", output);
    }
}
