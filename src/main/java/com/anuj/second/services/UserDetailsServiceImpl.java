package com.anuj.second.services;

import com.anuj.second.entity.user;
import com.anuj.second.repository.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userrepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         user u=userRepository.findByusername(username);
         if(u!=null){
             return User.builder()
                     .username(u.getUsername())
                     .password(u.getPassword())
                     .roles(u.getRoles().toArray(new String[0]))
                     .build();
         }
        throw new UsernameNotFoundException("User not found");
    }
}
