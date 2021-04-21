package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart implements Serializable {

    @EmbeddedId
    private ProductOrderId id = new ProductOrderId();

    @ManyToOne
    @MapsId("orderId")
    @JsonIgnoreProperties(value = {"cartProducts"})
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JsonIgnoreProperties(value = {"productCarts"})
    private Product product;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

}
