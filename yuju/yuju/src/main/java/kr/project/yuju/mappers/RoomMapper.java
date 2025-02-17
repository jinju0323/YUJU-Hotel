package kr.project.yuju.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.project.yuju.models.Room;

@Mapper
public interface RoomMapper {
    @Insert("...")
    @Options(useGeneratedKeys = true, keyProperty = "...", keyColumn = "...")
    public int insert(Room input);

    @Update("...")
    public int update(Room input);

    @Delete("...")
    public int delete(Room input);

    @Select("...")
    @Results(id="resultMap", value={
        @Result(property="...", column="..."),
        @Result(property="...", column="..."),
        @Result(property="...", column="...")
    })
    public Room selectItem(Room input);

    @Select("...")
    @ResultMap("resultMap")
    public List<Room> selectList(Room input);

    @Select("...")
    public int selectCount(Room input);
}

