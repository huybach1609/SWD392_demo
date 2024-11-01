package com.practice.swd392_demo.services.email;

import javax.mail.MessagingException;

public interface IEmailService {
    void sendRegisterVerificationEmail(String toEmail, String userLastName, String activationKey)
            throws MessagingException;
}
