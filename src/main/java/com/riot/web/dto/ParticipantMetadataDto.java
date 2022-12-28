package com.riot.web.dto;

import com.entity.match.Participant;
import lombok.Data;

@Data
public class ParticipantMetadataDto {

    private int kill;
    private int death;
    private int assist;

    private int championLevel;
    private String championName;
    private String sumonnerName;
    private String role;

    public ParticipantMetadataDto(Participant p) {
        this.kill = p.getKills();
        this.death = p.getDeaths();
        this.assist = p.getAssists();
        this.championLevel = p.getChampLevel();
        this.championName = p.getChampionName();
        this.sumonnerName = p.getSummonerName();
        this.role = p.getRole();
    }
}
