package kr.project.yuju.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.project.yuju.models.Member;
import kr.project.yuju.services.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class AccountScheduler {

    @Autowired
    private MemberService memberService;

    /**
     * 🕓 매일 새벽 4시에 탈퇴 회원 정리 스케줄러
     * - 탈퇴 상태(`is_out = 'Y'`)로 30일 이상 지난 회원을 DB에서 삭제
     * - 테스트 시에는 주석 처리된 1분 간격 스케줄을 활성화하여 테스트 가능
     */
    // @Scheduled(cron = "0 */1 * * * ?") // 테스트용: 1분마다 실행
    @Scheduled(cron = "0 0 4 * * ?") // 운영용: 매일 새벽 4시에 실행
    public void deleteOutMembers() {
        log.debug("⏰ [스케쥴러] 탈퇴 회원 정리 시작");

        try {
            // ✅ [1] 탈퇴 회원 삭제 처리
            List<Member> deletedList = memberService.deleteOutMembers();

            // ✅ [2] 삭제할 탈퇴 회원이 없는 경우
            if (deletedList == null || deletedList.isEmpty()) {
                log.debug("✅ 삭제할 탈퇴 회원이 없습니다 :)");
                return;
            }

            // ✅ [3] 삭제된 회원 수 로그 출력
            log.debug("🗑️ 총 {}명의 회원이 DB에서 삭제되었습니다.", deletedList.size());

        } catch (Exception e) {
            // ❌ [4] 예외 발생 시 로그 출력
            log.error("❌ 탈퇴 회원 삭제 중 오류 발생", e);
        }
    }
}