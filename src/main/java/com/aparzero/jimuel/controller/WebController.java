package com.aparzero.jimuel.controller;

import com.aparzero.jimuel.domain.Message;
import com.aparzero.jimuel.domain.Response;
import com.aparzero.jimuel.publisher.RabbitMQJSONProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/api/v1")
public class WebController {

    private final RabbitMQJSONProducer producer;

    public WebController(final RabbitMQJSONProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<Response> sendJson(@RequestBody Message message){
        producer.sendJsoMessage(message);
        return Response.success("SUCCESS");
    }
}
