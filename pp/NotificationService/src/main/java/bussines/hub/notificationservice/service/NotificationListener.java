package bussines.hub.notificationservice.service;

import bussines.hub.notificationservice.config.UserRegistrationEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class NotificationListener {

    private JavaMailSender emailSender;

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.body}")
    private String emailBody;

    @Value("${email.confirmation-link}")
    private String confirmationLink;

    @KafkaListener(topics = "user-registration", groupId = "notification-service-group")
    public void listen(UserRegistrationEvent event) {
        sendEmail(event.getEmail(), event.getConfirmationCode());
    }

    private void sendEmail(String to, String confirmationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(to);
        message.setSubject(emailSubject);
        String body = emailBody.replace("{confirmationLink}", confirmationLink + confirmationCode);
        message.setText(body);
        emailSender.send(message);
    }
}

