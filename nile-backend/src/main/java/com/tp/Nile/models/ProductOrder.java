package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder implements Serializable {

    @EmbeddedId
    private ProductOrderId id = new ProductOrderId();

    @ManyToOne
    @MapsId("orderId")
    @JsonIgnoreProperties(value = {"orderProducts"})
    private Order order;

    @ManyToOne
    @MapsId("productId")

    @JsonIgnoreProperties(value = {"productOrders"})

    private Product product;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

}
