package com.kaggle.titanic.dbservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "titanic")
public class Titanic {
        @Id
        @Column(name = "passenger")
        private int passenger;
        @Column(name="survived")
        private int survived;
        @Column(name="pclass")
        private int pclass;
        @Column(name = "name")
        private String name;
        @Column(name = "sex")
        private String sex;
        @Column(name = "age")
        private Integer age;
        @Column(name = "sibsp")
        private int sibsp;
        @Column(name = "parch")
        private int parch;
        @Column(name = "ticket")
        private String ticket;
        @Column(name = "fare")
        private double fare;
        @Column(name = "cabin")
        private String cabin;
        @Column(name = "embarked")
        private char embarked;
}
