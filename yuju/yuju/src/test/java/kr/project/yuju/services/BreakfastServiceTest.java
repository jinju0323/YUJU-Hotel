package kr.project.yuju.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Breakfast;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class BreakfastServiceTest {

    @Autowired
    private BreakfastService breakfastService;

    /** ✅ 조식 추가 테스트 */
    @Test
    @DisplayName("조식 추가 테스트")
    void addBreakfast() throws Exception {
        Breakfast input = new Breakfast();
        input.setBreakfastPrice(30000);

        Breakfast output = breakfastService.addBreakfast(input);
        log.debug("추가된 조식: {}", output);

        assertNotNull(output, "조식 추가 실패");
        assertEquals(input.getBreakfastPrice(), output.getBreakfastPrice(), "조식 가격이 일치하지 않습니다.");
    }

    /** ✅ 조식 가격 수정 테스트 */
    @Test
    @DisplayName("조식 가격 수정 테스트")
    void updateBreakfast() throws Exception {
        Breakfast input = new Breakfast();
        input.setBreakfastId(1); // 수정할 조식 ID
        input.setBreakfastPrice(35000); // 새로운 가격

        int output = breakfastService.updateBreakfast(input);
        log.debug("수정된 조식 가격: {}", output);

        assertTrue(output > 0, "조식 가격 수정 실패");
    }

    /** ✅ 조식 삭제 테스트 */
    @Test
    @DisplayName("조식 삭제 테스트")
    void deleteBreakfast() throws Exception {
        int breakfastId = 1; // 삭제할 조식의 ID

        int output = breakfastService.deleteBreakfast(breakfastId);
        log.debug("삭제된 조식 ID: {}", breakfastId);

        assertTrue(output > 0, "조식 삭제 실패: 존재하지 않는 조식 ID");
    }

    /** ✅ 특정 조식 조회 테스트 */
    @Test
    @DisplayName("특정 조식 조회 테스트")
    void getBreakfastById() throws Exception {
        int breakfastId = 1; // 조회할 조식 ID
        Breakfast output = breakfastService.getBreakfastById(breakfastId);

        log.debug("조회된 조식: {}", output);
        assertNotNull(output, "조식 조회 실패: 존재하지 않는 조식 ID");
    }

    /** ✅ 모든 조식 목록 조회 테스트 */
    @Test
    @DisplayName("모든 조식 목록 조회 테스트")
    void getAllBreakfasts() throws Exception {
        List<Breakfast> output = breakfastService.getAllBreakfasts();

        log.debug("조식 목록 조회 결과: {}", output);
        assertFalse(output.isEmpty(), "조식 목록이 비어 있습니다.");
    }

    /** ✅ 전체 조식 건수 조회 테스트 */
    @Test
    @DisplayName("전체 조식 건수 조회 테스트")
    void getBreakfastCount() throws Exception {
        int count = breakfastService.getBreakfastCount();

        log.debug("전체 조식 개수: {}", count);
        assertTrue(count >= 0, "조식 개수 조회 오류");
    }
}
