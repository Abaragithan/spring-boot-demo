package com.example.spring.boot.demo.services;

import com.example.spring.boot.demo.entities.UserEntity;
import com.example.spring.boot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userDetails = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User name not found"));

        return new User(userDetails.getUsername(), userDetails.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
    }
}
