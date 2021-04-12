package com.tp.Nile.models;

import javax.persistence.*;

@Entity
@Table(name = "user_name")
public class User {


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    public User() {

    }


}
