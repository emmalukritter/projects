package com.example.first;

import com.example.first.dao.calculatordao;
import com.example.first.model.Calculator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));

        int sum=a+b;


        calculatordao dao=new calculatordao();
        Calculator c1= dao.getcalc(a,b,sum);


        request.setAttribute("a",c1);


        request.getRequestDispatcher("result.jsp").forward(request,response);
    }
}
