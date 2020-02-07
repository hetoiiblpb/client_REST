package ru.hetoiiblpb.client_REST.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "403";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
