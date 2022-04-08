package com.example.springbootf.service;

import com.example.springbootf.consts.KakaoPayConst;
import com.example.springbootf.dto.CustomerDto;
import com.example.springbootf.dto.ProdInfoDto;
import com.example.springbootf.entity.Customer;
import com.example.springbootf.entity.Message;
import com.example.springbootf.entity.ProdInfo;
import com.example.springbootf.exception.UserException;
import com.example.springbootf.repository.CustomerRepository;
import com.example.springbootf.repository.MessageRepository;
import com.example.springbootf.repository.ProdInfoRepository;
import com.example.springbootf.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class KakaoPayService {
    @Autowired
    private CustomerRepository custRepository;
    @Autowired
    private ProdInfoRepository prodInfoRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private KakaoPayServiceAsync kakaoPayServiceAsync;

    /**
     * 투자하기 API
     * @param customer
     * @return CustomerDto
     */
    public CustomerDto investProd(Customer customer) throws UserException{

        Date date = new Date();
        CustomerDto responseDate = new CustomerDto();
        DateUtils nowDate = new DateUtils();

        //0000 정상 B000 비지니스에러  시스템에러 E000
        String resCd = KakaoPayConst.RES_DVCD.SUSS_CD;
        String resMsg = KakaoPayConst.RES_DVCD.SUSS_NM;

        ///////////////////////////////////////////////////////////////////////////
        //@D 1. 상품정보 조회
        //@L
        ///////////////////////////////////////////////////////////////////////////
        ProdInfo prodTmp =  prodInfoRepository.findById(customer.getProductId());

        ///////////////////////////////////////////////////////////////////////////
        //@D 2. 투자하기 조건에 부합여부 채크
        //@L
        ///////////////////////////////////////////////////////////////////////////
        if(prodTmp == null){
            //존재하지 않는 상품입니다
            throw new UserException("B003", HttpStatus.BAD_REQUEST,"");
        }else{
            //판매중 상품조회
            prodTmp =  prodInfoRepository.findProdBetSearch(customer.getProductId(),nowDate.getNowDate());

            //판매기간종료
            if(prodTmp == null){
                //판매기간 경과 된 상품입니다.
                throw new UserException("B002", HttpStatus.BAD_REQUEST,"");
            }else{
                //한도초과 채크
                if(prodTmp.getTotalInvestingAmount().compareTo(prodTmp.getNowInvestingAmount())==0){
                    //마감되었습니다.(sold-out) 총 : [1] 금액을 초과할 수 없습니다.
                    throw new UserException("B004", HttpStatus.BAD_REQUEST,prodTmp.getTotalInvestingAmount().toString());
                //한도초과 채크
                }else if(prodTmp.getTotalInvestingAmount().compareTo(prodTmp.getNowInvestingAmount().add(customer.getInvestAmt()))<0){

                    String str = "총 : " + prodTmp.getTotalInvestingAmount() + "금액을 초과할수없습니다.("+prodTmp.getNowInvestingAmount().add(customer.getInvestAmt())+")";
                    //투자가능금액을 초과하였습니다. [1]
                    throw new UserException("B001", HttpStatus.BAD_REQUEST,str);

                //마감채크
                }else if("모집완료".equals(prodTmp.getState())){
                    //모집마감상태 입니다. 투자 불가합니다
                    throw new UserException("B000", HttpStatus.BAD_REQUEST,"");
                }else{

                    ///////////////////////////////////////////////////////////////////////////
                    //@D 3. 고객정보이력저장
                    //@L 상품원장 갱신
                    //무거운작업 이라고 가정하여 트랜젝션 분리를 통하여 처리하도록 수행.
                    ///////////////////////////////////////////////////////////////////////////
                    kakaoPayServiceAsync.invertProdCommit(customer,prodTmp);

                    ///////////////////////////////////////////////////////////////////////////
                    //@D 4. 상품원장 갱신 저장
                    //@L 투자금액(잔액), 투자인원 ,상품상태 ,
                    ///////////////////////////////////////////////////////////////////////////
                    prodTmp.setNowInvestingAmount(prodTmp.getNowInvestingAmount().add(customer.getInvestAmt()));
                    prodTmp.setNowInvestingCnt(prodTmp.getNowInvestingCnt()+1);

                    if(prodTmp.getTotalInvestingAmount().compareTo(prodTmp.getNowInvestingAmount())==0){
                        prodTmp.setState("모집완료");
                    }else{
                        prodTmp.setState("모집중");
                    }

                    prodInfoRepository.save(prodTmp);
                }
            }
        }

        return responseDate;
    }

    /**
     * 마이페이지 조회
     * @param user
     * @return CustomerDto
     */
    public CustomerDto getMyPage(int user) throws UserException{

        List<Customer> customerList = null;
        CustomerDto customerDto = new CustomerDto();

        String resCd = KakaoPayConst.RES_DVCD.SUSS_CD;
        String resMsg = KakaoPayConst.RES_DVCD.SUSS_NM;

        ///////////////////////////////////////////////////////////////////////////
        //@D 1. 고객투자이력 조회
        //@L
        ///////////////////////////////////////////////////////////////////////////
        customerList = custRepository.findCustSearch(user);

        if(user == 0){
            //필수입력항목 확인해주세요. [1]
            throw new UserException("B005", HttpStatus.BAD_REQUEST,"고객번호");
        }else if(customerList.size()<1){
            //조회결과가 없습니다.
            throw new UserException("B006", HttpStatus.BAD_REQUEST,"");
        }

        customerDto.setResseCd(resCd);
        customerDto.setResseName(resMsg);
        customerDto.setCustomers(customerList);

        return customerDto;
    }

    /**
     * 판매중인 전체상품리스트조회
     * @param prodInfoDto
     * @return ProdInfoDto
     */
    public ProdInfoDto getProdList(ProdInfoDto prodInfoDto) throws Exception{
        DateUtils dateUtils = new DateUtils();
        List<ProdInfo> prodinfoList = null;
        //0000 정상 B000 비지니스에러  시스템에러 E000
        String resCd  = KakaoPayConst.RES_DVCD.SUSS_CD;
        String resMsg = KakaoPayConst.RES_DVCD.SUSS_NM;

        ///////////////////////////////////////////////////////////////////////////
        //@D 1. 판매중 상품 조회
        //@L
        ///////////////////////////////////////////////////////////////////////////
        prodinfoList = (List<ProdInfo>)prodInfoRepository.findProdSearch(dateUtils.getNowDate());

        prodInfoDto.setResseCd(resCd);
        prodInfoDto.setResseName(resMsg);
        prodInfoDto.setProdInfos(prodinfoList);

        return prodInfoDto;
    }

    /**
     * 에러메시지코드
     * @param message
     * @return message
     */
    public Message getMessage(Message message){
        try{
            ///////////////////////////////////////////////////////////////////////////
            //@D 1. 메시지 조회
            //@L
            ///////////////////////////////////////////////////////////////////////////
            message = (Message) messageRepository.findByCode(message.getCode());
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
