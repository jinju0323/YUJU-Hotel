package kr.project.yuju.services;

import kr.project.yuju.models.Reservation;
import java.util.List;

public interface ReservationService {
    
    /** ✅ 예약 추가 */
    Reservation addReservation(Reservation reservation) throws Exception;

    /** ✅ 특정 예약 정보 조회 */
    Reservation getReservationById(int reservationId) throws Exception;

    /** ✅ 회원 ID로 예약 조회 */
    List<Reservation> getReservationsByMemberId(int memberId) throws Exception;

    /** ✅ 객실 ID로 예약 조회 */
    List<Reservation> getReservationsByRoomId(int roomId) throws Exception;

    /** ✅ 전체 예약 개수 조회 */
    int getReservationCount() throws Exception;
}
