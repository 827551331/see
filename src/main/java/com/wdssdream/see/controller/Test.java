package com.wdssdream.see.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: Test
 * date: 2023/6/22 00:40
 *
 * @author wang_yw
 */
@RestController
public class Test {

    @GetMapping("/hello")
    public String login() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("authentication："+authentication.getName());
        return "ok";
    }
}
