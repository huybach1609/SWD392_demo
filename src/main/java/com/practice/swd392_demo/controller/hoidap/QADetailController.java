package com.practice.swd392_demo.controller.hoidap;

import com.practice.swd392_demo.DAL.AnswerDAO;
import com.practice.swd392_demo.DAL.DepartmentDAO;
import com.practice.swd392_demo.DAL.QuestionDAO;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User demoUser = (User) session.getAttribute("userSession");
        if (demoUser == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        }

        // 1 view detail, 2 delete, 3 update, 4 tra loi
        int action = Integer.parseInt(req.getParameter("action"));
        String qId = req.getParameter("questionId");
        int qIdint = Integer.parseInt(qId);

        IQuestionRepository repo = new QuestionRepository();
        Question question = repo.GetQuestion(qIdint);

        departments = DepartmentDAO.ins.getList();

        Answer ans = AnswerDAO.ins.getByQuestionId(qIdint);

        int cauhoidatraloi = CalcNum();
        int totalCauhoi = QuestionDAO.ins.getList().toArray().length;
        System.out.println(totalCauhoi);

        req.setAttribute("num1", cauhoidatraloi);
        req.setAttribute("num2", totalCauhoi - cauhoidatraloi);

        if(demoUser.getDepartment() != null) {
            req.setAttribute("departmentName", demoUser.getDepartment().getDepartmentName());
        }
        req.setAttribute("ans", ans);
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
        int questionId = Integer.parseInt(req.getParameter("quesId"));

        HttpSession session = req.getSession();
        User demoUser = (User) session.getAttribute("userSession");
        if (demoUser == null) {
            // return to error page no have session
        }
        Answer ans = new Answer();

        ans.setTitle(title);
        ans.setContent(content);
        ans.setAnswerDate(new Date());
        ans.setAnsweredId(demoUser.getId());
        ans.setQuestionId(questionId);

        IAnswerRepository repository = new AnswerRepository();
        repository.InsertAnswer(ans);

        resp.sendRedirect(req.getContextPath() + "/hoidap/details?questionId="+questionId+"&action=4");
    }

    private int CalcNum() {
        int cauhoidatraloi = 0;
        for (Question q : QuestionDAO.ins.getList()) {
            if (AnswerDAO.ins.getByQuestionId(q.getId()) != null) {
                cauhoidatraloi++;
            }
        }
        return cauhoidatraloi;
    }


}
