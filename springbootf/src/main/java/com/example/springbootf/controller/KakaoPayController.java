package com.example.springbootf.controller;

import com.example.springbootf.consts.KakaoPayConst;
import com.example.springbootf.dto.CustomerDto;
import com.example.springbootf.entity.Customer;
import com.example.springbootf.exception.UserException;
import com.example.springbootf.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class KakaoPayController {
    @Autowired
    KakaoPayService kakaopayService;

    @PutMapping("/invest")
    /**
     * 상품투자하기
     * request CustomerDto
     * response CustomerDto
     */
    public CustomerDto putInVestProd(@Valid CustomerDto customerDto, BindingResult bindingResult) throws  Exception{
        Customer customer = new Customer();

        ///////////////////////////////////////////////////////////////////////////
        //@D 1. 상품투자 필수항목 채크
        //@L
        ///////////////////////////////////////////////////////////////////////////
        if(bindingResult.hasErrors()){
            for(ObjectError e : bindingResult.getAllErrors()) {
                //필수입력항목 확인해주세요. {1}
                throw new UserException("B005", HttpStatus.BAD_REQUEST,e.getDefaultMessage());
            }
        }else {

            ///////////////////////////////////////////////////////////////////////////
            //@D 2. 상품투자하기
            //@L
            ///////////////////////////////////////////////////////////////////////////
            customer.setCustNo(customerDto.getCustNo());
            customer.setProductId(customerDto.getProductId());
            customer.setInvestAmt(customerDto.getInvestAmt());

            //상품투자 서비스
            customerDto = kakaopayService.investProd(customer);
        }

        //정상
        customerDto.setResseCd(KakaoPayConst.RES_DVCD.SUSS_CD);
        customerDto.setResseName(KakaoPayConst.RES_DVCD.SUSS_NM);
        return customerDto;
    }

    @PostMapping("/mypage")
    /**
     * 고객투자이력조회
     * request customerDto
     * response customerDto
     */
    public CustomerDto getMypage(CustomerDto customerDto) throws UserException{
        ///////////////////////////////////////////////////////////////////////////
        //@D 고객투자이력조회
        //@L
        ///////////////////////////////////////////////////////////////////////////
        return kakaopayService.getMyPage(customerDto.getCustNo());
    }

}
