package kr.project.yuju.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Room;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    /** ✅ 객실 추가 테스트 */
    @Test
    @DisplayName("객실 추가 테스트")
    void addRoom() throws Exception {
        Room input = new Room();
        input.setRoomType("Standard");
        input.setRoomCategory("A");
        input.setPricePerNight(150000);
        input.setCapacity(2);
        input.setDescription("오션뷰 스탠다드룸");
        input.setAvailable(true);

        Room output = roomService.addItem(input);
        log.debug("추가된 객실: {}", output);
    }

    /** ✅ 특정 객실 조회 테스트 */
    @Test
    @DisplayName("객실 조회 테스트")
    void getRoom() throws Exception {
        Room input = new Room();
        input.setRoomId(1);

        Room output = roomService.getItem(input);

        log.debug("조회된 객실: {}", output);
    }

    /** ✅ 객실 목록 조회 테스트 */
    @Test
    @DisplayName("객실 목록 조회 테스트")
    void getRoomList() throws Exception {
        List<Room> output = roomService.getList(null);

        log.debug("객실 목록 조회 결과: {}", output);
    }

    /** ✅ 객실 개수 조회 테스트 */
    @Test
    @DisplayName("객실 개수 조회 테스트")
    void getRoomCount() throws Exception {
        int count = roomService.getCount(null);

        log.debug("객실 총 개수: {}", count);
    }

    /** ✅ 객실 수정 테스트 */
    @Test
    @DisplayName("객실 수정 테스트")
    void updateRoom() throws Exception {
        Room input = new Room();
        input.setRoomId(13);
        input.setRoomType("Deluxe");
        input.setRoomCategory("B");
        input.setPricePerNight(200000);
        input.setCapacity(3);
        input.setDescription("리뉴얼된 디럭스룸");
        input.setAvailable(true);

        Room output = roomService.editItem(input);

        log.debug("수정된 객실: {}", output);
    }

    /** ✅ 객실 삭제 테스트 */
    @Test
    @DisplayName("객실 삭제 테스트")
    void deleteRoom() throws Exception {
        Room input = new Room();
        input.setRoomId(13);

        int output = roomService.deleteItem(input);

        log.debug("output: {}", output);
        log.debug("객실 삭제 완료: {}", input.getRoomId());
    }
}
