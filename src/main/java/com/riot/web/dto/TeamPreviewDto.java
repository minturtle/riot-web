package com.riot.web.dto;

import com.entity.match.Team;
import com.riot.db.entity.TeamEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamPreviewDto{
    private List<ParticipantMetadataDto> participants = new ArrayList<>();
    private boolean win;

    public TeamPreviewDto(TeamEntity team) {
        team.getParticipants().forEach(p->participants.add(new ParticipantMetadataDto(p)));
        this.win = team.isWin();
    }

}