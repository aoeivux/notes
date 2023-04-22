package com.aoeivux.controller;

import entity.Student;
import entity.UserList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class DataBIndController {
    @RequestMapping("/baseType")
//    @ResponseBody
    public String baseType(int id) {
        return String.valueOf(id);
    }
    @RequestMapping("/packageType")
//    @ResponseBody
    public String packageType(@RequestParam(value = "num",required =
            false,defaultValue = "0") Integer id){
        return id+"";
    }

    @RequestMapping("/arr")
    public String array(String[] arr) {
        String s = Arrays.toString(arr);
        return s;
    }

    @RequestMapping("/list")
    public String list(UserList userList) {
        StringBuffer sb = new StringBuffer();
        for (Student user : userList.getUsers()) {
            sb.append(user);
        }
        return sb.toString();
    }


}
