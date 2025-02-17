package kr.project.yuju.mappers;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.Inquiry;

@Mapper
public interface InquiryMapper {

    /**
     * 문의 데이터를 저장한다.
     * @param input
     * @return
     */
    @Insert("INSERT INTO inquiries (" + 
                "member_id, " + 
                "subject, " + 
                "message, " + 
                "status, " + 
                "reply, " + 
                "reg_date, " + 
                "edit_date" + 
            ") VALUES (" + 
                "#{memberId}, " + 
                "#{subject}, " + 
                "#{message}, " + 
                "#{status}, " + 
                "#{reply}, " + 
                "NOW(), " + 
                "NOW()" + 
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "inquiryId", keyColumn = "inquiry_id")
    public int insert(Inquiry input);

    /**
     * 문의 데이터를 수정한다.
     * @param input
     * @return
     */
    @Update("UPDATE inquiries SET " + 
                "subject = #{subject}, " + 
                "message = #{message}, " + 
                "status = #{status}, " + 
                "reply = #{reply}, " + 
                "edit_date = NOW() " + 
            "WHERE inquiry_id = #{inquiryId}")
    public int update(Inquiry input);

    /**
     * 문의 데이터를 삭제한다.
     * @param input
     * @return
     */
    @Delete("DELETE FROM inquiries " + 
            "WHERE inquiry_id = #{inquiryId}")
    public int delete(Inquiry input);

    /**
     * 특정 문의 정보를 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT * FROM inquiries " + 
            "WHERE inquiry_id = #{inquiryId}")
    @Results(id="inquiryResultMap", value={
        @Result(property="inquiryId", column="inquiry_id"),
        @Result(property="memberId", column="member_id"),
        @Result(property="subject", column="subject"),
        @Result(property="message", column="message"),
        @Result(property="status", column="status"),
        @Result(property="reply", column="reply"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public Inquiry selectItem(Inquiry input);

    /**
     * 문의 목록을 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT * FROM inquiries")
    @ResultMap("inquiryResultMap")
    public List<Inquiry> selectList(Inquiry input);

    /**
     * 문의 수를 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT COUNT(*) FROM inquiries")
    public int selectCount(Inquiry input);
}
