package com.hackday.angelhack.page;

import com.hackday.angelhack.common.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/page")
@Controller
public class PageController {

    @GetMapping("/log/{logId}")
    public String socket(Model model, @PathVariable Long logId) {
        model.addAttribute("log_id", String.valueOf(logId));
        return "socket";
    }

}
