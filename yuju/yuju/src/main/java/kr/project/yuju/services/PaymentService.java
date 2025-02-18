package kr.project.yuju.services;

import java.util.List;
import kr.project.yuju.models.Payment;

public interface PaymentService {

    /** ✅ 결제 추가 */
    Payment addPayment(Payment payment) throws Exception;

    /** ✅ 결제 완료 */
    int completePayment(int paymentId) throws Exception;

    /** ✅ 결제 취소 */
    int cancelPayment(int paymentId) throws Exception;

    /** ✅ 특정 결제 조회 */
    Payment getPaymentById(int paymentId) throws Exception;

    /** ✅ 특정 예약의 결제 내역 조회 */
    List<Payment> getPaymentsByReservationId(int reservationId) throws Exception;

    /** ✅ 특정 회원의 결제 내역 조회 */
    List<Payment> getPaymentsByMemberId(int memberId) throws Exception;

    /** ✅ 전체 결제 목록 조회 */
    List<Payment> getAllPayments() throws Exception;

    /** ✅ 전체 결제 건수 조회 */
    int getPaymentCount() throws Exception;
}
