package kr.project.yuju.mappers;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Reservation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReservationMapperTest {
    @Autowired
    private ReservationMapper reservationMapper;

    /** ✅ 예약 추가 테스트 */
    @Test
    @DisplayName("예약 추가 테스트")
    void insertReservation() {
        Reservation input = new Reservation();
        input.setMemberId(1);  // 테스트용 회원 ID
        input.setRoomId(2);     // 테스트용 객실 ID
        input.setCheckInDate(LocalDate.of(2025, 3, 1));
        input.setCheckOutDate(LocalDate.of(2025, 3, 5));
        input.setTotalPrice(600000);  // 1박 15만원 * 4일
        input.setStatus("대기중"); // 기본 상태 PENDING

        int output = reservationMapper.insertReservation(input);

        log.debug("output: {}", output);
    }

    /** ✅ 특정 예약 조회 테스트 */
    @Test
    @DisplayName("특정 예약 조회 테스트")
    void selectReservation() {
        Reservation input = new Reservation();
        input.setReservationId(1);
        
        Reservation output = reservationMapper.selectReservation(input.getReservationId());
        log.debug("output:{}", output);
    }

    /** ✅ 회원 ID로 예약 목록 조회 테스트 */
    @Test
    @DisplayName("회원의 예약 목록 조회 테스트")
    void selectByMemberId() {
        List<Reservation> output = reservationMapper.selectByMemberId(1);
        log.debug("output: {}", output);
    }

    /** ✅ 객실 ID로 예약 목록 조회 테스트 */
    @Test
    @DisplayName("객실의 예약 목록 조회 테스트")
    void selectByRoomId() {
        List<Reservation> output = reservationMapper.selectByRoomId(2);
        log.debug("output: {}", output);
    }

    /** ✅ 총 예약 개수 조회 테스트 */
    @Test
    @DisplayName("전체 예약 개수 조회 테스트")
    void selectCountReservation() {
        int output = reservationMapper.selectCount();
        log.debug("output: {}", output);
    }
}
