package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate(true)
@Table(name = "answer")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"answers"})
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"answers"})
    private User user;

}
