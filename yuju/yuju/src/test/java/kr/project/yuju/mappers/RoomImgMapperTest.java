package kr.project.yuju.mappers;

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
public class RoomImgMapperTest {

    @Autowired
    private RoomImgMapper roomImgMapper;

    /** ✅ 객실 이미지 추가 테스트 */
    @Test
    @DisplayName("객실 이미지 추가 테스트")
    void insertRoomImg() {
        RoomImg input = new RoomImg();
        input.setRoomId(1);
        input.setImgUrl("/rooms/deluxe/deluxe1.jpg");

        int output = roomImgMapper.insertRoomImg(input);
        log.debug("객실 이미지 추가 결과: {}", output);
        log.debug("새 이미지 ID: {}", input.getRoomImgId());

        assertTrue(output > 0, "객실 이미지 추가 실패");
    }

    /** ✅ 객실 이미지 여러 장 추가 테스트 */
    @Test
    @DisplayName("객실 이미지 여러 개 추가 테스트")
    void insertMultiRoomImg() {
        RoomImg img1 = new RoomImg();
        img1.setRoomId(4);
        img1.setImgUrl("/rooms/stand/stand1.jpg");

        RoomImg img2 = new RoomImg();
        img2.setRoomId(4);
        img2.setImgUrl("/rooms/stand/stand2.jpg");

        RoomImg img3 = new RoomImg();
        img3.setRoomId(4);
        img3.setImgUrl("/rooms/stand/stand3.jpg");

        List<RoomImg> roomImgList = Arrays.asList(img1, img2, img3);
        int output = roomImgMapper.insertMultiRoomImg(roomImgList);

        log.debug("객실 이미지 여러 개 추가 결과: {}", output);
        assertTrue(output > 0, "객실 이미지 여러 개 추가 실패");
    }

    /** ✅ 특정 객실의 모든 이미지 조회 테스트 */
    @Test
    @DisplayName("객실 이미지 조회 테스트")
    void selectRoomImgs() {
        int roomId = 1; // 조회할 객실 ID

        List<RoomImg> images = roomImgMapper.selectRoomImgs(roomId);

        log.debug("객실 ID {}의 이미지 목록: {}", roomId, images);
        assertFalse(images.isEmpty(), "해당 객실의 이미지가 존재하지 않습니다.");
    }

    /** ✅ 특정 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 특정 이미지 삭제 테스트")
    void deleteRoomImg() {
        int roomImgId = 1; // 삭제할 이미지 ID

        int output = roomImgMapper.deleteRoomImg(roomImgId);

        log.debug("객실 이미지 삭제 결과: {}", output);
        log.debug("✅ 삭제된 이미지 ID: {}", roomImgId);

        assertTrue(output > 0, "객실 이미지 삭제 실패");
    }

    /** ✅ 특정 객실의 모든 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 모든 이미지 삭제 테스트")
    void deleteMultiRoomImg() {
        int roomId = 4; // 삭제할 객실 ID

        int output = roomImgMapper.deleteMultiRoomImg(roomId);

        log.debug("객실의 모든 이미지 삭제 결과: {}", output);
        log.debug("✅ 객실 ID {}의 모든 이미지 삭제 완료", roomId);

        assertTrue(output > 0, "객실의 모든 이미지 삭제 실패");
    }

    /** ✅ 특정 객실의 대표 이미지 조회 테스트 */
    @Test
    @DisplayName("객실의 대표 이미지 조회 테스트")
    void selectMainImage() {
        int roomId = 3; // 조회할 객실 ID

        RoomImg mainImage = roomImgMapper.selectMainImage(roomId);

        log.debug("대표 이미지 ID: {}", mainImage.getRoomImgId());
        log.debug("객실 ID: {}", mainImage.getRoomId());
        log.debug("이미지 경로: {}", mainImage.getImgUrl());

        assertNotNull(mainImage, "대표 이미지 조회 실패");
    }
}
