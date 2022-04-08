package com.example.springbootf.dto;

import com.example.springbootf.entity.Customer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Getter @Setter
public class CustomerDto {
    private String resseCd;//응답코드
    private String resseName;//응답내용
    @Range(min=1,max=9000,message = "고객번호는 필수항목입니다(1~9000)")
    private int custNo;//고객번호
    @Range(min=1,max=9000,message = "상품번호는 필수항목입니다(1~9000)")
    private int productId;//상품코드
    @NotNull(message = "투자금액은 필수항목입니다.")
    @Min(value = 1,message = "투자급액은 1원이상 투자 가능합니다.")
    private BigDecimal investAmt;//투자금액
    private List<Customer> customers;//투자고객리스트
}
