package kr.project.yuju.services;

import kr.project.yuju.models.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    /** ✅ 예약 추가 테스트 */
    @Test
    @DisplayName("예약 추가 테스트")
    void addReservation() throws Exception {
        // 예약을 추가하면서 직접 테스트 데이터 입력
        Reservation input = new Reservation();
        input.setMemberId(1);
        input.setRoomId(3);
        input.setCheckInDate(LocalDate.of(2025, 2, 25));
        input.setCheckOutDate(LocalDate.of(2025, 2, 27));
        input.setTotalPrice(300000);

        Reservation output = reservationService.addReservation(input);
        log.debug("예약 추가 결과: {}", output);

        // ✅ 추가된 예약 ID로 바로 조회하여 검증
        Reservation reservation = reservationService.getReservationById(output.getReservationId());
        log.debug("예약 조회 결과: {}", reservation);
    }


    /** ✅ 회원 ID로 예약 조회 테스트 */
    @Test
    @DisplayName("회원 예약 조회 테스트")
    void getReservationsByMemberId() throws Exception {
        List<Reservation> reservations = reservationService.getReservationsByMemberId(1);
        log.debug("회원 예약 목록: {}", reservations);
    }

    /** ✅ 객실 ID로 예약 조회 테스트 */
    @Test
    @DisplayName("객실 예약 조회 테스트")
    void getReservationsByRoomId() throws Exception {
        List<Reservation> reservations = reservationService.getReservationsByRoomId(3);
        log.debug("객실 예약 목록: {}", reservations);
    }

    /** ✅ 전체 예약 개수 조회 테스트 */
    @Test
    @DisplayName("전체 예약 개수 조회 테스트")
    void getReservationCount() throws Exception {
        int count = reservationService.getReservationCount();
        log.debug("전체 예약 개수: {}", count);
    }
}
