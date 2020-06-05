package com.zrk.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/03/12
 */
@Slf4j
public class SystemInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("login-request-start-time", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("login-request-start-time");
        if (startTime != null) {
            long cost = System.currentTimeMillis() - startTime;
            log.info("request cost:[" + request.getRequestURI()
                    + ", " + request.getQueryString()
                    + ", USER-AGENT : " + request.getHeader("USER-AGENT")
                    + "] " + cost);
        }
    }
}
