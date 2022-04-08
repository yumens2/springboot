package com.example.springbootf.interceptor;

import com.example.springbootf.consts.KakaoPayConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeneralPurposerseInterceptor implements HandlerInterceptor {
    private final Logger LOG = LoggerFactory.getLogger(GeneralPurposerseInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("==== Start Intercepter ===");

        ///////////////////////////////////////////////////////////////////////////
        //@D 1. 시스템점검
        //@L KakaoPayConst 상수로 컨트롤 하도록 구성함.
        ///////////////////////////////////////////////////////////////////////////
        if(KakaoPayConst.SYS_CTRL.SYS_OFF){
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().append("{\"ressCd\" : \""+KakaoPayConst.SYS_CTRL.ERR_CODE+"\" , \"resseName\" : \""+KakaoPayConst.SYS_CTRL.ERR_MGS+"\"}");
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("==== End Intercepter ===");
    }
}
