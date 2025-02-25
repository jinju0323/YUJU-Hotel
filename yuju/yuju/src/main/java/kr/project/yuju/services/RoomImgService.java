package kr.project.yuju.services;

import java.util.List;
import kr.project.yuju.models.RoomImg;

public interface RoomImgService {

    /** ✅ 객실 이미지 1장 추가 */
    RoomImg addRoomImg(RoomImg roomImg) throws Exception;

    /** ✅ 객실 이미지 여러 장 추가 */
    int addMultipleRoomImgs(List<RoomImg> roomImgList) throws Exception;

    /** ✅ 특정 객실 이미지 삭제 */
    int deleteRoomImg(int roomImgId) throws Exception;

    /** ✅ 특정 객실의 모든 이미지 삭제 */
    int deleteMultiRoomImgs(int roomId) throws Exception;

    /** ✅ 특정 객실의 모든 이미지 조회 */
    List<RoomImg> getRoomImgsByRoomId(int roomId) throws Exception;

    /** ✅ 특정 객실의 대표 이미지 조회 */
    RoomImg getMainImage(int roomId) throws Exception;
}
