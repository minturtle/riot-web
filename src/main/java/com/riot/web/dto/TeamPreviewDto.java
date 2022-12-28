package com.riot.web.dto;

import com.entity.match.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamPreviewDto{
    private List<ParticipantMetadataDto> participants = new ArrayList<>();
    private boolean win;

    public TeamPreviewDto(Team team) {
        this.win = team.getWin();
    }

    public void addParticipantAtTeam(ParticipantMetadataDto participant){
        participants.add(participant);
    }
}