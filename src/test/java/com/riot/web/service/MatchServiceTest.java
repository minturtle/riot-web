package com.riot.web.service;

import com.RiotAPIConnection;
import com.entity.Summoner;
import com.entity.match.Info;
import com.entity.match.Match;
import com.entity.match.MetaData;
import com.riot.db.entity.MatchEntity;
import com.riot.db.entity.SummonerEntity;
import com.riot.db.repository.MatchRepository;
import com.riot.db.repository.SummonerRepository;
import com.riot.web.db.WebMatchRepository;
import com.riot.web.dto.MatchPreviewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private WebMatchRepository matchRepository;

    @Mock
    private SummonerService summonerService;

    @Mock
    private RiotAPIConnection api;

    private final Long fakeApiCallTime = 1672375501675L;
    private final String fakeSummonerName = "hide on bush";
    private final SummonerEntity fakeSummonerEntity =
            new SummonerEntity(new Summoner("id", 1, 11L, fakeSummonerName, "id", "puuid", 11L));




    @Test
    @DisplayName("객체 생성")
    void t1(){

        assertThat(matchRepository).isNotNull();
        assertThat(matchService).isNotNull();
        assertThat(summonerService).isNotNull();
        assertThat(api).isNotNull();
    }


    @Test
    @DisplayName("match api 조회-api calltime이 없는 경우")
    void t2() throws Exception {
        //given
        given(summonerService.getSummoner(fakeSummonerName)).willReturn(fakeSummonerEntity);

        given(api.getMatchesBySummonerName(fakeSummonerName, 0, 10, 0L)).willReturn(
                List.of(new Match(new MetaData(), new Info()),
                        new Match(new MetaData(), new Info()),
                        new Match(new MetaData(), new Info())));

        //when
        List<MatchPreviewDto> matches = matchService.findMatchesFromApi(fakeSummonerName, 0, 10);

        List<MatchEntity> findMatches = matchRepository.findAll();
        //then
        assertThat(findMatches.size()).isEqualTo(3);
        assertThat(findMatches.size()).isEqualTo(matches.size());
    }


}