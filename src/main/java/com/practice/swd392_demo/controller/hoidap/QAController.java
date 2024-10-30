package com.practice.swd392_demo.controller.hoidap;


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
        if(demoUser == null){

        }
        departments = new ArrayList<>();
        IQuestionRepository repository = new QuestionRepository();
        questions = repository.getList();

        sampleList();
        req.setAttribute("questions", questions);
        req.setAttribute("isStaff", demoUser.getRole() == AccountRole.STAFF);
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
        if(demoUser == null){
            demoUser = new User(1, "Demo", "User", "Example", AccountRole.USER); // replace with actual user if needed
        }
        // Create a new Question with demo data for user and department
        Department selectedDepartment = departments.stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst()
                .orElse(null);

        if (selectedDepartment != null) {
            // Create and insert new question
            Question newQuestion = new Question(
                    questions.size() + 1, // Generate ID
                    title,
                    content,
                    currentDate,
                    demoUser.getId(), // Use demoUser's ID
                    departmentId,
                    new ArrayList<>(), // Empty answers list for new question
                    demoUser,
                    selectedDepartment
            );

            repository.InsertQuestion(newQuestion);
            questions = repository.getList();

            req.setAttribute("questions", questions);
            req.setAttribute("departments", departments);
            req.setAttribute("mess", "Question submitted successfully!");
            req.setAttribute("navIndex", 3);
            req.setAttribute("isStaff", demoUser.getRole() == AccountRole.STAFF);
            req.getRequestDispatcher("/view/hoidapList.jsp").forward(req, resp);
        } else {
            req.setAttribute("mess", "Error: Selected department not found.");
            req.getRequestDispatcher("/view/hoidapList.jsp").forward(req, resp);
        }
    }
    private void sampleList(){
       departments.add(new Department(1, "Cục hợp tác quốc tế"));
        departments.add(new Department(2, "Cục quản lí chất lượng"));
        departments.add(new Department(3, "Cục Tổ chức cán bộ"));
        departments.add(new Department(4, "Cục Nhà giáo và Cán bo quản lý giáo dục"));
    }
}
