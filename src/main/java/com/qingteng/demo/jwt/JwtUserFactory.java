package com.qingteng.demo.jwt;

import com.qingteng.demo.entity.JwtUser;
import com.qingteng.demo.entity.Role;
import com.qingteng.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(authoritie -> new SimpleGrantedAuthority(authoritie.getName()))
                .collect(Collectors.toList());
    }
}