package kr.project.yuju.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }
}
