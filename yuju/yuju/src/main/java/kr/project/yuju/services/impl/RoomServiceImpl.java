package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.RoomMapper;
import kr.project.yuju.models.Room;
import kr.project.yuju.services.RoomService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    /**
     * ✅ 객실 추가
     */
    @Override
    public Room addItem(Room input) throws Exception {
        int rows = 0;

        try {
            rows = roomMapper.insertRoom(input);

            if (rows == 0) {
                throw new Exception("추가된 객실이 없습니다.");
            }
        } catch (Exception e) {
            log.error("객실 추가에 실패했습니다.", e);
            throw e;
        }

        return roomMapper.selectItem(input);
    }
    /** ✅ 객실 수정*/

    @Override
    public Room editItem(Room input) throws Exception {
        int rows = 0;

        try {
            rows = roomMapper.updateRoom(input);

            if (rows == 0) {
                throw new Exception("수정된 객실이 없습니다.");
            }
        } catch (Exception e) {
            log.error("객실 수정에 실패했습니다.", e);
            throw e;
        }

        return roomMapper.selectItem(input);
    }

    /** ✅ 객실 삭제*/
    @Override
    public int deleteItem(Room input) throws Exception {
        int rows = 0;

        try {
            rows = roomMapper.deleteRoom(input);

            if (rows == 0) {
                throw new Exception("삭제된 객실이 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }
    
    /** ✅ 특정 객실 조회*/
    @Override
    public Room getItem(Room input) throws Exception {
        Room output = null;

        try {
            output = roomMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 객실이 없습니다.");
            }
        } catch (Exception e) {
            log.error("객실 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /** ✅ 객실 목록 조회*/
    @Override
    public List<Room> getList(Room param) throws Exception {
        List<Room> output = null;

        try {
            output = roomMapper.selectList();
        } catch (Exception e) {
            log.error("객실 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /** ✅ 객실 개수 조회*/
    @Override
    public int getCount(Room param) throws Exception {
        int output = 0;

        try {
            output = roomMapper.selectCountRoom();
        } catch (Exception e) {
            log.error("객실 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
    
}
