package com.example.springboottest.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    private String client_id;
    private String redirect_uri;
    private String login;
    private String scope;
    private String state;
    private String code;
    private String client_secret;
}
