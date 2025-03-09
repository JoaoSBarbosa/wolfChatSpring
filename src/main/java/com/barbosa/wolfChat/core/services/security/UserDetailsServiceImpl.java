package com.barbosa.wolfChat.core.services.security;

import com.barbosa.wolfChat.core.models.security.UserDetailsImpl;
import com.barbosa.wolfChat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).map(UserDetailsImpl::new).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
