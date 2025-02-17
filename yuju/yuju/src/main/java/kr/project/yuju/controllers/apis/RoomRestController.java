// package kr.project.yuju.controllers.rest;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import kr.project.yuju.models.Room;
// import kr.project.yuju.services.RoomService;

// @RestController
// @RequestMapping("/room")
// public class RoomRestController {

//     @Autowired
//     private RoomService roomService;

//     @GetMapping("/{roomId}")
//     public String getRoomDetails(int roomId, Model model) {
//         // ✅ 객실 정보 가져오기
//         Room room = roomService.getRoomById(roomId);
        
//         // ✅ 객실 이미지 리스트 가져오기
//         List<String> imageUrls = roomService.getRoomImages(roomId);

//         // ✅ 모델에 추가 (Thymeleaf에서 사용 가능)
//         model.addAttribute("room", room);
//         model.addAttribute("imageUrls", imageUrls);

//         return "room"; // room.html 반환
//     }
// }

