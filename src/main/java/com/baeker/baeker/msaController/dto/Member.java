package com.baeker.baeker.msaController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Member {

    private Long id;
    private String username;
    private String nickname;
    private String baekJoonName;
    private String about;
    private String profileImg;
    private String kakaoProfileImage;
    private String password;
    private String provider;
    private String email;
    private String token;
    private boolean newMember;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    int bronze;
    int sliver;
    int gold;
    int diamond;
    int ruby;
    int platinum;

    //-- create method --//

    public Member(String username, String nickname, String password, String provider, String email, String token, String profileImage) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.provider = provider;
        this.email = email;
        this.token = token;
        this.kakaoProfileImage = profileImage;
    }


    //-- create authorize --//
    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 모든 회원에게 member 권한 부여 //
        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        // admin 권한 부여 //
        if ("admin".equals(username))
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));

        return grantedAuthorities;
    }
}
