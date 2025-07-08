package com.react.ecom.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class jwtDTO {
    private String token;
    private String userName;

    public jwtDTO(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }
    // getters and setters
}