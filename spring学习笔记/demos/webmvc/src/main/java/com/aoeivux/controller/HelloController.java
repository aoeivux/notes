package com.aoeivux.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value = "/index",method = RequestMethod.GET, params = {"name", "id"})
    public String index(String name, int id) {
        System.out.println(id);
        System.out.println(name);
        return "test";
    }
}
