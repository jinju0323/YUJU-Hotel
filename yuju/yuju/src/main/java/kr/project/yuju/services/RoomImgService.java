package kr.project.yuju.services;

import java.util.List;
import kr.project.yuju.models.RoomImg;

public interface RoomImgService {

    /** ✅ 객실 이미지 1장 추가 
     * @param params   
     * @return RoomImg
     * @throws Exception
     *  */
    public RoomImg addRoomImg(RoomImg params) throws Exception;

    /** ✅ 객실 이미지 여러 장 추가 
     * @param params
     * @return int
     * @throws Exception
     * */
    public int addMultipleRoomImgs(List<RoomImg> roomImgList) throws Exception;

    /** ✅ 특정 객실 이미지 삭제 
     * @param params
     * @return int
     * @throws Exception
     * */
    public int deleteRoomImg(RoomImg params) throws Exception;

    /** ✅ 특정 객실의 모든 이미지 삭제 
     * @param roomId
     * @return int
     * @throws Exception
     * */
    public int deleteMultiRoomImgs(int roomId) throws Exception;

    /** ✅ 특정 객실의 모든 이미지 조회 
     * @param roomId
     * @return List<RoomImg>
     * @throws Exception
     *  */
    public List<RoomImg> getRoomImgsByRoomId(int roomId) throws Exception;
}
