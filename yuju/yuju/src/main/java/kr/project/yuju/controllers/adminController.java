package kr.project.yuju.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }
    @GetMapping("/admin/reservations")
    public String reservations() {
        return "admin/reservations";
    }
    @GetMapping("/admin/rooms")
    public String rooms() {
        return "admin/rooms";
    }
}
