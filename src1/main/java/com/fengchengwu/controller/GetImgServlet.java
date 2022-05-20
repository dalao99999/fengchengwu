package com.guoguochen.controller;

import com.guoguochen.dao.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "GetImgServlet", value = "/GetImg")
public class GetImgServlet extends HttpServlet {
    Connection con = null;
    public void init() throws ServletException{
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if(request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        ProductDao productDao = new ProductDao();
        try {
            byte[] imgByte = new byte[0];
            imgByte = productDao.getPictureById(id,con);
            if(imgByte != null){
                response.setContentType("image/gif");
                OutputStream out = response.getOutputStream();
                out.write(imgByte);
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
