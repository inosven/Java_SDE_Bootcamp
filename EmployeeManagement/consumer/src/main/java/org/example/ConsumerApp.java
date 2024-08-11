package org.example;

import org.example.service.SQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages = "org.example")
public class ConsumerApp
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApp.class, args);
        SQSMessageService messageService = context.getBean(SQSMessageService.class);

        messageService.receiveMessage();
    }
}
