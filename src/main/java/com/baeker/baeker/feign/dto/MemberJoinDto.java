package com.baeker.baeker.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberJoinDto {
    private String username;
    private String nickName;
    private String password;
    private String provider;
    private String email;
    private String token;
    private String profileImage;

}
