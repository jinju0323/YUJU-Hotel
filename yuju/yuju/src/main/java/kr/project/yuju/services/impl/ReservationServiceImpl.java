package kr.project.yuju.services.impl;

import kr.project.yuju.mappers.ReservationMapper;
import kr.project.yuju.models.Reservation;
import kr.project.yuju.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    /** ✅ 예약 추가 */
    @Override
    public Reservation addReservation(Reservation reservation) throws Exception {
        try {
            int rows = reservationMapper.insertReservation(reservation);
            if (rows == 0) {
                throw new Exception("예약 실패: 저장된 데이터가 없습니다.");
            }
            return reservationMapper.selectReservation(reservation.getReservationId());
        } catch (Exception e) {
            log.error("예약 추가 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 예약 정보 조회 */
    @Override
    public Reservation getReservationById(int reservationId) throws Exception {
        try {
            Reservation reservation = reservationMapper.selectReservation(reservationId);
            if (reservation == null) {
                throw new Exception("예약 조회 실패: 해당 예약이 존재하지 않습니다.");
            }
            return reservation;
        } catch (Exception e) {
            log.error("예약 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 회원 ID로 예약 조회 */
    @Override
    public List<Reservation> getReservationsByMemberId(int memberId) throws Exception {
        try {
            return reservationMapper.selectByMemberId(memberId);
        } catch (Exception e) {
            log.error("회원 예약 목록 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 객실 ID로 예약 조회 */
    @Override
    public List<Reservation> getReservationsByRoomId(int roomId) throws Exception {
        try {
            return reservationMapper.selectByRoomId(roomId);
        } catch (Exception e) {
            log.error("객실 예약 목록 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 전체 예약 개수 조회 */
    @Override
    public int getReservationCount() throws Exception {
        try {
            return reservationMapper.selectCount();
        } catch (Exception e) {
            log.error("전체 예약 개수 조회 실패", e);
            throw e;
        }
    }
}
