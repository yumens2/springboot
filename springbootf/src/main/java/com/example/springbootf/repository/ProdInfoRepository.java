package com.example.springbootf.repository;

import com.example.springbootf.entity.ProdInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 삼품 레파지토리
 */
public interface ProdInfoRepository extends CrudRepository<ProdInfo,Integer> {

    ///////////////////////////////////////////////////////////////////////////
    //@D 전체 상품조회
    //@L 조건 상품코드(사용)
    ///////////////////////////////////////////////////////////////////////////
    ProdInfo findById (int value);

    ///////////////////////////////////////////////////////////////////////////
    //@D 판매중 상품조회
    //@L 조건 날짜(사용)
    ///////////////////////////////////////////////////////////////////////////
    @Query(value = "select product_Id,title,total_Investing_Amount" +
            " ,now_Investing_Amount" +
            " ,now_Investing_cnt" +
            " ,started_At" +
            " ,finished_At" +
            " ,STATE" +
            " from prod_info A where ?1 between started_At and finished_At",nativeQuery = true)
    List<ProdInfo> findProdSearch(String value);

    ///////////////////////////////////////////////////////////////////////////
    //@D 판매중 상품조회2
    //@L 조건 상풐코드,날짜(사용)
    ///////////////////////////////////////////////////////////////////////////
    @Query(value = "select * from prod_info where product_id = ?1 and ?2 " +
            "between started_at and finished_at",nativeQuery = true)
    ProdInfo findProdBetSearch(int value1,String value2);


}
