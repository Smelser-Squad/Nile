package com.tp.Nile.controllers.Helper;

public class ChargeRequest {
    String token;
    Integer amount;
    String email;

    public ChargeRequest(){

    }
    public ChargeRequest(String token, Integer amount, String email) {
        this.token = token;
        this.amount = amount;
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public Integer getAmount() {
        return amount;
    }
}
