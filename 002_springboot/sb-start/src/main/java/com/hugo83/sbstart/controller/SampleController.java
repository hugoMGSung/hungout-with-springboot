package com.hugo83.sbstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {
    @GetMapping(value="/hello")
    public void hello(Model model) {
        log.info("hello >>>>>>>>>>>>>>>>>>>> ");
        model.addAttribute("msg", "Hello, SpringBoot!");
    }    
}
