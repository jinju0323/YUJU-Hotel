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
                "is_out, " + 
                "is_admin, " + 
                "login_date, " + 
                "reg_date, " + 
                "edit_date" + 
            ") VALUES (" + 
                "#{userName}, " + 
                "#{userId}, " + 
                "#{userPw}, " + 
                "'N', " + 
                "'N', " + 
                "NOW(), " + 
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
                "is_out = #{isOut}, " + 
                "is_admin = #{isAdmin}, " + 
                "login_date = NOW(), " + 
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
    @Select("SELECT " + 
                "member_id, " + 
                "user_name, " + 
                "user_id, " + 
                "user_pw, " + 
                "is_out, " + 
                "is_admin, " + 
                "login_date, " + 
                "reg_date, " + 
                "edit_date " + 
            "FROM members " + 
            "WHERE user_id = #{userId}") // 아이디(이메일)로 조회
    @Results(id="memberResultMap", value={
        @Result(property="memberId", column="member_id"),
        @Result(property="userName", column="user_name"),
        @Result(property="userId", column="user_id"),
        @Result(property="userPw", column="user_pw"),
        @Result(property = "isOut", column = "is_out"),
        @Result(property = "isAdmin", column = "is_admin"),
        @Result(property = "loginDate", column = "login_date"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public Member selectItem(Member input);

    /**
     * 로그인 처리 - 회원탈퇴를 하지 않은 회원만 조회
     * @param input
     * @return
     */
    @Select("SELECT " + 
                "member_id, " + 
                "user_name, " + 
                "user_id, " + 
                "user_pw, " + 
                "is_out, " + 
                "is_admin, " + 
                "login_date, " + 
                "reg_date, " + 
                "edit_date " + 
            "FROM members " + 
            "WHERE user_id = #{userId} AND is_out = 'N'")
    @ResultMap("memberResultMap")
    public Member login(Member input);

    /**
     * 회원 목록을 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT " + 
                "member_id, " + 
                "user_name, " + 
                "user_id, " + 
                "user_pw, " + 
                "is_out, " + 
                "is_admin, " + 
                "login_date, " + 
                "reg_date, " + 
                "edit_date " + 
            "FROM members")
    @ResultMap("memberResultMap")
    public List<Member> selectList(Member input);

    /**
     * 회원가입 아이디(이메일) 중복 검사
     * @param input - 0: 중복 없음, 1: 중복 발생
     * @return
     */
    @Select("<script>" + 
            "SELECT COUNT(*) FROM members" + 
                "<where>" + 
                    "<if test='userId != null'>user_id = #{userId}</if>" +
                "</where>" +
            "</script>")
    public int selectCount(Member input);

    /**
     * 비밀번호와 일치하는지 확인한 후, 탈퇴 처리 (is_out = 'Y')
     * @param userId
     * @return
     */
    @Update("UPDATE members SET " + 
                "is_out = 'Y', " + 
                "edit_date = NOW() " +
            "WHERE " + 
                "user_id = #{userId} AND " + 
                "is_out = 'N'")
    public int updateMemberToOut(@Param("userId") String userId);

    /**
     * 삭제 대상인 탈퇴 회원 목록 조회 (삭제 전 확인용)
     */
    @Select("SELECT " + 
                "member_id, " + 
                "user_name, " + 
                "user_id, " + 
                "user_pw, " + 
                "is_out, " + 
                "is_admin, " + 
                "login_date, " + 
                "reg_date, " + 
                "edit_date " +  
            "FROM members " + 
            "WHERE " + 
                "is_out = 'Y' AND " + 
                "edit_date < DATE_SUB(NOW(), INTERVAL 30 DAY)")
    @ResultMap("memberResultMap")
    public List<Member> selectOutMembers(); 

    /**
     * 탈퇴한지 30일이 지난 회원 데이터를 삭제
     * @return
     */
    @Delete("DELETE FROM members " + 
                "WHERE is_out = 'Y' AND " + 
                "edit_date < DATE_SUB(NOW(), INTERVAL 30 DAY)")
    public int deleteOutMembers();


}
