package com.aoeivux.controller;


import entity.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value = "/index")
    public String index() {
        System.out.println("test");
        return "test";
    }

    @RequestMapping(value = "/test1", params = {"name", "id=10"})
    public String test1(){
        System.out.println("Test1");
        return "test";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(@RequestParam("name") String name, @RequestParam("id") int id){
        System.out.println(name);
        System.out.println(id);
        return "test";
    }

    @RequestMapping(value = "/test3/{name}/{id}")
    public String restTest (@PathVariable("name") String name, @PathVariable("id") int id) {
        System.out.println(name);
        System.out.println(id);
        return "test";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Student student){
        System.out.println(student);
        return "test";
    }

    // 重定向
    @RequestMapping("/test4")
    public String redirect() {
        return "redirect:/index.jsp";
    }
    //转发，默认是转发
    @RequestMapping("/test5")
    public String forward() {
        return "forward:/index.jsp";
        // return "index";
    }
}
