package com.riot.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class OAuthAttribute {

    private String name;
    private String image;
    private String email;
    private static ObjectMapper objectMapper;

    static{
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private OAuthAttribute(String name, String image, String email) {
        this.name = name;
        this.image = image;
        this.email = email;
    }

    public static OAuthAttribute ofKakao(Map<String, Object> attributes){

        KakaoAttributeDto kakaoAttributeDto = objectMapper.convertValue(attributes, KakaoAttributeDto.class);

        return new OAuthAttribute(
                (String)kakaoAttributeDto.getProperties().get("nickname"),
                (String)kakaoAttributeDto.getProperties().get("thumbnail_image"),
                (String)kakaoAttributeDto.getKakao_account().get("email"));

    }


}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class KakaoAttributeDto{

    private Long id;
    private LocalDateTime connected_at;
    private Map<String, Object> properties;
    private Map<String, Object> kakao_account;

}

