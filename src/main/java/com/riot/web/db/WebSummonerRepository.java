package com.riot.web.db;

import com.riot.db.entity.SummonerEntity;
import com.riot.db.repository.SummonerRepository;

import java.util.Optional;

public interface WebSummonerRepository extends SummonerRepository {

    Optional<SummonerEntity> findSummonerEntityByName(String name);

    Optional<SummonerEntity> findByIdx(Long idx);
}
