package com.shuai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
@RequestMapping("info")
    public String info(){
    return "hollo world!";
}
}
