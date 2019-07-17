package com.horizon.tstest.models;

import com.google.gson.annotations.SerializedName;

/*By Phat La*/
public class BankDTO extends DTO {
    private String name;

    @SerializedName("logo")
    private String logoURL;


    //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }
}
