package com.qingteng.demo.service;

import com.qingteng.demo.entity.JwtUser;
import com.qingteng.demo.entity.Role;
import com.qingteng.demo.entity.User;
import com.qingteng.demo.jwt.JwtTokenUtil;
import com.qingteng.demo.respository.RoleRepository;
import com.qingteng.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Arrays.asList;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private UserRepository userRepository;


  @Override
  public User register(User userToAdd) {
    final String username = userToAdd.getUsername();
    if (userRepository.findByUsername(username) != null) {
      return null;
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    final String rawPassword = userToAdd.getPassword();
    userToAdd.setPassword(encoder.encode(rawPassword));
    userToAdd.setLastPasswordResetDate(new Date());
    Role role = roleRepository.findByName("ROLE_USER");
    userToAdd.setRoles(asList(role));
    return userRepository.save(userToAdd);
  }

  @Override
  public String login(String username, String password) {
    UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
    // Perform the security
    final Authentication authentication = authenticationManager.authenticate(upToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-security so we can generate token
    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    final String token = jwtTokenUtil.generateToken(userDetails);
    return token;
  }

  @Override
  public String refresh(String oldToken) {
    final String token = oldToken;
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      return jwtTokenUtil.refreshToken(token);
    }
    return null;
  }
}