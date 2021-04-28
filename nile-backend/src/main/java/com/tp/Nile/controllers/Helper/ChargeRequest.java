package com.tp.Nile.controllers.Helper;

public class ChargeRequest {
    String token;
    Integer amount;

    public ChargeRequest(){

    }
    public ChargeRequest(String token, Integer amount) {
        this.token = token;
        this.amount = amount;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public Integer getAmount() {
        return amount;
    }
}
