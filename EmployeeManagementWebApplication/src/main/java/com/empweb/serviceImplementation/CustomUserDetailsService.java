package com.empweb.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empweb.entity.User;
import com.empweb.repository.UserRepository;
import com.empweb.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

       // Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        //user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getAuthority())));

       // return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        return new CustomUserDetails(user);
    }
}
