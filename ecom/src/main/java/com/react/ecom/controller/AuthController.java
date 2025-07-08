package com.react.ecom.controller;


import com.react.ecom.Repository.RoleRepo;
import com.react.ecom.Service.AuthService;
import com.react.ecom.model.LoginRequest;
import com.react.ecom.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//@RequestMapping("/")
public class AuthController {

    @Autowired
    AuthService auth;



        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request){
            return auth.validate(request.getUserName(), request.getPassword());
        }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){

         auth.registerService(user);
        return  ResponseEntity.ok("User SuccessFully registered");
    }
}
