package com.hugo83.mybatlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hugo83.mybatlog.dao.UserDao;
import com.hugo83.mybatlog.dto.UserDto;


@Controller 
public class SignUpController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/signUp")
    public String signUp() {
        return "signUpForm";
    }

    @PostMapping("/signUp/save")
    public String save(@ModelAttribute UserDto userDto, Model m, BindingResult bindingResult) throws Exception {
        userDao.insertUser(userDto);
        return "home";
    }
    
}