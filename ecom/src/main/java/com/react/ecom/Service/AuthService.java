package com.react.ecom.Service;


import com.react.ecom.Repository.RoleRepo;
import com.react.ecom.Repository.UserRepo;
import com.react.ecom.configuration.JwtUtil;
import com.react.ecom.model.Users;
import com.react.ecom.model.jwtDTO;
import com.react.ecom.model.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user = repo.findByEmail(username);

        if(user==null)throw new UsernameNotFoundException("No user Found For "+username);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRole());
    }

    @Autowired
    UserRepo repo;

    @Autowired
    JwtUtil jwt;


    @Autowired
    RoleRepo roleRepo;
    public ResponseEntity<?> validate(String userName, String password) {
        UserDetails userDetails = loadUserByUsername(userName);
        // allow access
        if(encoder().matches(password, userDetails.getPassword())){
            String token = jwt.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new jwtDTO(token,userDetails.getUsername()));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    public void registerService(Users user){
        user.setPassword(encoder().encode(user.getPassword()));

        role userRole = roleRepo.findByRoleName("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("Default ROLE_USER not found in database.");
        }

        // Assign role
        Set<role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRole(roles);

        repo.save(user);
        //return user;
    }
}
