package it.caoxin.smarthome.app.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null) {
            System.out.println("登录成功...");
            return true;
        }
        else {
            System.out.println("登录失败...");
            response.sendRedirect(request.getContextPath());
            return false;
        }
    }
}
