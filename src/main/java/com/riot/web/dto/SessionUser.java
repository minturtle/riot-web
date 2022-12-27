package com.riot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class SessionUser implements Serializable {

    private String name;
    private String uuid;
    private String picture;

    @Builder
    public SessionUser(String name, String uuid, String picture) {
        this.name = name;
        this.uuid = uuid;
        this.picture = picture;
    }
}
