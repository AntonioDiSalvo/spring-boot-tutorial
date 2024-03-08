package com.example.springboottutorial.service;

import com.example.springboottutorial.model.UserModel;
import com.example.springboottutorial.repository.UserRepository;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    final private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userDetails = userRepository.findByUsername(username);
        // caso utente non trovato
        if (userDetails == null) {
            throw new UsernameNotFoundException(username);
        } else {
            // se trovo l'utente, lo ritorno nella struttura User di Spring Security
//            List<SimpleGrantedAuthority> grantedAuthorityList = userDetails.getAuthority()
//                    .map(authority -> new SimpleGrantedAuthority(authority))
//                    .collect(Collectors.toList());
            List<SimpleGrantedAuthority> grantedAuthorityList =
                    new ArrayList<SimpleGrantedAuthority>();

            if ("ROLE_ADMIN".equals(userDetails.getRole())) {
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_READ"));
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_WRITE"));
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_UPDATE"));
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_DELETE"));
            } else if ("ROLE_USER".equals(userDetails.getRole())) {
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_READ"));
            } else {
                grantedAuthorityList.add(new SimpleGrantedAuthority(userDetails.getRole()));
            }

            return new User(userDetails.getUsername(), userDetails.getPassword(), grantedAuthorityList);
        }
    }
}
