
/*
package com.example.shopad.securityconfig;

import com.example.shopad.model.UserAccount;
import com.example.shopad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountDetail  implements UserDetailsService {


    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccount credentials=userRepository.findByUserName(username);

        if (credentials == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails details= User.withUsername(username).password(credentials.getPassword()).authorities("USER").build();



        return details;
    }
}
*/
