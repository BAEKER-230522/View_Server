package com.baeker.baeker.base.security;

import com.baeker.baeker.msaController.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // username 이 이미 db 에 등록되어있는지 확인하는 api 요청
        Member member = new Member();

        return new User(member.getUsername(), member.getPassword(), member.getGrantedAuthorities());
    }
}
