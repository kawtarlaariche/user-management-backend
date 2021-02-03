package com.laariche.usermanagement.services;

import com.laariche.usermanagement.entities.User;
import com.laariche.usermanagement.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = iUserRepository.findByEmail(email);
        if(user.isPresent()) return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        else throw new UsernameNotFoundException("");
    }
}
