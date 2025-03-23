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

import kr.project.yuju.models.Breakfast;
import kr.project.yuju.models.Reservation;
import kr.project.yuju.models.Room;
import kr.project.yuju.models.RoomImg;
import kr.project.yuju.services.BreakfastService;
import kr.project.yuju.services.ReservationService;
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
    private ReservationService reservationService;

    @Autowired
    private BreakfastService breakfastService;

    @Autowired
    private FileHelper fileHelper;

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
    public String payment(Model model, int memberId) throws Exception {

        // 사용자의 예약 내역 가져오기 (회원 ID로 예약 조회)
        List<Reservation> reservations = reservationService.getReservationsByMemberId(memberId); // 해당 사용자의 예약 내역을 불러오는 서비스 메서드

        if (reservations.isEmpty()) {
            throw new Exception("예약 내역이 존재하지 않습니다.");
        }

        Reservation reservation = reservations.get(0); // 첫 번째 예약 선택

        // 예약된 객실 정보 가져오기 (room_id로 객실 정보 조회)
        Room room = roomService.getItem(reservation.getRoomId());

        // 조식 가격 불러오기 (breakfasts 테이블에서 가격 조회)
        Breakfast breakfast = breakfastService.getBreakfastById(1);  // 조식의 ID를 1로 가정 (혹은 적절한 ID로 수정)

        // 최종 가격 계산 (total_price + 조식 추가 가격)
        Integer finalPrice = reservation.getTotalPrice(); // 기본 가격
        if (reservation.getStatus().equals("예약확정")) { // 예약 상태가 '예약확정'일 때 조식을 포함하는 것으로 간주
            finalPrice += breakfast.getBreakfastPrice(); // 조식 가격 추가
        }

        // 모델에 예약 정보와 계산된 최종 가격 추가
        model.addAttribute("reservationDetails", reservation);
        model.addAttribute("room", room);
        model.addAttribute("breakfastPrice", breakfast.getBreakfastPrice());
        model.addAttribute("finalPrice", finalPrice);  // 최종 가격 (조식 추가 여부 포함)

        return "payment";  // 결제 페이지로 이동
    }

}
