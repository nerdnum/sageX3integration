package na.com.meatco.sageX3integration.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailProcessor implements Processor {

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    Environment environment;

    @Override
    public void process(Exchange exchange) throws Exception {

        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        log.info("Exception caught in mail processor: " + e.getMessage());

        String messageBody = "Exception occured in camel route: " + e.getMessage();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(environment.getProperty("mailFrom"));
        mailMessage.setTo(environment.getProperty("mailTo"));
        mailMessage.setSubject("Exception occurred in camel route");
        mailMessage.setText(messageBody);

        emailSender.send(mailMessage);

    }
}
