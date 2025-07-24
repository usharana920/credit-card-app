package org.usra.creditApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.usra.creditApp.constants.Constants;
import org.usra.creditApp.dto.EmailRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void processEmail(EmailRequest emailRequest) {
        log.info("Sending Email to: {}. Status: {} ", emailRequest.getReceiverEmail(), emailRequest.getSubject() );
        sendEmail(emailRequest);
    }


    private void sendEmail(EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Constants.SENDER_EMAIL);
        message.setTo(emailRequest.getReceiverEmail());
        message.setText(emailRequest.getEmailPayload());
        message.setSubject(emailRequest.getSubject());
        javaMailSender.send(message);
        log.info("Email sent to {} successfully. ", emailRequest.getReceiverEmail());
    }

}
