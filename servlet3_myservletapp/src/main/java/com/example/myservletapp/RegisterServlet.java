package com.example.myservletapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Registerinsert")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User u1=new User();
        u1.setName(name);
        u1.setPassword(password);
        u1.setEmail(email);
        int status = Userdao.save(u1);
        if (status > 0) {
            request.getRequestDispatcher("index.html").include(request, response);
        } else {
            out.println("Sorry! unable to save record");
        }

    }
}
