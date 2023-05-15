package com.baeker.baeker.msaController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {

    private String username;
    private String nickname;
    private String password;
    private String baekJoonName;
    private String provider;
    private String email;
    private String token;
    private String profileImage;

    //-- create method --//


    public MemberDto(String username, String nickname,String password, String provider, String email, String token, String profileImage) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.provider = provider;
        this.email = email;
        this.token = token;
        this.profileImage = profileImage;
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
