package kr.co.jhta.app.springbootex15;

import kr.co.jhta.app.springbootex15.entity.EmailMessage;
import kr.co.jhta.app.springbootex15.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/sendMail/email")
    public String sendMail(@RequestParam String receiver, Model model){
        EmailMessage emailMessage =
                EmailMessage.builder()
                        .to(receiver)
                        .message("message")
                        .subject("인증코드 발송")
                        .build();
        String code = emailService.sendMail(emailMessage);
        model.addAttribute("code", code);
        return "verify";
    }
    @GetMapping("/form")
    public String formm(Model model){

        return "form";
    }


}
