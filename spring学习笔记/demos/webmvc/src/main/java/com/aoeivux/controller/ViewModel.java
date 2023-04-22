package com.aoeivux.controller;

import entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;

@Controller
public class ViewModel {
    @RequestMapping("/model")
    public String model(Model model){
        Student student = new Student();
        model.addAttribute("user", student);
        return "view";
    }

    @RequestMapping("/modelandview")
    public String modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        return "view";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Student json(@RequestBody Student user) {
        System.out.println("user: "+user);
        user.setId(2);
        user.setName("小红");
        return user;
    }

    @RequestMapping("/convert")
    @ResponseBody
    public String convert(Date date) {
        return date.toString();
    }

}
