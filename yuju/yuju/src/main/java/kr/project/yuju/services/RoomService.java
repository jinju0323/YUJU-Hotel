package kr.project.yuju.services;

import java.util.List;
import kr.project.yuju.models.Room;

public interface RoomService {

    /** ✅ 객실 추가 
     * @param params
     * @return
     * @throws Exception
     */
    public Room addItem(Room params) throws Exception;

    /** ✅ 객실 수정 
     * @param params
     * @return
     * @throws Exception
     */
    public Room editItem(Room params) throws Exception;

    /** ✅ 객실 삭제 
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Room params) throws Exception;

    /** ✅ 특정 객실 조회 
     * @param params
     * @return
     * @throws Exception
     */
    public Room getItem(Room params) throws Exception;

    /** ✅ 객실 목록 조회 
     * @param params
     * @return
     *  @throws Exception
     */
    public List<Room> getList(Room params) throws Exception;

    /** ✅ 객실 개수 조회 
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(Room params) throws Exception;
}
