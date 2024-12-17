package com.example.todolist.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter{

    private static final String[] WHITE_LIST = {"/","/users","/login","/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;


        //Authenticationentrypoint
        //whitelist에 포함되지 않으면 로직 수행
        if(!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);

            if(session==null) {
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"로그인 필요");
                return;
            }

            //쿠키로 구현한 부분
//            Cookie[] cookies = httpRequest.getCookies();
//            if(cookies==null){
//                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"로그인 필요");
//                return;
////                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
//            }
//            boolean used = false;
//            for(Cookie cookie : cookies) {
//                if(cookie.getName().equals("userId")){
//                    used = true;
//                    String cookieValue = cookie.getValue();
//                }
//            }
//            if(!used) {
//                return;
//            }
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }
}
