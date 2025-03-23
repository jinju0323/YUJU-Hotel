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
     * 매일 새벽 4시에 탈퇴 회원 실제 삭제
     */
    // @Scheduled(cron = "0 */1 * * * ?") // 테스트 시간을 1분 마다로 변경
    @Scheduled(cron = "0 0 4 * * ?")
    public void deleteOutMembers() {
        log.debug("⏰ [스케쥴러] 탈퇴 회원 정리 시작");

        try {
            List<Member> deletedList = memberService.deleteOutMembers();

            if (deletedList == null || deletedList.isEmpty()) {
                log.debug("✅ 삭제할 탈퇴 회원이 없습니다 :)");
                return;
            }

            log.debug("🗑️ 총 {}명의 회원이 DB에서 삭제되었습니다.", deletedList.size());

        } catch (Exception e) {
            log.error("❌ 탈퇴 회원 삭제 중 오류 발생", e);
        }
    }
}
