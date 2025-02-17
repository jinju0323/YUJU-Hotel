package kr.project.yuju.mappers;

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
        log.debug("output: {}", output);
        log.debug("새 이미지 ID: {}", input.getRoomImgId());
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

        log.debug("output: {}", output);
    }

    /** ✅ 특정 객실의 모든 이미지 조회 테스트 */
    @Test
    @DisplayName("객실 이미지 조회 테스트")
    void selectRoomImgs() {
        RoomImg input =new RoomImg();
        input.setRoomImgId(1);
       
        List<RoomImg> images = roomImgMapper.selectRoomImgs(1);

        log.debug("output: {}", input.getRoomId(), images);
    }

    /** ✅ 특정 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 특정 이미지 삭제 테스트")
    void deleteRoomImg() {
        RoomImg input = new RoomImg();
        input.setRoomImgId(1); // 삭제할 이미지 ID 설정

        // 삭제 실행
        int output = roomImgMapper.deleteRoomImg(input);
        
        log.debug("output:{}", output);
        log.debug("✅ 삭제된 이미지 ID: {}", input.getRoomImgId());
    }

    /** ✅ 특정 객실의 모든 이미지 삭제 테스트 */
    @Test
    @DisplayName("객실의 모든 이미지 삭제 테스트")
    void deleteMultiRoomImg() {
        RoomImg input = new RoomImg();
        input.setRoomId(4); // 삭제할 객실 ID 설정

        // 삭제 실행
        int output = roomImgMapper.deleteMultiRoomImg(input.getRoomId());
        
        log.debug("output:{}", output);
        log.debug("✅ 객실 ID {}의 모든 이미지 삭제 완료", input.getRoomId());
    }


}
