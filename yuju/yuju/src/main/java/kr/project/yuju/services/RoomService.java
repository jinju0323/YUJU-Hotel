package kr.project.yuju.services;
import java.util.List;
import kr.project.yuju.models.Room;


public interface RoomService {
    public Room addItem(Room params) throws Exception;

    public Room editItem(Room params) throws Exception;

    public int deleteItem(Room params) throws Exception;

    public Room getItem(Room params) throws Exception;

    public List<Room> getList(Room params) throws Exception;

    public int getCount(Room params) throws Exception;
}
