package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "specification")
public class Specification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spec_id")
    private Integer specId;

    @Column(name = "spec_name")
    private String specName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    @JsonBackReference
    private Type type;

    public Specification(String specName, Type type) {
        this.specName = specName;
        this.type = type;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
