package kr.project.yuju.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;


    /** ✅ 결제 테스트 */
    @Test
    @DisplayName("결제 테스트")
    void insertPayment() {
        Payment input = new Payment();
        input.setReservationId(1);
        input.setMemberId(1);
        input.setAmount(600000);

        int output = paymentMapper.insertPayment(input);
        log.debug("output: {}", output);
    }

    /** ✅ 결제 완료 테스트 */
    @Test
    @DisplayName("결제 완료 테스트")
    void completePayment() {
        int paymentId = 1; // 테스트할 결제 ID
        int output = paymentMapper.completePayment(paymentId);
        log.debug("output: {}", output);

    }
    /** ✅ 예약확정 테스트 */
    @Test
    @DisplayName("예약 확정 테스트")
    void completeReservation() {
        int paymentId = 1; // 테스트할 결제 ID
        int output = paymentMapper.completeReservation(paymentId);
        log.debug("output: {}", output);

    }

    /** ✅ 결제 취소 테스트 */
    @Test
    @DisplayName("결제 취소 테스트")
    void cancelPayment() {
        int paymentId = 1; // 테스트할 결제 ID
        int output = paymentMapper.cancelPayment(paymentId);
        log.debug("output: {}", output);
    }

    /** ✅ 예약 취소 테스트 */
    @Test
    @DisplayName("예약 취소 테스트")
    void cancelReservation() {
        int paymentId = 1; // 테스트할 결제 ID
        int output = paymentMapper.cancelReservation(paymentId);
        log.debug("output: {}", output);
    }

    /** ✅ 특정 결제 조회 테스트 */
    @Test
    @DisplayName("특정 결제 조회 테스트")
    void selectPayment() {
        int paymentId = 1; // 테스트할 결제 ID
        Payment output = paymentMapper.selectPayment(paymentId);

        log.debug("output: {}", output);
    }

    /** ✅ 특정 예약의 결제 내역 조회 테스트 */
    @Test
    @DisplayName("예약별 결제 내역 조회 테스트")
    void selectByReservationId() {
        int reservationId = 1;
        List<Payment> output = paymentMapper.selectByReservationId(reservationId);

        log.debug("output: {}", output);
    }

    /** ✅ 특정 회원의 결제 내역 조회 테스트 */
    @Test
    @DisplayName("회원별 결제 내역 조회 테스트")
    void selectByMemberId() {
        int memberId = 1;
        List<Payment> output = paymentMapper.selectByMemberId(memberId);

        log.debug("output: {}", output);
    }

    /** ✅ 전체 결제 목록 조회 테스트 */
    @Test
    @DisplayName("전체 결제 목록 조회 테스트")
    void selectAllPayments() {
        List<Payment> output = paymentMapper.selectAllPayments();

        log.debug("output: {}", output);
    }

    /** ✅ 전체 결제 건수 조회 테스트 */
    @Test
    @DisplayName("전체 결제 건수 조회 테스트")
    void selectPaymentCount() {
        int output = paymentMapper.selectPaymentCount();
        log.debug("output: {}", output);

    }

}
