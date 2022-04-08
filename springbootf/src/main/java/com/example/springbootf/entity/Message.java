package com.example.springbootf.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Setter @Getter
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String kind;
    private String code;
    private String name;
}
