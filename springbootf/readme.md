# 스프링부트 연습
***

### 개발환경
java,Spring boot,JPA,H2 Database
intelliJ,Postman

#### 투자가능 상품조회:
* http://localhost:8080/prod/prodInfo
* HTTP Method GET
* Request Parameter 없음

#### 투자하기 API:
* http://localhost:8080/customer/invest
* HTTP Method PUT
* Request parameter custNo 고객번호 ,productId 상품번호 ,invertAmt 투자금액
 
#### 나의 투자상품 조회 API
* http://localhost:8080/customer/mypage
* HTTP Method POST
* Request parameter custNo 고객번호

### 핵심 문제해결 전략 및 분석
1. Exception 핸들러를 통해 에러처리 통합 구성
2. 메시지처리 핸들러를 통해 통합 구성
3. 투자하기API 처리시 헤비한 프로세스를 가정하여 Service를 영역을 나눠 
   쓰레드/비동기로 프로세스를 구성
4. loggerAspect 통해 서비스별 처리시간 모니터링을 위해 로그 구성
5. 시스템예외 상황발생에 대비하여 Intercepter를 통해 ON/OFF 기능 구성