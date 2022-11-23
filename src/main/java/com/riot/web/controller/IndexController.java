package com.riot.web.controller;

import com.riot.web.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class IndexController {


    private final MatchService matchService;


    @GetMapping("/")
    public String index(){
        return "hello riot server!";
    }

    @GetMapping("/match/list")
    public String findMatches(@RequestParam String summonerName) throws IOException {
        matchService.findMatchesBySummonerName(summonerName);

        return "good";
    }
}
