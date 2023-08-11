package com.hugo83.sbstart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class JsonSampleController {
    @GetMapping("/helloJson")
    public String helloJson() {
        log.info("▶▷▶▷▶▷ hello :: ");
        return "{ " +
            " \"CityName\": \"Busan\", " +
            " \"homeTown\": \"Metro City\", " +
            " \"formed\": 2023, " +
            " \"members\": [ " +
            " { " +
            "    \"name\": \"Molecule Man\",  " +
            "    \"age\": 29, " +
            "    \"secretIdentity\": \"Dan Jukes\", " +
            "    \"powers\": [\"Radiation resistance\", \"Turning tiny\", \"Radiation blast\" ] " +
            "  } " +
            "] " +
        "} ";
    }
}
