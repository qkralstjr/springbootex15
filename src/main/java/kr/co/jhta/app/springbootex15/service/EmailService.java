package kr.co.jhta.app.springbootex15.service;

import kr.co.jhta.app.springbootex15.entity.EmailMessage;

public interface EmailService {
    public String sendMail(EmailMessage emailMessage);
}
