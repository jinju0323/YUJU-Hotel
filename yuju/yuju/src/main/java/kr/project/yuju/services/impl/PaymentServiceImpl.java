package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.PaymentMapper;
import kr.project.yuju.models.Payment;
import kr.project.yuju.services.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    /** ✅ 결제 추가 */
    @Override
    public Payment addPayment(Payment payment) throws Exception {
        try {
            int rows = paymentMapper.insertPayment(payment);
            if (rows == 0) {
                throw new Exception("결제가 추가되지 않았습니다.");
            }
            return paymentMapper.selectPayment(payment.getPaymentId());
        } catch (Exception e) {
            log.error("결제 추가 실패", e);
            throw e;
        }
    }

    /** ✅ 결제 완료 */
    @Override
    public int completePayment(int paymentId) throws Exception {
        try {
            int rows1 = paymentMapper.completePayment(paymentId);
            int rows2 = paymentMapper.completeReservation(paymentId);
            if (rows1 == 0 || rows2 == 0) {
                throw new Exception("결제 완료 처리 실패");
            }
            return rows1;
        } catch (Exception e) {
            log.error("결제 완료 처리 실패", e);
            throw e;
        }
    }

    /** ✅ 결제 취소 */
    @Override
    public int cancelPayment(int paymentId) throws Exception {
        try {
            int rows1 = paymentMapper.cancelPayment(paymentId);
            int rows2 = paymentMapper.cancelReservation(paymentId);
            if (rows1 == 0 || rows2 == 0) {
                throw new Exception("결제 취소 처리 실패");
            }
            return rows1;
        } catch (Exception e) {
            log.error("결제 취소 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 결제 조회 */
    @Override
    public Payment getPaymentById(int paymentId) throws Exception {
        try {
            Payment payment = paymentMapper.selectPayment(paymentId);
            if (payment == null) {
                throw new Exception("해당 결제가 존재하지 않습니다.");
            }
            return payment;
        } catch (Exception e) {
            log.error("결제 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 예약의 결제 내역 조회 */
    @Override
    public List<Payment> getPaymentsByReservationId(int reservationId) throws Exception {
        try {
            List<Payment> payments = paymentMapper.selectByReservationId(reservationId);
            if (payments == null || payments.isEmpty()) {
                throw new Exception("해당 예약의 결제 내역이 존재하지 않습니다.");
            }
            return payments;
        } catch (Exception e) {
            log.error("예약의 결제 내역 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 회원의 결제 내역 조회 */
    @Override
    public List<Payment> getPaymentsByMemberId(int memberId) throws Exception {
        try {
            List<Payment> payments = paymentMapper.selectByMemberId(memberId);
            if (payments == null || payments.isEmpty()) {
                throw new Exception("해당 회원의 결제 내역이 존재하지 않습니다.");
            }
            return payments;
        } catch (Exception e) {
            log.error("회원의 결제 내역 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 전체 결제 목록 조회 */
    @Override
    public List<Payment> getAllPayments() throws Exception {
        try {
            List<Payment> payments = paymentMapper.selectAllPayments();
            if (payments == null || payments.isEmpty()) {
                throw new Exception("결제 내역이 존재하지 않습니다.");
            }
            return payments;
        } catch (Exception e) {
            log.error("전체 결제 목록 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 전체 결제 건수 조회 */
    @Override
    public int getPaymentCount() throws Exception {
        try {
            return paymentMapper.selectPaymentCount();
        } catch (Exception e) {
            log.error("전체 결제 건수 조회 실패", e);
            throw e;
        }
    }
}
