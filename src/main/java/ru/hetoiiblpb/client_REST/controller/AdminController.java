package ru.hetoiiblpb.client_REST.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AdminController {

    @GetMapping("allUsers")
    public String allUsersPage(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session.getAttribute("roles").toString().contains("ROLE_ADMIN")){
            return "allUsers";
        } else {return "403";}
    }



}
