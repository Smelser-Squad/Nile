package com.tp.Nile.controllers.Helper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "charge")
public class ChargeRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_id")
    private Integer chargeId;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "email", nullable = false)
    private String email;


}
