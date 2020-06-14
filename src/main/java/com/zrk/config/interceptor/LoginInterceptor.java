package com.zrk.config.interceptor;

import com.zrk.config.webmvc.RequestHolder;
import com.zrk.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userIdStr = request.getHeader("X-ZRK-userId");
        Long userId = null;
        if(StringUtils.isNotEmpty(userIdStr)){
            userId = Long.valueOf(userIdStr);
        }
        User user = new User();
        user.setId(userId);
        RequestHolder.add(user);
        return true;
    }
}
