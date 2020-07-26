package com.demo.mqsender.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo/queue")
public class DemoController {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "cafe.topic";

    @Autowired
    public DemoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/default")
    public String testMessagePub() {
        log.info("call");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "order.coffee.first", "Message");
        return "OK";
    }

}
