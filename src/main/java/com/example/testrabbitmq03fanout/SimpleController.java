package com.example.testrabbitmq03fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //    Замена AmqpTemplate template;
    @Autowired
    RabbitTemplate template;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit")
    @ResponseBody
    String emit() {
        log.info("Emit to exchange-example-3");
        template.setExchange("exchange-example-3");
        for(int i = 0;i<10;i++) {
            template.convertAndSend("Fanout message" + i);
        }
        return "Emit to exchange-example-3";
    }
}
