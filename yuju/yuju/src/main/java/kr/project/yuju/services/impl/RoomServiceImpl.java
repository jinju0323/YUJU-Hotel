package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.RoomMapper;
import kr.project.yuju.models.Room;
import kr.project.yuju.services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;

    /** ✅ 객실 추가 */
    @Override
    public Room addItem(Room input) throws Exception {
        try {
            int rows = roomMapper.insertRoom(input);
            if (rows == 0) {
                throw new Exception("객실 추가에 실패하였습니다.");
            }
            return roomMapper.selectItem(input.getRoomId());
        } catch (Exception e) {
            log.error("객실 추가 실패", e);
            throw e;
        }
    }

    /** ✅ 객실 수정 */
    @Override
    public Room editItem(Room room) throws Exception {
        try {
            int rows = roomMapper.updateRoom(room);
            if (rows == 0) {
                throw new Exception("객실 수정에 실패하였습니다.");
            }
            return roomMapper.selectItem(room.getRoomId());
        } catch (Exception e) {
            log.error("객실 수정 실패", e);
            throw e;
        }
    }

    /** ✅ 객실 삭제 */
    @Override
    public int deleteItem(int roomId) throws Exception {
        try {
            int rows = roomMapper.deleteRoom(roomId);
            if (rows == 0) {
                throw new Exception("객실 삭제에 실패하였습니다.");
            }
            return rows;
        } catch (Exception e) {
            log.error("객실 삭제 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 객실 조회 */
    @Override
    public Room getItem(int roomId) throws Exception {
        try {
            Room output = roomMapper.selectItem(roomId);
            if (output == null) {
                throw new Exception("해당 ID의 객실이 존재하지 않습니다.");
            }
            return output;
        } catch (Exception e) {
            log.error("객실 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 객실 목록 조회 */
    @Override
    public List<Room> getList() throws Exception {
        try {
            return roomMapper.selectList();
        } catch (Exception e) {
            log.error("객실 목록 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 객실 개수 조회 */
    @Override
    public int getCount() throws Exception {
        try {
            return roomMapper.selectCountRoom();
        } catch (Exception e) {
            log.error("객실 개수 조회 실패", e);
            throw e;
        }
    }
}
