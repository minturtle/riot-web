package com.riot.web.controller;


import com.riot.web.dto.MatchPreviewDto;
import com.riot.web.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController{

    private final MatchService matchService;

    @GetMapping("/summoner/name/{name}")
    public ResponseEntity<List<MatchPreviewDto>> getMatchByName(@PathVariable String name) throws IOException {
        final List<MatchPreviewDto> findMatches = matchService.findMatchesBySummonerName(name);
        return new ResponseEntity<>(findMatches, HttpStatus.OK);
    }

}
