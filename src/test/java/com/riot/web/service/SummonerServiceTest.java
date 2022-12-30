package com.riot.web.service;

import com.RiotAPIConnection;
import com.entity.Summoner;
import com.riot.db.entity.SummonerEntity;
import com.riot.web.db.WebSummonerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest //단위테스트이나 DB에 값이 있는지 확인해야하는 경우가 있기 때문에 @SpringBootTest 사용
@Transactional
class SummonerServiceTest {

    @Autowired
    private SummonerService summonerService;

    @SpyBean
    private WebSummonerRepository summonerRepository;

    @MockBean
    private RiotAPIConnection api;

    private final String summonerName = "hide on bush";
    private final String puid = "hE85rBOC6XPL4fIpNbM6ERduuAoHEvjvf9CZGVWf4yNB85zen1rXPH8gL3KuinKsuJoECCz2uzHy_w";
    private final Summoner fakeSummoner = new Summoner("accountId", 1, 11L, summonerName, "id", puid, 10L);

    @Test
    @DisplayName("객체 생성 테스트")
    void t1() throws Exception {
        //then
        assertThat(summonerService).isNotNull();
        assertThat(summonerRepository).isNotNull();
        assertThat(api).isNotNull();
    }

    @Test
    @DisplayName("Summoner 조회시 DB에 Summoner이 없는 경우")
    void t2() throws Exception {
        //given
        given(summonerRepository.findSummonerEntityByName(summonerName)).willReturn(Optional.empty());
        given(api.getSummonerByName(summonerName)).willReturn(fakeSummoner);
        //when
        SummonerEntity summonerEntity = summonerService.getSummoner(summonerName);

        //then
        SummonerEntity findSummoner = summonerRepository.findByIdx(summonerEntity.getIdx()).get();

        assertThat(summonerEntity).isNotNull();
        assertThat(findSummoner).isNotNull();
        assertThat(summonerEntity.getIdx()).isEqualTo(findSummoner.getIdx());
    }

    @Test
    @DisplayName("DB에 Summoner이 있는 경우")
    void t3() throws Exception {
        //given
        final SummonerEntity fakeSummonerEntity = new SummonerEntity(fakeSummoner);

        given(summonerRepository.findSummonerEntityByName(summonerName)).willReturn(Optional.of(fakeSummonerEntity));

        //when
        SummonerEntity findSummoner = summonerService.getSummoner(summonerName);
        //then
        assertThat(findSummoner).isEqualTo(fakeSummonerEntity);
    }
}