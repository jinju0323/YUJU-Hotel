package kr.project.yuju.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.RoomImg;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

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

        assertNotNull(output, "객실 이미지 추가 실패");
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

        assertTrue(output > 0, "객실 이미지 여러 개 추가 실패");
    }

    /** ✅ 특정 객실의 모든 이미지 조회 테스트 */
    @Test
    @DisplayName("객실 이미지 조회 테스트")
    void selectRoomImgs() throws Exception {
        int roomId = 1; 

        List<RoomImg> images = roomImgService.getRoomImgsByRoomId(roomId);
        log.debug("객실 ID {}의 이미지 리스트: {}", roomId, images);

        assertFalse(images.isEmpty(), "해당 객실의 이미지가 존재하지 않습니다.");
    }

    /** ✅ 특정 객실의 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 특정 이미지 삭제 테스트")
    void deleteRoomImg() throws Exception {
        int roomImgId = 2; // 삭제할 이미지 ID

        int output = roomImgService.deleteRoomImg(roomImgId);
        log.debug("객실 이미지 삭제 완료: {}", output);

        assertTrue(output > 0, "객실 이미지 삭제 실패");
    }

    /** ✅ 특정 객실의 모든 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 모든 이미지 삭제 테스트")
    void deleteMultiRoomImg() throws Exception {
        int roomId = 1; 

        int output = roomImgService.deleteMultiRoomImgs(roomId);
        log.debug("객실의 모든 이미지 삭제 완료: {}", output);

        assertTrue(output > 0, "객실의 모든 이미지 삭제 실패");
    }

    /** ✅ 특정 객실의 대표 이미지 조회 테스트 */
    @Test
    @DisplayName("객실의 대표 이미지 조회 테스트")
    void getMainImage() {
        int roomIdTest = 3; // 테스트할 객실 ID

        try {
            // ✅ 대표 이미지 조회
            RoomImg mainImage = roomImgService.getMainImage(roomIdTest);

            // ✅ 로그 출력
            log.debug("대표 이미지 ID: {}", mainImage.getRoomImgId());
            log.debug("객실 ID: {}", mainImage.getRoomId());
            log.debug("이미지 경로: {}", mainImage.getImgUrl());

            assertNotNull(mainImage, "대표 이미지 조회 실패");

        } catch (Exception e) {
            log.error("대표 이미지 조회 실패: {}", e.getMessage());
            fail("대표 이미지 조회 예외 발생");
        }
    }
}
