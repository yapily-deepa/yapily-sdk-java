package com.acaciaconnect.api.client.model.acacia;

import lombok.Data;

@Data
public class Country {

    public Country() {}

    public Country(String displayName, String countryCode2) {
        this.displayName = displayName;
        this.countryCode2 = countryCode2;
    }

    private String displayName;
    private String countryCode2;
}
