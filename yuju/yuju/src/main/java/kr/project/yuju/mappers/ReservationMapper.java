package kr.project.yuju.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Reservation;

@Mapper
public interface ReservationMapper {

    /** ✅ 예약 추가 */
    @Insert("INSERT INTO reservations ( " +
            "member_id, " +
            "room_id, " +
            "check_in_date, " +
            "check_out_date, " +
            "total_price, " +
            "reserved_at, " +
            "reg_date, " +
            "edit_date) " +
            "VALUES ( " +
            "#{memberId}, " +
            "#{roomId}, " +
            "#{checkInDate}, " +
            "#{checkOutDate}, " +
            "#{totalPrice}, " +
            "NULL, " +
            "NOW(), " +
            "NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "reservationId", keyColumn = "reservation_id")
    int insertReservation(Reservation input);

    /** ✅ 특정 예약 정보 조회 */
    @Select("SELECT " +
            "reservation_id, " +
            "member_id, " +
            "room_id, " +
            "check_in_date, " +
            "check_out_date, " +
            "total_price, " +
            "status, " +
            "reserved_at, " +
            "reg_date, " +
            "edit_date " +
            "FROM reservations " +
            "WHERE reservation_id = #{reservationId}")
    @Results(id = "reservationResultMap", value = {
        @Result(property = "reservationId", column = "reservation_id"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "roomId", column = "room_id"),
        @Result(property = "checkInDate", column = "check_in_date"),
        @Result(property = "checkOutDate", column = "check_out_date"),
        @Result(property = "totalPrice", column = "total_price"),
        @Result(property = "status", column = "status"),
        @Result(property = "reservedAt", column = "reserved_at"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    Reservation selectReservation(@Param("reservationId") int reservationId);

    /** ✅ 회원 ID로 예약 조회 */
    @Select("SELECT " +
            "reservation_id, " +
            "member_id, " +
            "room_id, " +
            "check_in_date, " +
            "check_out_date, " +
            "total_price, " +
            "status, " +
            "reserved_at, " +
            "reg_date, " +
            "edit_date " +
            "FROM reservations " +
            "WHERE member_id = #{memberId} " +
            "ORDER BY check_in_date DESC")
    @ResultMap("reservationResultMap")
    List<Reservation> selectByMemberId(@Param("memberId") int memberId);

    /** ✅ 객실 ID로 예약 조회 (객실 예약 현황 확인용) */
    @Select("SELECT " +
            "reservation_id, " +
            "member_id, " +
            "room_id, " +
            "check_in_date, " +
            "check_out_date, " +
            "total_price, " +
            "status, " +
            "reserved_at, " +
            "reg_date, " +
            "edit_date " +
            "FROM reservations " +
            "WHERE room_id = #{roomId} " +
            "ORDER BY check_in_date ASC")
    @ResultMap("reservationResultMap")
    List<Reservation> selectByRoomId(@Param("roomId") int roomId);
    
    /** ✅ 전체 예약 개수 조회 */
    @Select("SELECT COUNT(reservation_id) " +
            "FROM reservations")
    int selectCount();
}
