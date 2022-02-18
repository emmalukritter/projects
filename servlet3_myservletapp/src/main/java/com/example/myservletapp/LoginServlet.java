package com.example.myservletapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginpage")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        int status = Userdao.login(name, password);
        if (status > 0) {
            request.getRequestDispatcher("dashboard.html").include(request, response);
        } else {
            out.println("Sorry! unable to save record");
        }
    }
}
