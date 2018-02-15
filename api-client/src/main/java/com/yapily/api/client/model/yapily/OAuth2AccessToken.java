package com.yapily.api.client.model.yapily;

import lombok.Data;

@Data
public class OAuth2AccessToken {
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;
    private String refreshToken;
    private String scope;
    private String rawResponse;
}
