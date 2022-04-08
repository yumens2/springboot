package com.example.springbootf.repository;

import com.example.springbootf.entity.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * 메시지관리 레파지토리
 */
public interface MessageRepository extends CrudRepository<Message,Integer> {
    ///////////////////////////////////////////////////////////////////////////
    //@D 메시지 조회
    //@L 조건 메시지코드(사용)
    ///////////////////////////////////////////////////////////////////////////
    Message findByCode(String value);
}
