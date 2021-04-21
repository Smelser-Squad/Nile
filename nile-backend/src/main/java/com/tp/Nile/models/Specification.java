package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table
public class Specification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spec_id")
    private Integer specId;

    @Column(name = "spec_name", nullable = false)
    private String specName;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE})
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
}
