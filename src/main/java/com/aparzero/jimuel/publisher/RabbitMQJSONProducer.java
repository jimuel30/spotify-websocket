package com.aparzero.jimuel.publisher;

import com.aparzero.jimuel.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONProducer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQJSONProducer.class);
    private final  String exchangeName;
    private final String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJSONProducer(@Value("${rabbitmq.exchange.name}") final String exchangeName,
                                @Value("${rabbitmq.routing.json.key}") final String routingJsonKey,
                                final  RabbitTemplate rabbitTemplate) {
        this.exchangeName = exchangeName;
        this.routingJsonKey = routingJsonKey;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsoMessage(final Message message){
        LOG.info("MESSAGE SENT: {}",message);
        rabbitTemplate.convertAndSend(exchangeName,routingJsonKey,message);
    }


}
