package com.jicl.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author : xianzilei
 * @date : 2019/12/2 08:33
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String redirectUrl = null;
        if (url.contains("/admin")) {
            redirectUrl = "/admin/toAdminLoginPage";
        } else {
            redirectUrl = "/user/toLoginPage";
        }
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(redirectUrl);
            return false;
        }
        return true;
    }
}
