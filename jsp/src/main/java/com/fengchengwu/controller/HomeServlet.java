package com.guoguochen.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//no web.xml coding
@WebServlet(name = "HomeServlet", value = "/home") //url
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //week7 code
        //user click the link - request come here - doget()
        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
