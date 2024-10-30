package com.practice.swd392_demo.controller.hoidap;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.models.Answer;
import com.practice.swd392_demo.models.Department;
import com.practice.swd392_demo.models.Question;
import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.answer.AnswerRepository;
import com.practice.swd392_demo.repository.answer.IAnswerRepository;
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
import java.util.UUID;


@WebServlet(name = "QuestionDetails", urlPatterns = {"/hoidap/details"})
public class QADetailController extends HttpServlet {

    List<Department> departments;
    private String action;
    private int qIdint;

    @Override
    public void init() throws ServletException {
        qIdint = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 view detail, 2 delete, 3 update, 4 tra loi
        if (action == null && qIdint == 0) {
            action = req.getParameter("action");
            String qId = req.getParameter("questionId");
            qIdint = Integer.parseInt(qId);
        }

        IQuestionRepository repo = new QuestionRepository();
        Question question = repo.GetQuestion(qIdint);

        departments = new ArrayList<>();
        sampleList();

        req.setAttribute("departments", departments);
        req.setAttribute("question", question);
        req.setAttribute("action", action);
        req.setAttribute("navIndex", 3);
        req.getRequestDispatcher("/view/hoidap/hoidapDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ansTitle,ansDepartment,ansContent
        String title = req.getParameter("ansTitle");
        String content = req.getParameter("ansContent");
        int departmentId = Integer.parseInt(req.getParameter("ansDepartment"));
        int questionId = Integer.parseInt(req.getParameter("quesId"));

        HttpSession session = req.getSession();
        User demoUser = (User) session.getAttribute("userSession");
        if (demoUser == null) {
            // return to error page no have session
            demoUser = new User(1, "Demo", "User", "Example", AccountRole.USER); // replace with actual user if needed
        }
        Answer ans = new Answer();

        ans.setTitle(title);
        ans.setContent(content);
        ans.setAnswerDate(new Date());
        ans.setAnsweredId(demoUser.getId());
        ans.setQuestionId(questionId);
        IAnswerRepository repository = new AnswerRepository();
        repository.InsertAnswer(ans);
        action = "4";
        qIdint = questionId;
        doGet(req, resp);
    }

    private void sampleList() {
        departments.add(new Department(1, "Cục hợp tác quốc tế"));
        departments.add(new Department(2, "Cục quản lí chất lượng"));
        departments.add(new Department(3, "Cục Tổ chức cán bộ"));
        departments.add(new Department(4, "Cục Nhà giáo và Cán bo quản lý giáo dục"));
    }

}
