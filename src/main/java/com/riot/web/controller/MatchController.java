package com.riot.web.controller;


import com.riot.web.dto.MatchPreviewDto;
import com.riot.web.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController{

    private final MatchService matchService;

    @GetMapping("/summoner/name")
    public ResponseEntity<List<MatchPreviewDto>> getMatchByNameFromDB(@RequestParam String name) throws IOException {
        final List<MatchPreviewDto> findMatches = matchService.findMatchesInDatabaseBySummonerName(name, 0, 10);
        return new ResponseEntity<>(findMatches, HttpStatus.OK);
    }
    @GetMapping("/summoner/name/refresh")
    public ResponseEntity<List<MatchPreviewDto>> getMatchByNameFromApi(@RequestParam String name) throws IOException {
        final List<MatchPreviewDto> findMatches = matchService.findMatchesFromApi(name, 0, 10);
        return new ResponseEntity<>(findMatches, HttpStatus.OK);
    }

}
