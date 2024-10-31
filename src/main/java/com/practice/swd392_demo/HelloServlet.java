package com.practice.swd392_demo;

import java.io.*;

import com.practice.swd392_demo.DAL.UserDAO;
import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "home", value = "/home")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

//        staff roll
//        User user = UserDAO.ins.getByID(5);
//        user roll
        User user= UserDAO.ins.getByID(6);
        System.out.println(user);
        session.setAttribute("userSession", user);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

}