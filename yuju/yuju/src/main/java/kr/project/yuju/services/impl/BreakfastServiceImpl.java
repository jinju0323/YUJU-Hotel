package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.BreakfastMapper;
import kr.project.yuju.models.Breakfast;
import kr.project.yuju.services.BreakfastService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BreakfastServiceImpl implements BreakfastService {

    @Autowired
    private BreakfastMapper breakfastMapper;

    /** ✅ 조식 추가 */
    @Override
    public Breakfast addBreakfast(Breakfast breakfast) throws Exception {
        try {
            int rows = breakfastMapper.insert(breakfast);
            if (rows == 0) {
                throw new Exception("조식이 추가되지 않았습니다.");
            }
            // 추가된 조식 정보 반환
            return breakfastMapper.selectItem(breakfast.getBreakfastId()); 
        } catch (Exception e) {
            log.error("조식 추가 실패", e);
            throw e;
        }
    }

    /** ✅ 조식 정보 수정 */
    @Override
    public int updateBreakfast(Breakfast breakfast) throws Exception {
        try {
            int rows = breakfastMapper.update(breakfast.getBreakfastId(), breakfast.getBreakfastPrice());
            if (rows == 0) {
                throw new Exception("조식 정보 수정에 실패했습니다.");
            }
            return rows;  // 수정된 행 수 반환
        } catch (Exception e) {
            log.error("조식 정보 수정 실패", e);
            throw e;
        }
    }

    /** ✅ 조식 삭제 */
    @Override
    public int deleteBreakfast(int breakfastId) throws Exception {
        try {
            int rows = breakfastMapper.delete(breakfastId);
            if (rows == 0) {
                throw new Exception("조식 삭제에 실패했습니다.");
            }
            return rows;  // 삭제된 행 수 반환
        } catch (Exception e) {
            log.error("조식 삭제 실패", e);
            throw e;
        }
    }

    /** ✅ 특정 조식 조회 */
    @Override
    public Breakfast getBreakfastById(int breakfastId) throws Exception {
        try {
            Breakfast breakfast = breakfastMapper.selectItem(breakfastId);
            if (breakfast == null) {
                throw new Exception("해당 조식 정보가 존재하지 않습니다.");
            }
            return breakfast;
        } catch (Exception e) {
            log.error("조식 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 모든 조식 목록 조회 */
    @Override
    public List<Breakfast> getAllBreakfasts() throws Exception {
        try {
            List<Breakfast> breakfasts = breakfastMapper.selectList();
            if (breakfasts == null || breakfasts.isEmpty()) {
                throw new Exception("조식 목록이 비어 있습니다.");
            }
            return breakfasts;
        } catch (Exception e) {
            log.error("전체 조식 목록 조회 실패", e);
            throw e;
        }
    }

    /** ✅ 전체 조식 건수 조회 */
    @Override
    public int getBreakfastCount() throws Exception {
        try {
            return breakfastMapper.selectCount();
        } catch (Exception e) {
            log.error("조식 건수 조회 실패", e);
            throw e;
        }
    }
}
