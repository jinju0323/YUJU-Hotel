package kr.project.yuju.services;

import java.util.List;
import kr.project.yuju.models.Breakfast;

public interface BreakfastService {

    /** ✅ 조식 추가 */
    Breakfast addBreakfast(Breakfast breakfast) throws Exception;

    /** ✅ 조식 정보 수정 */
    int updateBreakfast(Breakfast breakfast) throws Exception;

    /** ✅ 조식 삭제 */
    int deleteBreakfast(int breakfastId) throws Exception;

    /** ✅ 특정 조식 조회 */
    Breakfast getBreakfastById(int breakfastId) throws Exception;

    /** ✅ 모든 조식 목록 조회 */
    List<Breakfast> getAllBreakfasts() throws Exception;

    /** ✅ 전체 조식 건수 조회 */
    int getBreakfastCount() throws Exception;
}
