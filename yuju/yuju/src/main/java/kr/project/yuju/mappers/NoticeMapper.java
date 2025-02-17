package kr.project.yuju.mappers;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Notice;

@Mapper
public interface NoticeMapper {

    /**
     * 공지사항 데이터를 저장한다. (관리자만 가능)
     * @param input
     * @return
     */
    @Insert(
        "INSERT INTO notices (member_id, title, content, reg_date, edit_date)\n" +
        "SELECT #{memberId}, #{title}, #{content}, NOW(), NOW()\n" +
        "FROM members WHERE member_id = #{memberId} AND admin = 'Y'"
    )
    @Options(useGeneratedKeys = true, keyProperty = "noticeId", keyColumn = "notice_id")
    public int insert(Notice input);

    /**
     * 공지사항 데이터를 수정한다. (관리자만 가능)
     * @param input
     * @return
     */
    @Update(
        "UPDATE notices SET title = #{title}, content = #{content}, edit_date = NOW()\n" +
        "WHERE notice_id = #{noticeId}\n" +
        "AND member_id = #{memberId}\n" +
        "AND EXISTS (SELECT 1 FROM members WHERE member_id = #{memberId} AND admin = 'Y')"
    )
    public int update(Notice input);

    /**
     * 공지사항 데이터를 삭제한다. (관리자만 가능)
     * @param input
     * @return
     */
    @Delete(
        "DELETE FROM notices\n" +
        "WHERE notice_id = #{noticeId}\n" +
        "AND member_id = #{memberId}\n" +
        "AND EXISTS (SELECT 1 FROM members WHERE member_id = #{memberId} AND admin = 'Y')"
    )
    public int delete(Notice input);

    /**
     * 특정 공지사항 정보를 조회한다. (사용자 & 관리자 가능)
     * @param input
     * @return
     */
    @Select(
        "SELECT * FROM notices WHERE notice_id = #{noticeId}"
    )
    @Results(id="noticeResultMap", value={
        @Result(property="noticeId", column="notice_id"),
        @Result(property="memberId", column="member_id"),
        @Result(property="title", column="title"),
        @Result(property="content", column="content"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public Notice selectItem(Notice input);

    /**
     * 공지사항 목록을 조회한다. (사용자 & 관리자 가능)
     * @param input
     * @return
     */
    @Select("SELECT * FROM notices")
    @ResultMap("noticeResultMap")
    public List<Notice> selectList(Notice input);

    /**
     * 공지사항 수를 조회한다. (사용자 & 관리자 가능)
     * @param input
     * @return
     */
    @Select("SELECT COUNT(*) FROM notices")
    public int selectCount(Notice input);
}