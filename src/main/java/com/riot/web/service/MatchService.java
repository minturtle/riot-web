package com.riot.web.service;


import com.RiotAPIConnection;
import com.entity.Summoner;
import com.riot.db.repository.MatchRepository;
import com.riot.web.dto.MatchPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final RiotAPIConnection api;

    public List<MatchPreviewDto> findMatchesBySummonerName(String name) throws IOException {
        return api.getMatchesBySummonerName(name, 0, 1).stream().map(match -> new MatchPreviewDto(match)).collect(Collectors.toList());
    }

}