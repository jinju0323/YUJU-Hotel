package kr.project.yuju.services;

import java.util.List;
import kr.project.yuju.models.Room;

public interface RoomService {

    /** ✅ 객실 추가 
     * @param room 객실 정보
     * @return 추가된 객실
     * @throws Exception
     */
    Room addItem(Room room) throws Exception;

    /** ✅ 객실 수정 
     * @param room 수정할 객실 정보
     * @return 수정된 객실
     * @throws Exception
     */
    Room editItem(Room room) throws Exception;

    /** ✅ 객실 삭제 
     * @param roomId 삭제할 객실 ID
     * @return 삭제된 객실 수 (1이면 성공, 0이면 실패)
     * @throws Exception
     */
    int deleteItem(int roomId) throws Exception;

    /** ✅ 특정 객실 조회 
     * @param roomId 조회할 객실 ID
     * @return 조회된 객실 정보
     * @throws Exception
     */
    Room getItem(int roomId) throws Exception;

    /** ✅ 객실 목록 조회 
     * @return 전체 객실 목록
     * @throws Exception
     */
    List<Room> getList() throws Exception;

    /** ✅ 객실 개수 조회 
     * @return 전체 객실 개수
     * @throws Exception
     */
    int getCount() throws Exception;     
}
