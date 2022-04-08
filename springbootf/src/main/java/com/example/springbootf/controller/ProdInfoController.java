package com.example.springbootf.controller;

import com.example.springbootf.dto.ProdInfoDto;
import com.example.springbootf.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prod")
public class ProdInfoController {
    @Autowired
    KakaoPayService kakaopayService;

    @GetMapping("/prodInfo")
    /**
     * 판매상품조회
     * request ProdInfoDto
     * response ProdInfoDto
     */
    public ProdInfoDto getProdListAll(ProdInfoDto prodInfoDto) throws Exception{
        ///////////////////////////////////////////////////////////////////////////
        //@D 1. 판매상품조회
        //@L
        ///////////////////////////////////////////////////////////////////////////
        return kakaopayService.getProdList(prodInfoDto);
    }
}
