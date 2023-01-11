package com.hunglp.authservice.service;

import com.hunglp.authservice.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User getByUsername(String name);
}
