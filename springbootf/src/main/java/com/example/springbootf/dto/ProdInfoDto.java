package com.example.springbootf.dto;

import com.example.springbootf.entity.ProdInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter @Setter
public class ProdInfoDto {
    private String resseCd;//응답코드
    private String resseName;//응답내용
    private String nowDate;//날짜
    private int productId;//상품코드
    private List<ProdInfo> prodInfos; //상품리스트
}
