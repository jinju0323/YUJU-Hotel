package kr.project.yuju.mappers;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kr.project.yuju.models.InquiryFile;

@Mapper
public interface InquiryFileMapper {

    /**
     * 문의 파일 데이터를 저장한다.
     * @param input
     * @return
     */
    @Insert("INSERT INTO inquiry_files (" + 
                "inquiry_id, " + 
                "file_name, " + 
                "file_path, " + 
                "reg_date, " + 
                "edit_date" + 
            ") VALUES (" + 
                "#{inquiryId}, " + 
                "#{fileName}, " + 
                "#{filePath}, " + 
                "NOW(), " + 
                "NOW()" + 
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "fileId", keyColumn = "file_id")
    public int insert(InquiryFile input);

    /**
     * 문의 파일 데이터를 수정한다.
     * @param input
     * @return
     */
    @Update("UPDATE inquiry_files SET " + 
                "file_name = #{fileName}, " + 
                "file_path = #{filePath}, " + 
                "edit_date = NOW() " + 
            "WHERE file_id = #{fileId}")
    public int update(InquiryFile input);

    /**
     * 문의 파일 데이터를 삭제한다.
     * @param input
     * @return
     */
    @Delete("DELETE FROM inquiry_files " + 
            "WHERE file_id = #{fileId}")
    public int delete(InquiryFile input);

    /**
     * 특정 문의 파일 정보를 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT * FROM inquiry_files " + 
            "WHERE file_id = #{fileId}")
    @Results(id="inquiryFileResultMap", value={
        @Result(property="fileId", column="file_id"),
        @Result(property="inquiryId", column="inquiry_id"),
        @Result(property="fileName", column="file_name"),
        @Result(property="filePath", column="file_path"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public InquiryFile selectItem(InquiryFile input);

    /**
     * 문의 파일 목록을 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT * FROM inquiry_files WHERE inquiry_id = #{inquiryId}")
    @ResultMap("inquiryFileResultMap")
    public List<InquiryFile> selectList(InquiryFile input);

    /**
     * 문의 파일 수를 조회한다.
     * @param input
     * @return
     */
    @Select("SELECT COUNT(*) FROM inquiry_files")
    public int selectCount(InquiryFile input);
}
