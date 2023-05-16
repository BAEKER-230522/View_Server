package com.baeker.baeker.base.security;

import com.baeker.baeker.base.request.RsData;
import com.baeker.baeker.feign.MemberClient;
import com.baeker.baeker.feign.dto.MemberJoinDto;
import com.baeker.baeker.msaController.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired private MemberClient client;

    private String username;
    private String nickName;
    private String profileImage;
    private String email;
    private String token;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        switch (provider) {
            case "KAKAO" -> kakaoPathing(oAuth2User, userRequest);
            default -> username = oAuth2User.getName();
        }

        RsData<Member> memberRs = client.join(new MemberJoinDto(username, nickName, "", provider, email, token, profileImage));
        Member member = memberRs.getData();
        return new CustomOAuth2User(member.getUsername(), member.getPassword(), member.getGrantedAuthorities());
    }

    //-- 카카오 Json mapping --//
    private void kakaoPathing(OAuth2User oAuth2User, OAuth2UserRequest userRequest) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String profileImg = (String) profile.get("profile_image_url");

        this.token = userRequest.getAccessToken().getTokenValue();

        this.username = oAuth2User.getName();
        this.profileImage = profileImg;
        this.nickName = nickname;
        this.email = email;
    }
}

class CustomOAuth2User extends User implements OAuth2User {
    public CustomOAuth2User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return getUsername();
    }
}