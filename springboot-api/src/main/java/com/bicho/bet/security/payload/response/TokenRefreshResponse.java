package com.bicho.bet.security.payload.response;


import lombok.Getter;

@Getter
public class TokenRefreshResponse {


    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";


    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
