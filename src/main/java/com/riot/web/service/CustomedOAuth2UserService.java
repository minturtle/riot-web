package com.riot.web.service;

import com.riot.web.dto.OAuthAttribute;
import com.riot.web.entity.User;
import com.riot.web.db.UserRepository;
import com.riot.web.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomedOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // 유저의 email, image, name정보가 담겨있음.
        final Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuthAttribute attribute = OAuthAttribute.ofKakao(attributes);

        User user = saveOrUpdate(attribute);

        SessionUser sessionUser = SessionUser.builder()
                .name(user.getName())
                .uuid(user.getUuid())
                .picture(user.getPicture()).build();

        httpSession.setAttribute("user", sessionUser); // SessionUser (직렬화된 dto 클래스 사용)

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), oAuth2User.getAttributes(), userNameAttributeKey);
    }


    // 유저 생성, 이미 존재하면 수정함.
    private User saveOrUpdate(OAuthAttribute attribute){

        User user = userRepository.findByEmail(attribute.getEmail())
                .map(entity -> entity.update(attribute.getName() ,attribute.getImage()))
                .orElse(User.builder()
                        .uuid(UUID.randomUUID().toString())
                        .email(attribute.getEmail())
                        .name(attribute.getName())
                        .picture(attribute.getImage())
                        .role(User.Role.USER).build());

        return userRepository.save(user);
    }


}
