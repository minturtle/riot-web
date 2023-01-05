package com.riot.web.service;

import com.RiotAPIConnection;
import com.entity.Summoner;
import com.riot.db.entity.SummonerEntity;
import com.riot.web.db.WebSummonerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SummonerService {

    private final WebSummonerRepository summonerRepository;
    private final RiotAPIConnection api;


    /* Summoner을 DB에서 조회해 DB에 없으면 API로 찾아서 DB에 저장
    service 클래스들만 사용할 수 있도록 제한
     */
    protected SummonerEntity getSummoner(String name) throws IOException {
        Optional<SummonerEntity> optionalSummoner = summonerRepository.findSummonerEntityByName(name);
        SummonerEntity summonerEntity;

        if(optionalSummoner.isPresent()){
            summonerEntity = optionalSummoner.get();
        }else{
            Summoner s = api.getSummonerByName(name);
            summonerEntity = new SummonerEntity(s);
            summonerRepository.save(summonerEntity);
        }

        return summonerEntity;
    }


    public void updateSummoner(String name) throws IOException {
        final SummonerEntity summonerEntity = getSummoner(name);
        final Summoner apiSummoner = api.getSummonerByName(name);

        summonerEntity.setLevel(apiSummoner.getSummonerLevel());
        summonerEntity.setAccountId(apiSummoner.getAccountId());
        summonerEntity.setEid(apiSummoner.getId());
        summonerEntity.setProfileIconId(apiSummoner.getProfileIconId());
        summonerEntity.setPuuid(apiSummoner.getPuuid());
        summonerEntity.setRevisionDate(apiSummoner.getRevisionDate());

    }

    public void setSummonerApiCallTime(String name) throws IOException {
        final SummonerEntity summonerEntity = getSummoner(name);

        summonerEntity.setLastApiCallTime(System.currentTimeMillis());
    }
}
