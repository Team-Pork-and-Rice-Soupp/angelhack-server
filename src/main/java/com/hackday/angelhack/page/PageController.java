package com.hackday.angelhack.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/socket")
    public String socket() {
        return "socket";
    }

}
