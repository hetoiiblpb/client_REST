package ru.hetoiiblpb.client_REST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRestController {
    @GetMapping("greeting")
    public String allUsersPage(){
        return "greeting";
    }
}
