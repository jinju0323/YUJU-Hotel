package kr.project.yuju.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Room;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RoomMapperTest {

    @Autowired
    private RoomMapper roomMapper;

    /** ✅ 객실 추가 테스트 */
    @Test
    @DisplayName("객실 추가 테스트")
    void insertRoom() {
        Room input = new Room();
        input.setRoomType("Standard");
        input.setRoomCategory("A");
        input.setPricePerNight(200000);
        input.setCapacity(3);
        input.setDescription("오션뷰 스탠다드룸.");
        input.setAvailable(true);

        int output = roomMapper.insertRoom(input);
        log.debug("output: {}", output);
        log.debug("새 객실 ID: {}", input.getRoomId());
    }

    /** ✅ 특정 객실 조회 테스트 */
    @Test
    @DisplayName("객실 단일 조회 테스트")
    void selectItemRoom() {
        Room input = new Room();
        input.setRoomId(11);
        
        Room output = roomMapper.selectItem(input);
        log.debug("output: {}", output);
    }

    /** ✅ 객실 목록 조회 테스트 */
    @Test
    @DisplayName("객실 목록 조회 테스트")
    void selectListRoom() {
        List<Room> output = roomMapper.selectList();
        
        for (Room item : output) {
            log.debug("output: {}", item);
        }
    }

    /** ✅ 객실 개수 조회 테스트 */
    @Test
    @DisplayName("객실 개수 조회 테스트")
    void selectCountRoom() {
        int output = roomMapper.selectCountRoom();
        log.debug("객실 수: {}", output);
    }

    /** ✅ 객실 수정 테스트 */
    @Test
    @DisplayName("객실 수정 테스트")
    void updateRoom() {
        Room input = new Room();
        input.setRoomId(11);
        input.setRoomType("Deluxe");
        input.setRoomCategory("B");
        input.setPricePerNight(200000);
        input.setCapacity(3);
        input.setDescription("오션뷰 디럭스룸.");
        input.setAvailable(true);
        
        int output = roomMapper.updateRoom(input);
        log.debug("output: {}", output);
    }

    /** ✅ 객실 삭제 테스트 */
    @Test
    @DisplayName("객실 삭제 테스트")
    void DeleteRoom() {
        Room input = new Room();
        input.setRoomId(12);
        
        int output = roomMapper.deleteRoom(input);
        log.debug("output: {}", output);
        log.debug("삭제.");
    }
}
