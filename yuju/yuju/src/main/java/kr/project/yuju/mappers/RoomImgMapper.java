package kr.project.yuju.mappers;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.RoomImg;

@Mapper
public interface RoomImgMapper {

    /** ✅ 객실 이미지 1장 추가 */
    @Insert("INSERT INTO room_imgs ( " +
            "room_id, " +
            "img_url, " +
            "reg_date, " +
            "edit_date) " +
            "VALUES ( " +
            "#{roomId}, " +
            "#{imgUrl}, " +
            "NOW(), " +
            "NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "roomImgId", keyColumn = "room_img_id")
    int insertRoomImg(RoomImg input);

    /** ✅ 객실 이미지 여러 개 추가 */
    @Insert("<script> " +
            "INSERT INTO room_imgs ( " +
            "room_id, " +
            "img_url, " +
            "reg_date, " +
            "edit_date) " +
            "VALUES " +
            "<foreach collection='roomImgList' item='img' separator=','> " +
            "( " +
            "#{img.roomId}, " +
            "#{img.imgUrl}, " +
            "NOW(), " +
            "NOW()) " +
            "</foreach> " +
            "</script>")
    int insertMultiRoomImg(@Param("roomImgList") List<RoomImg> roomImgList);

    /** ✅ 특정 이미지 삭제 */
    @Delete("DELETE FROM room_imgs WHERE room_img_id = #{roomImgId}")
    int deleteRoomImg(@Param("roomImgId") int roomImgId);

    /** ✅ 특정 객실의 모든 이미지 삭제 */
    @Delete("DELETE FROM room_imgs WHERE room_id = #{roomId}")
    int deleteMultiRoomImg(int roomId);

    /** ✅ 특정 객실의 모든 이미지 조회 */
    @Select("SELECT " +
            "room_img_id, " +
            "room_id, " +
            "img_url, " +
            "reg_date, " +
            "edit_date " +
            "FROM room_imgs " +
            "WHERE room_id = #{roomId}")
    @Results(id = "roomImgResultMap", value = {
        @Result(property = "roomImgId", column = "room_img_id"),
        @Result(property = "roomId", column = "room_id"),
        @Result(property = "imgUrl", column = "img_url"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    List<RoomImg> selectRoomImgs(@Param("roomId") int roomId);

    /** ✅ 특정 객실의 대표 이미지 조회 (최근 등록된 이미지 기준) */
    @Select("SELECT * FROM room_imgs " +
            "WHERE room_id = #{roomId} " +
            "ORDER BY reg_date DESC " +
            "LIMIT 1")
    @ResultMap("roomImgResultMap")
    RoomImg selectMainImage(@Param("roomId") int roomId);
}
