package com.guoguochen.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FrontEndAuthenticationFilter implements Filter {

    private HttpServletRequest httpRequest;
    private static final String[] loginRequiredURLs ={"/updateUser","/logout","/myCart"};

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        httpRequest = (HttpServletRequest) request;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if(path.startsWith("/admin/")){
            chain.doFilter(request,response);
            return;
        }
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("userList")!=null);

        String loginURI = httpRequest.getContextPath() +"/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");

        if(isLoggedIn && (isLoginRequest || isLoginPage)){
            httpRequest.getRequestDispatcher("/").forward(request,response);
        }else if(!isLoggedIn || isLoginRequired()){
            String loginPage = "/login";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
            dispatcher.forward(request,response);
        }else{
            chain.doFilter(request,response);
        }


    }
    private boolean isLoginRequired(){
        String requestURL = httpRequest.getRequestURL().toString();
        for(String loginRequiredURL : loginRequiredURLs){
            if(requestURL.contains(loginRequiredURL)){
                return true;
            }
        }
        return false;
    }
}
