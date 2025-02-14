package kr.project.yuju_hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/join")
    public String join() {
        return "account/join";
    }
}
