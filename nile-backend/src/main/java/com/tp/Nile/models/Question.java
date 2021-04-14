package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate(true)
@Table(name = "question")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "votes", nullable = false)
    private Integer votes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "question",
            orphanRemoval = true)
    @JsonManagedReference
    private Set<Answer> answers = new HashSet<>();

}
