package com.practice.swd392_demo;

import java.io.*;

import com.practice.swd392_demo.DAL.UserDAO;
import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "home", value = "/home/*")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        int selectedId;
        if(pathInfo != null){
            String[] pathParts = pathInfo.split("/");
            if(pathParts.length > 0){
                String value = pathParts[1];
                switch (value) {
                    //staff department 1
                    case "1":
                        selectedId = 5;
                        break;
                    //staff department 2
                    case "2":
                        selectedId = 6;
                        break;
                    //staff department 3
                    case "3":
                        selectedId = 9;
                        break;
                    //staff department 4
                    case "4":
                        selectedId = 10;
                        break;
                    //user
                    default:
                        selectedId = 11;
                        break;
                }
            }
            else{
                selectedId = 11;
            }
        }
        else{
            selectedId = 11;
        }
        User user= UserDAO.ins.getByID(selectedId);
        System.out.println(user);
        session.setAttribute("userSession", user);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

}