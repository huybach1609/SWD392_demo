package com.practice.swd392_demo.controller.hoidap;


import com.practice.swd392_demo.DAL.AccountDAO;
import com.practice.swd392_demo.DAL.AnswerDAO;
import com.practice.swd392_demo.DAL.DepartmentDAO;
import com.practice.swd392_demo.DAL.QuestionDAO;
import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.*;
import com.practice.swd392_demo.repository.question.IQuestionRepository;
import com.practice.swd392_demo.repository.question.QuestionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ListHoiDap", urlPatterns = {"/hoidap/list"})
public class QAController extends HttpServlet {

    List<Question> questions;
    List<Department> departments;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User demoUser = (User) session.getAttribute("userSession");
        if (demoUser == null) {
            resp.sendRedirect(req.getContextPath() +"/home");
        }

        departments = DepartmentDAO.ins.getList();

        IQuestionRepository repository = new QuestionRepository();
        questions = repository.getList();

        // get roleAcc
        AccountRole role = AccountDAO.ins.getByUID(demoUser.getId()).getRole();
        System.out.println(AccountDAO.ins.getStatus());


        int cauhoidatraloi = CalcNum();
        int totalCauhoi = QuestionDAO.ins.getList().toArray().length;
        System.out.println(totalCauhoi);

        req.setAttribute("num1", cauhoidatraloi);
        req.setAttribute("num2", totalCauhoi-cauhoidatraloi);


        req.setAttribute("questions", questions);
        req.setAttribute("isStaff", role == AccountRole.STAFF);
        req.setAttribute("departments", departments);
        req.setAttribute("mess", "hello");
        req.setAttribute("navIndex", 3);
        req.getRequestDispatcher("/view/hoidap/hoidapList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve data from the form
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int departmentId = Integer.parseInt(req.getParameter("departmentId"));
        Date currentDate = new Date();

        // Get repository to handle questions
        IQuestionRepository repository = new QuestionRepository();

        HttpSession session = req.getSession();
        User demoUser = (User) session.getAttribute("userSession");
        if (demoUser == null) {
//            reuturn error page()
        }

        // Create and insert new question
        Question newQuestion = new Question();
        newQuestion.setTitle(title);
        newQuestion.setContent(content);
        newQuestion.setAskDate(currentDate);
        newQuestion.setSenderId(demoUser.getId());
        newQuestion.setDepartmentId(departmentId);

        System.out.println("insert question: " + newQuestion);
        repository.InsertQuestion(newQuestion);
        questions = repository.getList();

        // get roleAcc
        AccountRole role = AccountDAO.ins.getByUID(demoUser.getId()).getRole();


        int cauhoidatraloi = CalcNum();
        int totalCauhoi = QuestionDAO.ins.getList().toArray().length;
        System.out.println(totalCauhoi);

        req.setAttribute("num1", cauhoidatraloi);
        req.setAttribute("num2", totalCauhoi-cauhoidatraloi);


        req.setAttribute("questions", questions);
        req.setAttribute("departments", departments);
        req.setAttribute("mess", "Question submitted successfully!");
        req.setAttribute("navIndex", 3);
        req.setAttribute("isStaff", role == AccountRole.STAFF);

        req.getRequestDispatcher("/view/hoidap/hoidapList.jsp").forward(req, resp);
    }


    private int CalcNum(){
        int cauhoidatraloi = 0;
        for(Question q : QuestionDAO.ins.getList()){
            if( AnswerDAO.ins.getByQuestionId(q.getId())!= null){
                cauhoidatraloi++;
            }
        }
        return cauhoidatraloi;
    }


}
