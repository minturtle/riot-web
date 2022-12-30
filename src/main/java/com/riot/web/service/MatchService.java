package com.riot.web.service;


import com.RiotAPIConnection;

import com.entity.match.Match;
import com.riot.db.entity.MatchEntity;
import com.riot.db.entity.SummonerEntity;
import com.riot.web.db.WebMatchRepository;
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

    private final WebMatchRepository matchRepository;
    private final SummonerService summonerService;
    private final RiotAPIConnection api;

    //DB에 있는 match 데이터들을 조회함.
    public List<MatchPreviewDto> findMatchesInDatabaseBySummonerName(String summonerName, int startIdx, int count) throws IOException {
        // DB에 summonerEntity가 들어있음이 보장되어 있음.
        SummonerEntity summonerEntity = summonerService.getSummoner(summonerName);

        return matchRepository.findMatchsByPuuid(summonerEntity.getPuuid()).stream().map(MatchPreviewDto::new).collect(Collectors.toList());
    }


    public List<MatchPreviewDto> findMatchesFromApi(String summonerName, int startIdx, int count) throws IOException {
        SummonerEntity summonerEntity = summonerService.getSummoner(summonerName);

        List<Match> findMatches = api.getMatchesBySummonerName(summonerName, startIdx, count, summonerEntity.getLastApiCallTime());
        List<MatchEntity> matchEntities = matchRepository.saveAll(findMatches.stream().map(MatchEntity::new).collect(Collectors.toList()));

        return matchEntities.stream().map(MatchPreviewDto::new).collect(Collectors.toList());
    }
}