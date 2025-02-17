package kr.project.yuju.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Payment;

@Mapper
public interface PaymentMapper {

    /** ✅ 결제 추가 (PENDING 상태로 생성) */
    @Insert("INSERT INTO payments ( " +
            "reservation_id, " +
            "member_id, " +
            "amount, " +
            "payment_date, " +
            "reg_date, " +
            "edit_date) " +
            "VALUES ( " +
            "#{reservationId}, " +
            "#{memberId}, " +
            "#{amount}, " +
            "NULL, " +
            "NOW(), " +
            "NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "paymentId", keyColumn = "payment_id")
    int insertPayment(Payment input);

    /** ✅ 결제 완료 (COMPLETED) + 예약 확정 (CONFIRMED) */
    @Update({
        "UPDATE payments SET " +
        "status = 'COMPLETED', " +
        "payment_date = NOW(), " +
        "edit_date = NOW() " +
        "WHERE payment_id = #{paymentId} " +
        "AND status = 'PENDING';",
        
        "UPDATE reservations SET " +
        "status = 'CONFIRMED', " +
        "reserved_at = NOW(), " +
        "edit_date = NOW() " +
        "WHERE reservation_id = ( " +
        "SELECT reservation_id FROM payments " +
        "WHERE payment_id = #{paymentId})"
    })
    int completePayment(@Param("paymentId") int paymentId);

    /** ✅ 결제 취소 (CANCELED) + 예약 취소 */
    @Update({
        "UPDATE payments SET " +
        "status = 'CANCELED', " +
        "payment_date = NULL, " +
        "edit_date = NOW() " +
        "WHERE payment_id = #{paymentId} " +
        "AND status = 'COMPLETED';",

        "UPDATE reservations SET " +
        "status = 'CANCELED', " +
        "reserved_at = NULL, " +
        "edit_date = NOW() " +
        "WHERE reservation_id = ( " +
        "SELECT reservation_id FROM payments " +
        "WHERE payment_id = #{paymentId}) " +
        "AND status = 'CONFIRMED' " +
        "AND check_in_date < CURDATE()"
    })
    int cancelPayment(@Param("paymentId") int paymentId);

    /** ✅ 특정 결제 조회 (결제 ID 기준) */
    @Select("SELECT * " +
            "FROM payments " +
            "WHERE payment_id = #{paymentId}")
    @Results(id = "paymentResultMap", value = {
        @Result(property = "paymentId", column = "payment_id"),
        @Result(property = "reservationId", column = "reservation_id"),
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "amount", column = "amount"),
        @Result(property = "status", column = "status"),
        @Result(property = "paymentDate", column = "payment_date"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    Payment selectPayment(@Param("paymentId") int paymentId);

    /** ✅ 특정 예약의 결제 내역 조회 */
    @Select("SELECT * " +
            "FROM payments " +
            "WHERE reservation_id = #{reservationId}")
    @ResultMap("paymentResultMap")
    List<Payment> selectByReservationId(@Param("reservationId") int reservationId);

    /** ✅ 특정 회원의 결제 내역 조회 */
    @Select("SELECT * " +
            "FROM payments " +
            "WHERE member_id = #{memberId} " +
            "ORDER BY payment_date DESC")
    @ResultMap("paymentResultMap")
    List<Payment> selectByMemberId(@Param("memberId") int memberId);

    /** ✅ 전체 결제 목록 조회 */
    @Select("SELECT * " +
            "FROM payments " +
            "ORDER BY reg_date DESC")
    @ResultMap("paymentResultMap")
    List<Payment> selectAllPayments();

    /** ✅ 전체 결제 건수 조회 */
    @Select("SELECT COUNT(*) " +
            "FROM payments")
    int selectPaymentCount();
}
