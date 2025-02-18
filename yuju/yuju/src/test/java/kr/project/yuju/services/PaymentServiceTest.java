package kr.project.yuju.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    /** ✅ 결제 추가 및 조회 테스트 */
    @Test
    @DisplayName("결제 추가 및 조회 테스트")
    void addAndGetPayment() throws Exception {
        // 1️⃣ 새로운 결제 추가
        Payment input = new Payment();
        input.setReservationId(2);
        input.setMemberId(1);
        input.setAmount(300000);

        Payment output = paymentService.addPayment(input);
        log.debug("추가된 결제: {}", output);

        // 2️⃣ 추가한 결제 조회
        Payment retrievedPayment = paymentService.getPaymentById(input.getPaymentId());
        log.debug("조회된 결제: {}", retrievedPayment);
    }

    /** ✅ 결제 완료 처리 테스트 */
    @Test
    @DisplayName("결제 완료 처리 테스트")
    void completePayment() throws Exception {
        Payment input = new Payment();
        input.setPaymentId(2); // 결제 ID (테스트용)
        
        int output = paymentService.completePayment(input.getPaymentId());
        log.debug("결제 완료 처리 결과: {}", output);
    }

    /** ✅ 특정 예약의 결제 내역 조회 테스트 */
    @Test
    @DisplayName("특정 예약의 결제 내역 조회 테스트")
    void getPaymentsByReservationId() throws Exception {
        Payment input = new Payment();
        input.setReservationId(2); // 예약 ID (테스트용)

        List<Payment> output = paymentService.getPaymentsByReservationId(input.getReservationId());
        log.debug("예약의 결제 내역: {}", output);
    }

    /** ✅ 특정 회원의 결제 내역 조회 테스트 */
    @Test
    @DisplayName("회원 결제 내역 조회 테스트")
    void getPaymentsByMemberId() throws Exception {
        Payment input = new Payment();
        input.setMemberId(1); // 회원 ID (테스트용)

        List<Payment> output = paymentService.getPaymentsByMemberId(input.getMemberId());
        log.debug("회원의 결제 내역: {}", output);
    }

    /** ✅ 전체 결제 목록 조회 테스트 */
    @Test
    @DisplayName("전체 결제 목록 조회 테스트")
    void getAllPayments() throws Exception {
        List<Payment> output = paymentService.getAllPayments();
        log.debug("전체 결제 목록: {}", output);
    }

    /** ✅ 전체 결제 건수 조회 테스트 */
    @Test
    @DisplayName("전체 결제 건수 조회 테스트")
    void getPaymentCount() throws Exception {
        int output = paymentService.getPaymentCount();
        log.debug("전체 결제 건수: {}", output);
    }

    /** ✅ 결제 취소 테스트 */
    @Test
    @DisplayName("결제 취소 테스트")
    void cancelPayment() throws Exception {
        Payment input = new Payment();
        input.setPaymentId(2); // 결제 ID (테스트용)

        int output = paymentService.cancelPayment(input.getPaymentId());
        log.debug("결제 취소 결과: {}", output);
    }
}
