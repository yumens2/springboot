package com.example.springbootf.consts;


public interface KakaoPayConst {

    //시스템점검
    public interface SYS_CTRL{
        public  static  final  Boolean SYS_OFF = false;
        public  static  final  String ERR_CODE = "E999";
        public  static  final  String ERR_MGS  = "시스템점검중 입니다.";
    }

    //메시지코드
    public interface RES_DVCD{
        public  static  final  String SUSS_CD = "0000"; //정상
        public  static  final  String SUSS_NM = "정상 입니다."; //정상
        public  static  final  String ERR_CD  = "E000"; //시스템점검중
        public  static  final  String ERR_NM  = "시스템 점검중입니다."; //시스템점검중
    }


}
