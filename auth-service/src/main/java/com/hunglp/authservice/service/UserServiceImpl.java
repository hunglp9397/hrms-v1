package com.hunglp.authservice.service;


import com.hunglp.authservice.domain.CustomUser;
import com.hunglp.authservice.domain.User;
import com.hunglp.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

//    @Override
//    public UserDetails getByUsername(String name) {
//        User user = null;
//
//        // Query DB
//
//
//        return new CustomUser(user);
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not exists in DB");
        }


        return new CustomUser(user);
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
