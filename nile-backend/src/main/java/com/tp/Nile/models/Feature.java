package com.tp.Nile.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "feature")
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private Integer featureId;

    @Column(name ="name",nullable = false)
    private String name;

    public Feature(String name) {
        this.name = name;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
