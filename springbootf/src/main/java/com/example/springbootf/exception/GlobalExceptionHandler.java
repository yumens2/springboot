package com.example.springbootf.exception;

import com.example.springbootf.dto.CustomerDto;
import com.example.springbootf.entity.Message;
import com.example.springbootf.service.KakaoPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    KakaoPayService kakaopayService;

    @ExceptionHandler(value = Exception.class)
    public CustomerDto handleException(Exception e){
        CustomerDto cusDto = new CustomerDto();
        cusDto.setResseCd("E000");
        cusDto.setResseName("오류가 발생하였습니다.[" + e.getMessage() +"]");
        return cusDto;
    }

    @ExceptionHandler(value = UserException.class)
    public CustomerDto handleUserException(UserException e){
        CustomerDto cusDto = new CustomerDto(); //고객정보
        Message message = new Message();        //메시지관리

        String getMsg  = e.getMessage();
        String errCode = "";//에러코드
        String errMsg  = "";//에러메시지

        errCode = e.getMessage().split(",")[0];

        if(getMsg.split(",").length>1)
            errMsg  = e.getMessage().split(",")[1];

        //조회값 셋팅
        message.setCode(errCode);

        //메시지코드 조회
        message = kakaopayService.getMessage(message);

        //치환할 메시지가 있을경우 치환
        if(errMsg.length()>0){
            errMsg = message.getName().replaceAll("1",errMsg);
        }else {
            errMsg = message.getName();
        }

        cusDto.setResseCd(errCode);
        cusDto.setResseName(errMsg);
        
        return cusDto;
    }


}
