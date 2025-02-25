package kr.project.yuju.mappers;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Room;

@Mapper
public interface RoomMapper {

    /** ✅ 객실 정보 추가 */
    @Insert("INSERT INTO rooms ( " +
            "room_type, " +
            "room_category, " +
            "price_per_night, " +
            "capacity, " +
            "description, " +
            "is_available, " +
            "reg_date, " +
            "edit_date) " +
            "VALUES ( " +
            "#{roomType}, " +
            "#{roomCategory}, " +
            "#{pricePerNight}, " +
            "#{capacity}, " +
            "#{description}, " +
            "#{isAvailable}, " +
            "NOW(), " +
            "NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "roomId", keyColumn = "room_id")
    int insertRoom(Room input);

    /** ✅ 객실 정보 수정 */
    @Update("UPDATE rooms SET " +
            "room_type = #{roomType}, " +
            "room_category = #{roomCategory}, " +
            "price_per_night = #{pricePerNight}, " +
            "capacity = #{capacity}, " +
            "description = #{description}, " +
            "is_available = #{isAvailable}, " +
            "edit_date = NOW() " +
            "WHERE room_id = #{roomId}")
    int updateRoom(Room input);

    /** ✅ 객실 정보 삭제 */
    @Delete("DELETE FROM rooms WHERE room_id = #{roomId}")
    int deleteRoom(@Param("roomId") int roomId);

    /** ✅ 특정 객실 조회 */
    @Select("SELECT " +
            "room_id, " +
            "room_type, " +
            "room_category, " +
            "price_per_night, " +
            "capacity, " +
            "description, " +
            "is_available, " +
            "reg_date, " +
            "edit_date " +
            "FROM rooms " +
            "WHERE room_id = #{roomId}")
    @Results(id = "roomResultMap", value = {
        @Result(property = "roomId", column = "room_id"),
        @Result(property = "roomType", column = "room_type"),
        @Result(property = "roomCategory", column = "room_category"),
        @Result(property = "pricePerNight", column = "price_per_night"),
        @Result(property = "capacity", column = "capacity"),
        @Result(property = "description", column = "description"),
        @Result(property = "isAvailable", column = "is_available"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    Room selectItem(@Param("roomId") int roomId);

    /** ✅ 객실 목록 조회 */
    @Select("SELECT " +
            "room_id, " +
            "room_type, " +
            "room_category, " +
            "price_per_night, " +
            "capacity, " +
            "description, " +
            "is_available, " +
            "reg_date, " +
            "edit_date " +
            "FROM rooms " +
            "ORDER BY room_type, room_category")
    @ResultMap("roomResultMap")
    List<Room> selectList();

    /** ✅ 객실 개수 조회 */
    @Select("SELECT COUNT(room_id) FROM rooms")
    int selectCountRoom();
}
