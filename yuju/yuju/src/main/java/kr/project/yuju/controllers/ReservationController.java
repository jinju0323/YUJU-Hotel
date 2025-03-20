package kr.project.yuju.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.project.yuju.models.Room;
import kr.project.yuju.models.RoomImg;
import kr.project.yuju.services.RoomImgService;
import kr.project.yuju.services.RoomService;
import kr.project.yuju.helpers.FileHelper;

@Controller
public class ReservationController {

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomImgService  roomImgService;

    @Autowired
    private FileHelper fileHelper;


    // @GetMapping("/reservation")
    // public String reservation(Model model) throws Exception {
    //     List<Room> allRooms = roomService.getList();

    //     Set<Integer> uniqueRoomIds = new HashSet<>();
    //     List<Room> uniqueRooms = new ArrayList<>();
    //     Map<Integer, String> roomImageMap = new HashMap<>(); // ✅ 객실 ID별 대표 이미지 저장

    //     for (Room room : allRooms) {
    //         if (uniqueRoomIds.add(room.getRoomId())) {
    //             uniqueRooms.add(room);

    //             try {
    //                 // ✅ 객실 대표 이미지 조회
    //                 RoomImg mainImage = roomImgService.getMainImage(room.getRoomId());
    //                 roomImageMap.put(room.getRoomId(), fileHelper.getUrl(mainImage.getImgUrl()));
    //             } catch (Exception e) {
    //                 // ✅ 로그만 남기고 예외 처리 (대표 이미지가 없을 경우)
    //                 log.error("객실 ID {}의 대표 이미지를 불러오지 못했습니다: {}", room.getRoomId(), e.getMessage());
    //                 throw new RuntimeException("객실 대표 이미지 조회 실패: " + room.getRoomId(), e);
    //             }                
    //         }
    //     }

    //     List<Room> standardRooms = new ArrayList<>();
    //     List<Room> deluxeRooms = new ArrayList<>();
    //     List<Room> suiteRooms = new ArrayList<>();

    //     for (Room room : uniqueRooms) {
    //         if (room.getRoomType().equalsIgnoreCase("Standard")) {
    //             standardRooms.add(room);
    //         } else if (room.getRoomType().equalsIgnoreCase("Deluxe")) {
    //             deluxeRooms.add(room);
    //         } else if (room.getRoomType().equalsIgnoreCase("Suite")) {
    //             suiteRooms.add(room);
    //         }
    //     }

    //     model.addAttribute("standardRooms", standardRooms);
    //     model.addAttribute("deluxeRooms", deluxeRooms);
    //     model.addAttribute("suiteRooms", suiteRooms);
    //     model.addAttribute("allRooms", uniqueRooms);
    //     model.addAttribute("roomImageMap", roomImageMap); // ✅ 대표 이미지 맵 추가

    //     return "reservation";
    // }
    @GetMapping("/reservation")
    public String reservation(Model model) {
        try {
            List<Room> allRooms = roomService.getList();

            Set<Integer> uniqueRoomIds = new HashSet<>();
            List<Room> uniqueRooms = new ArrayList<>();
            Map<Integer, String> roomImageMap = new HashMap<>(); // 객실 ID별 대표 이미지 저장

            for (Room room : allRooms) {
                if (uniqueRoomIds.add(room.getRoomId())) {
                    uniqueRooms.add(room);

                    try {
                        // 객실 대표 이미지 조회
                        RoomImg mainImage = roomImgService.getMainImage(room.getRoomId());
                        roomImageMap.put(room.getRoomId(), fileHelper.getUrl(mainImage.getImgUrl()));
                    } catch (Exception e) {
                        // 이미지 불러오기 실패 시 로그만 남기고, 콘솔에 출력
                        log.error("객실 ID {}의 대표 이미지를 불러오지 못했습니다: {}", room.getRoomId(), e.getMessage());
                    }
                }
            }

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

            // 모델에 객실 정보 추가
            model.addAttribute("standardRooms", standardRooms);
            model.addAttribute("deluxeRooms", deluxeRooms);
            model.addAttribute("suiteRooms", suiteRooms);
            model.addAttribute("allRooms", uniqueRooms);
            model.addAttribute("roomImageMap", roomImageMap); // 대표 이미지 맵 추가

            return "reservation";
        } catch (Exception e) {
            // 전체적인 예외 처리: 콘솔에 에러 원인 출력
            log.error("예약 페이지 로딩 중 오류 발생: {}", e.getMessage());
            e.printStackTrace(); // 콘솔에 자세한 에러 스택 트레이스를 출력
            return "reservation"; // 에러 페이지가 아니라 그냥 예약 페이지를 다시 로드
        }
    }



    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }
}
