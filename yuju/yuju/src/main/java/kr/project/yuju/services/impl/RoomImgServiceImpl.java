package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.RoomImgMapper;
import kr.project.yuju.models.RoomImg;
import kr.project.yuju.services.RoomImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomImgServiceImpl implements RoomImgService {

    private final RoomImgMapper roomImgMapper;

    /** ✅ 객실 이미지 1장 추가 */
    @Override
    public RoomImg addRoomImg(RoomImg roomImg) throws Exception {
        try {
            int rows = roomImgMapper.insertRoomImg(roomImg);
            if (rows == 0) {
                throw new Exception("이미지가 추가되지 않았습니다.");
            }
            return roomImgMapper.selectMainImage(roomImg.getRoomId());
        } catch (Exception e) {
            log.error("이미지 추가 실패", e);
            throw e;
        }
    }

    /** ✅ 객실 이미지 여러 장 추가 */
    @Override
    public int addMultipleRoomImgs(List<RoomImg> roomImgList) throws Exception {
        try {
            int rows = roomImgMapper.insertMultiRoomImg(roomImgList);
            if (rows == 0) {
                throw new Exception("여러 개의 이미지가 추가되지 않았습니다.");
            }
            return rows;
        } catch (Exception e) {
            log.error("여러 개의 이미지 추가 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 객실 이미지 삭제 */
    @Override
    public int deleteRoomImg(int roomImgId) throws Exception {
        try {
            int rows = roomImgMapper.deleteRoomImg(roomImgId);
            if (rows == 0) {
                throw new Exception("삭제된 이미지가 없습니다.");
            }
            return rows;
        } catch (Exception e) {
            log.error("이미지 삭제 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 객실의 모든 이미지 삭제 */
    @Override
    public int deleteMultiRoomImgs(int roomId) throws Exception {
        try {
            int rows = roomImgMapper.deleteMultiRoomImg(roomId);
            if (rows == 0) {
                throw new Exception("객실 ID에 해당하는 이미지가 없습니다.");
            }
            return rows;
        } catch (Exception e) {
            log.error("객실의 모든 이미지 삭제 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 객실의 모든 이미지 조회 */
    @Override
    public List<RoomImg> getRoomImgsByRoomId(int roomId) throws Exception {
        try {
            List<RoomImg> output = roomImgMapper.selectRoomImgs(roomId);
            if (output == null || output.isEmpty()) {
                throw new Exception("해당 객실의 이미지가 존재하지 않습니다.");
            }
            return output;
        } catch (Exception e) {
            log.error("객실 이미지 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 객실의 대표 이미지 조회 */
    @Override
    public RoomImg getMainImage(int roomId) throws Exception {
        try {
            RoomImg mainImage = roomImgMapper.selectMainImage(roomId);
            if (mainImage == null) {
                throw new Exception("대표 이미지가 존재하지 않습니다.");
            }
            log.info("객실 ID {}의 대표 이미지 조회: {}", roomId, mainImage.getImgUrl());
            return mainImage;
        } catch (Exception e) {
            log.error("대표 이미지 조회 실패", e);
            throw e;
        }
    }
}
