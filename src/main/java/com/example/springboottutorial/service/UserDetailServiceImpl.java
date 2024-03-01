package com.example.springboottutorial.service;

import com.example.springboottutorial.model.UserModel;
import com.example.springboottutorial.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
            return new User(userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>());
        }
    }
}
