package kr.co.jhta.app.springbootex15.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.jhta.app.springbootex15.entity.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    public String sendMail(EmailMessage emailMessage) {
        String authNum = createCode();  //인증번호 생성
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());      //메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject());    //메일제목
            mimeMessageHelper.setText(setContext(authNum), true);       //메일본문, HTML 여부
            javaMailSender.send(mimeMessage);                           //메일 발송
            log.info("SUCCESS =>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        return authNum;
    }

    //인증 번호
    public String createCode() {
        return ""+(int)(Math.random()*10000);
    }

    //thymeleaf를 통한 html적용
    private String setContext(String authNum) {
        Context context = new Context();
        context.setVariable("authNum", authNum);
        return springTemplateEngine.process("email", context);
    }

}
