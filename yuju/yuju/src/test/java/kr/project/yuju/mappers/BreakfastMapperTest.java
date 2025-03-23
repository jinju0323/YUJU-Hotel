package kr.project.yuju.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.yuju.models.Breakfast;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BreakfastMapperTest {

    @Autowired
    private BreakfastMapper breakfastMapper;

    /** ✅ 조식 추가 테스트 */
    @Test
    @DisplayName("조식 추가 테스트")
    void insertBreakfast() {
        Breakfast input = new Breakfast();
        input.setBreakfastPrice(30000); // 가격 설정

        int output = breakfastMapper.insert(input);
        log.debug("output: {}", output);
    }

    /** ✅ 조식 가격 업데이트 테스트 */
    @Test
    @DisplayName("조식 가격 업데이트 테스트")
    void updateBreakfast() {
        int breakfastId = 1; // 기존 조식 아이디
        int newPrice = 35000; // 새로운 가격

        int output = breakfastMapper.update(breakfastId, newPrice);
        log.debug("output: {}", output);
    }

    /** ✅ 조식 삭제 테스트 */
    @Test
    @DisplayName("조식 삭제 테스트")
    void deleteBreakfast() {
        int breakfastId = 1; // 삭제할 조식의 ID

        int output = breakfastMapper.delete(breakfastId);
        log.debug("output: {}", output);
    }

    /** ✅ 특정 조식 조회 테스트 */
    @Test
    @DisplayName("특정 조식 조회 테스트")
    void selectBreakfast() {
        int breakfastId = 1; // 조회할 조식 ID

        Breakfast output = breakfastMapper.selectItem(breakfastId);
        log.debug("output: {}", output);
    }

    /** ✅ 모든 조식 조회 테스트 */
    @Test
    @DisplayName("모든 조식 조회 테스트")
    void selectAllBreakfasts() {
        List<Breakfast> output = breakfastMapper.selectList();
        log.debug("output: {}", output);
    }

    /** ✅ 조식 수 조회 테스트 */
    @Test
    @DisplayName("조식 수 조회 테스트")
    void selectBreakfastCount() {
        int output = breakfastMapper.selectCount();
        log.debug("output: {}", output);
    }
}
