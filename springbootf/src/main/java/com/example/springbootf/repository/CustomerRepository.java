package com.example.springbootf.repository;

import com.example.springbootf.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * 고객 레파지토리
 */
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    ///////////////////////////////////////////////////////////////////////////
    //@D 고객투자정보 조회
    //@L 조건 고객번호(사용)
    ///////////////////////////////////////////////////////////////////////////
    @Query(value = "select A.* from customer A where cust_no = ?1",nativeQuery = true)
    List<Customer> findCustSearch(int value);

    ///////////////////////////////////////////////////////////////////////////
    //@D 상품 투자총금액 조회
    //@L 조건 상품코드(사용안함) 고객메인원장에 갱신하는방식으로 변경
    ///////////////////////////////////////////////////////////////////////////
    @Query(value = "select (nvl(select total_investing_amount from prod_info where product_id = a.product_id,0)-sum(invest_amt)) as invest_amt " +
            "from customer A where product_id = ?1 group by product_id",nativeQuery = true)
    double findChkInvestSearch(int value);

}
