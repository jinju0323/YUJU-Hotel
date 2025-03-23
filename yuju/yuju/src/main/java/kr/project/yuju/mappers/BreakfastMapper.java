package kr.project.yuju.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Breakfast;

@Mapper
public interface BreakfastMapper {

    // 조식 데이터 삽입
    @Insert("INSERT INTO breakfasts (breakfast_price, reg_date, edit_date) " +
            "VALUES (#{breakfastPrice}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "breakfastId", keyColumn = "breakfast_id")
    public int insert(Breakfast breakfast);

    // 조식 데이터 업데이트
    @Update("UPDATE breakfasts SET breakfast_price = #{breakfastPrice}, edit_date = NOW() " +
            "WHERE breakfast_id = #{breakfastId}")
    public int update(@Param("breakfastId") int breakfastId, @Param("breakfastPrice") int breakfastPrice);

    // 조식 데이터 삭제
    @Delete("DELETE FROM breakfasts WHERE breakfast_id = #{breakfastId}")
    public int delete(@Param("breakfastId") int breakfastId);

    // 특정 조식 데이터 조회
    @Select("SELECT breakfast_id, breakfast_price, reg_date, edit_date " +
            "FROM breakfasts WHERE breakfast_id = #{breakfastId}")
    @Results(id="breakfastResultMap", value={
        @Result(property="breakfastId", column="breakfast_id"),
        @Result(property="breakfastPrice", column="breakfast_price"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public Breakfast selectItem(@Param("breakfastId") int breakfastId);

    // 모든 조식 데이터 조회
    @Select("SELECT breakfast_id, breakfast_price, reg_date, edit_date FROM breakfasts")
    @ResultMap("breakfastResultMap")
    public List<Breakfast> selectList();

    // 전체 조식 데이터 수 조회
    @Select("SELECT COUNT(*) FROM breakfasts")
    public int selectCount();
}
