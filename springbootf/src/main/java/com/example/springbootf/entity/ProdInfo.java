package com.example.springbootf.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Setter @Getter
public class ProdInfo {
    @Id
    private int productId;
    private String title;
    private BigDecimal totalInvestingAmount;
    private BigDecimal nowInvestingAmount;
    private int nowInvestingCnt;
    private Date startedAt;
    private Date finishedAt;
    private String state;
}
