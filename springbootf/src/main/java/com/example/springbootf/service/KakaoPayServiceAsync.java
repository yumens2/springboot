package com.example.springbootf.service;

import com.example.springbootf.entity.Customer;
import com.example.springbootf.entity.ProdInfo;
import com.example.springbootf.repository.CustomerRepository;
import com.example.springbootf.repository.ProdInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Async
@Component
@RequiredArgsConstructor
public class KakaoPayServiceAsync {
    @Autowired
    private CustomerRepository custRepository;
    @Autowired
    private ProdInfoRepository prodInfoRepository;

    /**
     * 투자하기 API
     * 연산작업이 많은 작업으로 가정하여 트랜잭션 분리작업영역
     * @param customer
     * @param prodTmp
     */
    public void invertProdCommit(Customer customer, ProdInfo prodTmp){

        log.info("DB Start"); // DB 저장
        try{
            ///////////////////////////////////////////////////////////////////////////
            //@D 테스트코드
            //@L 강제 딜레이를 줘서 거래 몰리는상황 재현
            ///////////////////////////////////////////////////////////////////////////
//            Thread.sleep(5000);  // 5초 멈추기 (오래 처리 중 가정)

            ///////////////////////////////////////////////////////////////////////////
            //@D 1. 고객투자이력원장 저장
            //@L CUSTOMER TABLE
            ///////////////////////////////////////////////////////////////////////////
            Date date = new Date();
            customer.setStartedAt(date);
            custRepository.save(customer);
            
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("DB End");

    }
}
