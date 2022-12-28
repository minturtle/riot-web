package com.riot.web.dto;

import com.entity.match.Match;
import com.entity.match.Participant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class MatchPreviewDto{

    private List<TeamPreviewDto> teams;


    public MatchPreviewDto(Match match){
        teams = new ArrayList<>(List.of(
                    new TeamPreviewDto(match.getInfo().getTeams().get(0)),
                    new TeamPreviewDto(match.getInfo().getTeams().get(1))));

        final List<Participant> participants = match.getInfo().getParticipants();

        participants.forEach(p->{
            int teamIdx = (p.getTeamId() / 100)- 1;
            teams.get(teamIdx).addParticipantAtTeam(new ParticipantMetadataDto(p));
        });
    }
}
