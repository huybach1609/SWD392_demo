package com.practice.swd392_demo.services.email;

import org.modelmapper.internal.bytebuddy.utility.RandomString;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailService implements IEmailService {

    static final String FROM = "anemosnguyen2409@gmail.com";
    static final String PASSWORD = "mfsmllcixufmrbrm";
    static final String REGISTRATION_VERIFICATION_SUBJECT = "Registration verification";
    static final String REGISTRATION_VERIFICATION_BODY = "Thân gửi [[name]],<br>" +
            "Địa chỉ email của bạn đã được đăng ký cho web Dịch vụ công của Bộ Giáo dục và Đào tạo<br>" +
            "Bấm vào liên kết dưới đây để xác thực đăng ký:<br>" +
            "<h3><a href=\"[[URL]]\" target=\"_self\">Xác thực</a></h3><br>" +
            "Cảm ơn,<br>\"\n" +
            "Dịch vụ công của Bộ Giáo dục và Đào tạo\";";
    static final String WEB_URL = "http://localhost:8080/SWD392_demo_war";
    @Override
    public void sendRegisterVerificationEmail(String toEmail, String userLastName, String activationKey)
                            throws MessagingException{
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.setFrom(FROM);

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            msg.setSubject(REGISTRATION_VERIFICATION_SUBJECT);

            msg.setSentDate(new Date());

            String content = REGISTRATION_VERIFICATION_BODY
                    .replace("[[name]]", userLastName)
                    .replace("[[URL]]", WEB_URL + "/account/register/verification?activationKey=" + activationKey);

            msg.setContent(content, "text/HTML; charset=UTF-8");

            Transport.send(msg);
            System.out.println("Gửi email thành công");
        } catch (MessagingException e) {
            e.fillInStackTrace();
        }
    }
}
