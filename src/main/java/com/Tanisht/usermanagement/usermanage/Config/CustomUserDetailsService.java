package com.Tanisht.usermanagement.usermanage.Config;


import com.Tanisht.usermanagement.usermanage.Model.User;
import com.Tanisht.usermanagement.usermanage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        else{
            return new CustomUserDetails(user);
        }
    }
}
