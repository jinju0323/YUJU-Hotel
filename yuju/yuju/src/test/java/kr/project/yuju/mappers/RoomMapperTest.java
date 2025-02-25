package kr.project.yuju.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Room;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

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
        log.debug("객실 추가 결과: {}", output);
        log.debug("새 객실 ID: {}", input.getRoomId());

        assertTrue(output > 0, "객실 추가 실패");
    }

    /** ✅ 특정 객실 조회 테스트 */
    @Test
    @DisplayName("객실 단일 조회 테스트")
    void selectItemRoom() {
        int roomId = 11; // 존재하는 객실 ID 사용
        Room output = roomMapper.selectItem(roomId);
        log.debug("조회된 객실 정보: {}", output);

        assertNotNull(output, "객실 조회 실패: 존재하지 않는 객실");
    }

    /** ✅ 객실 목록 조회 테스트 */
    @Test
    @DisplayName("객실 목록 조회 테스트")
    void selectListRoom() {
        List<Room> output = roomMapper.selectList();
        output.forEach(room -> log.debug("객실 목록 출력: {}", room));

        assertFalse(output.isEmpty(), "객실 목록이 비어 있음");
    }

    /** ✅ 객실 개수 조회 테스트 */
    @Test
    @DisplayName("객실 개수 조회 테스트")
    void selectCountRoom() {
        int output = roomMapper.selectCountRoom();
        log.debug("총 객실 수: {}", output);

        assertTrue(output >= 0, "객실 개수 조회 오류");
    }

    /** ✅ 객실 수정 테스트 */
    @Test
    @DisplayName("객실 수정 테스트")
    void updateRoom() {
        Room input = new Room();
        input.setRoomId(11);
        input.setRoomType("Deluxe");
        input.setRoomCategory("B");
        input.setPricePerNight(250000);
        input.setCapacity(3);
        input.setDescription("오션뷰 디럭스룸.");
        input.setAvailable(true);

        int output = roomMapper.updateRoom(input);
        log.debug("객실 수정 결과: {}", output);

        assertTrue(output > 0, "객실 수정 실패");
    }

    /** ✅ 객실 삭제 테스트 */
    @Test
    @DisplayName("객실 삭제 테스트")
    void deleteRoom() {
        int roomId = 12; // 삭제할 객실 ID

        int output = roomMapper.deleteRoom(roomId);
        log.debug("객실 삭제 결과: {}", output);
        log.debug("객실 ID {} 삭제 완료.", roomId);

        assertTrue(output > 0, "객실 삭제 실패: 존재하지 않는 객실 ID");
    }
}
