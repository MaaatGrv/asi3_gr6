package com.asi1.GameCard.statics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
}
