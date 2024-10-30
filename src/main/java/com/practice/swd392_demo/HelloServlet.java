package com.practice.swd392_demo;

import java.io.*;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "home", value = "/home")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        User user = new User(1, "John", "A.", "Doe", AccountRole.STAFF);
        session.setAttribute("userSession", user);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

}