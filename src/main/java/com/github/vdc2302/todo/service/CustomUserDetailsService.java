package com.github.vdc2302.todo.service;

import com.github.vdc2302.todo.model.User;
import com.github.vdc2302.todo.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @NonNull
  public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException{
    User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        Collections.emptyList()
    );
  }
}
