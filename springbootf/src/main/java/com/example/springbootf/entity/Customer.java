package com.example.springbootf.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Setter @Getter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int custNo;
    private int productId;
    private BigDecimal investAmt;
    private Date startedAt;
}
