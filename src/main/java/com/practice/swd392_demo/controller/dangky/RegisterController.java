package com.practice.swd392_demo.controller.dangky;

import com.practice.swd392_demo.exceptions.EmailDuplicationException;
import com.practice.swd392_demo.exceptions.IdCardNoDuplicationException;
import com.practice.swd392_demo.models.Account;
import com.practice.swd392_demo.models.User;
import com.practice.swd392_demo.repository.account.AccountRepository;
import com.practice.swd392_demo.repository.user.UserRepository;
import com.practice.swd392_demo.services.account.AccountService;
import com.practice.swd392_demo.services.account.IAccountService;
import com.practice.swd392_demo.services.email.EmailService;
import com.practice.swd392_demo.services.email.IEmailService;
import com.practice.swd392_demo.services.user.IUserService;
import com.practice.swd392_demo.services.user.UserService;
import com.practice.swd392_demo.utitlities.Utility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@WebServlet(name = "RegisterController", urlPatterns = {"/account/register"})
public class RegisterController extends HttpServlet {
    private IUserService _userService;
    private IAccountService _accountService;
    private IEmailService _emailService;
    public final String DUPLICATED_EMAIL_MESSAGE = "Email da duoc dang ky";
    public final String DUPLICATED_ID_CARD_NO_MESSAGE = "CCCD da duoc dang ky";
    public final String EMAIL_INVALID_MESSAGE = "Co loi trong qua trinh gui email xac thuc";
    public final String REGISTRATION_SUCCESSFULLY_MESSAGE = "Da gui email xac nhan dang ky thanh cong";
    public String registrationMessage = REGISTRATION_SUCCESSFULLY_MESSAGE;
    public void init() {
        _userService = new UserService(new UserRepository());
        _accountService = new AccountService(new AccountRepository());
        _emailService = new EmailService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getRequestDispatcher("/view/register/register.jsp").forward(request, response);
        }catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");
        String dobStr = req.getParameter("dob"); // Expecting dob as a string, e.g., "yyyy-MM-dd"
        String idCardNo = req.getParameter("idCardNo");
        String homeTown = req.getParameter("homeTown");
        String tribeStr = req.getParameter("tribe");
        String genderStr = req.getParameter("gender");
        java.util.Date dob;
        Date dobConverted;
        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
            dobConverted = new java.sql.Date(dob.getTime());
        } catch (ParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format for dob. Expected yyyy-MM-dd.");
            return;
        }
        int tribe;
        int gender;
        try {
            tribe = Integer.parseInt(tribeStr);
            gender = Integer.parseInt(genderStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tribe ID.");
            return;
        }
        register(email, password, firstName, middleName, lastName, dobConverted, idCardNo,
                homeTown, tribe, gender, req, resp);

    }
    private void register(String email, String password, String firstName, String middleName,
                          String lastName, Date dob, String idCardNo, String homeTown, int tribe,
                          int gender, HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        try {
            String activationKey = Utility.GenerateActivationKey(64);
            User user = _userService.addUser(firstName, middleName, lastName, dob, idCardNo, homeTown, tribe, gender, null);
            Account account = _accountService.addAccount(email, password, activationKey, user.getId());
            try {
                _emailService.sendRegisterVerificationEmail(email, lastName, activationKey);
            }
            catch (MessagingException e) {
                registrationMessage = EMAIL_INVALID_MESSAGE;
                _userService.removeUser(user);
                _accountService.removeAccount(account);
                e.fillInStackTrace();
            }
        }
        catch(EmailDuplicationException emailDuplicationException){
            registrationMessage = DUPLICATED_EMAIL_MESSAGE;
            emailDuplicationException.fillInStackTrace();
        }
        catch (IdCardNoDuplicationException idCardNoDuplicationException){
            registrationMessage = DUPLICATED_ID_CARD_NO_MESSAGE;
            idCardNoDuplicationException.fillInStackTrace();
        }
        finally {
            response.sendRedirect(request.getContextPath() + "/account/register?registrationMessage="+registrationMessage);
        }
    }
    public void destroy() {
    }
}