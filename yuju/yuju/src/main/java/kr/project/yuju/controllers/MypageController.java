package kr.project.yuju.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MypageController {
    @GetMapping("/mypage/reservation_list")
    public String reservation_list() {
        return "/mypage/reservation_list";
    }
    
    @GetMapping("/mypage/change_password")
    public String change_password() {
        return "/mypage/change_password";
    }

    @GetMapping("/mypage/delete_account")
    public String delete_account() {
        return "/mypage/delete_account";
    }
}
