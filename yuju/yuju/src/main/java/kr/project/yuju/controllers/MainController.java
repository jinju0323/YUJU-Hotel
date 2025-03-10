package kr.project.yuju.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.project.yuju.helpers.FileHelper;
import kr.project.yuju.models.Room;
import kr.project.yuju.models.RoomImg;
import kr.project.yuju.services.RoomService;

@Controller
public class MainController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private FileHelper fileHelper;


    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/room")
    public String getRooms(@RequestParam(value = "room_id", defaultValue = "1") int roomId, Model model) throws Exception {
        Room selectedRoom = roomService.getItem(roomId);
        List<Room> allRooms = roomService.getList();

        // ✅ 중복 제거된 객실 리스트 생성 (room_id 기준으로 필터링)
        Set<Integer> uniqueRoomIds = new HashSet<>();
        List<Room> uniqueRooms = new ArrayList<>();

        for (Room room : allRooms) {
            if (uniqueRoomIds.add(room.getRoomId())) { 
                uniqueRooms.add(room);
            }
        }

        // ✅ 객실 유형별(A형, B형, C형)로 그룹화
        List<Room> standardRooms = new ArrayList<>();
        List<Room> deluxeRooms = new ArrayList<>();
        List<Room> suiteRooms = new ArrayList<>();

        for (Room room : uniqueRooms) {
            if (room.getRoomType().equalsIgnoreCase("Standard")) {
                standardRooms.add(room);
            } else if (room.getRoomType().equalsIgnoreCase("Deluxe")) {
                deluxeRooms.add(room);
            } else if (room.getRoomType().equalsIgnoreCase("Suite")) {
                suiteRooms.add(room);
            }
        }

        // ✅ 이미지 URL 변환
        if (selectedRoom != null && selectedRoom.getRoomImgs() != null) {
            for (RoomImg img : selectedRoom.getRoomImgs()) {
                img.setImgUrl(fileHelper.getUrl(img.getImgUrl())); // 이미지 경로 변환
            }
        }

        model.addAttribute("selectedRoom", selectedRoom);
        model.addAttribute("standardRooms", standardRooms);
        model.addAttribute("deluxeRooms", deluxeRooms);
        model.addAttribute("suiteRooms", suiteRooms);
        model.addAttribute("allRooms", uniqueRooms); // 중복 제거된 리스트 전달
        return "room"; 
    }

       
}
