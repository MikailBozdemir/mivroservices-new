package com.hepsisurada.notificationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
  @Autowired
  EmailService emailService;
  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }

  @KafkaListener(topics="notificationTopic")
  public void handleNotification(OrderPlacedEvent orderPlacedEvent){
    emailService.sendOrderNotificationEmail("mikail.bozdemir@gmail.com", orderPlacedEvent.getOrderNumber());
    log.info("received notificatioon for {}",orderPlacedEvent.getOrderNumber());
  }
}
