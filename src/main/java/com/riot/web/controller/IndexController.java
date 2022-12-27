package com.riot.web.controller;


import com.riot.web.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {


    @GetMapping("/")
    public String index(HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");

        return user.toString();

    }
}
