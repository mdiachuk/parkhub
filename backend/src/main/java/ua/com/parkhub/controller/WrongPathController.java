package ua.com.parkhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WrongPathController {
    @RequestMapping(value = "/**/{path:[^.]*}")
    public String redirect() {
        return "forward:/";
    }
}

