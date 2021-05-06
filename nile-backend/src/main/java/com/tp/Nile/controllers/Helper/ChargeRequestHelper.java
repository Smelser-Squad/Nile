package com.tp.Nile.controllers.Helper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tp.Nile.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class ChargeRequestHelper  {


    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "email", nullable = false)
    private String email;

    private List<Object> cartProductId;


    public List<Object> getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(List<Object> cartProductId) {
        this.cartProductId = cartProductId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
