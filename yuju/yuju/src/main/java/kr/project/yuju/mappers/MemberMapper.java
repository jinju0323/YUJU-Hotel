package kr.project.yuju.mappers;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Member;

@Mapper
public interface MemberMapper {

    /**
     * 회원 데이터를 저장한다.
     * @param input
     * @return
     */
    @Insert("INSERT INTO members (" + 
                "user_name, " + 
                "user_id, " + 
                "user_pw, " + 
                "admin, " + 
                "reg_date, " + 
                "edit_date" + 
            ") VALUES (" + 
                "#{userName}, " + 
                "#{userId}, " + 
                "#{userPw}, " + 
                "#{admin}, " + 
                "NOW(), " + 
                "NOW()" + 
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "memberId", keyColumn = "member_id")
    public int insert(Member input);

    /**
     * 회원 데이터를 수정한다.
     * @param input
     * @return
     */
    @Update("UPDATE members SET " + 
                "user_name = #{userName}, " + 
                "user_pw = #{userPw}, " + 
                "admin = #{admin}, " + 
                "edit_date = NOW() " + 
            "WHERE member_id = #{memberId}")
    public int update(Member input);

    /**
     * 회원 데이터를 삭제한다.
     * @param input
     * @return
     */
    @Delete("DELETE FROM members " + 
            "WHERE member_id = #{memberId}")
    public int delete(Member input);

    /**
     * 특정 회원 정보를 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT * FROM members " + 
            "WHERE member_id = #{memberId}")
    @Results(id="memberResultMap", value={
        @Result(property="memberId", column="member_id"),
        @Result(property="userName", column="user_name"),
        @Result(property="userId", column="user_id"),
        @Result(property="userPw", column="user_pw"),
        @Result(property="admin", column="admin"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public Member selectItem(Member input);

    /**
     * 회원 목록을 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT * FROM members")
    @ResultMap("memberResultMap")
    public List<Member> selectList(Member input);

    /**
     * 회원 수를 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT COUNT(*) FROM members")
    public int selectCount(Member input);
}
