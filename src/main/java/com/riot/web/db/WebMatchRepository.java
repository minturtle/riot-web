package com.riot.web.db;


import com.riot.db.entity.MatchEntity;
import com.riot.db.entity.SummonerEntity;
import com.riot.db.repository.MatchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebMatchRepository extends MatchRepository{

    @Query("SELECT m FROM ParticipantEntity p LEFT JOIN MatchEntity m ON p.team.match=m WHERE p.puuid= :puuid")
    List<MatchEntity> findMatchsByPuuid(String puuid);
}
