package com.riot.web.dto;

import com.entity.match.Match;
import com.entity.match.Participant;
import com.riot.db.entity.MatchEntity;
import com.riot.db.entity.ParticipantEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class MatchPreviewDto{

    private List<TeamPreviewDto> teams;


    public MatchPreviewDto(MatchEntity match){
        teams = new ArrayList<>(List.of(
                    new TeamPreviewDto(match.getTeam().get(0)),
                    new TeamPreviewDto(match.getTeam().get(1))));
    }
}
