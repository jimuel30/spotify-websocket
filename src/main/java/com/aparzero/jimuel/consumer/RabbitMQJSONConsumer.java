package com.aparzero.jimuel.consumer;

import com.aparzero.jimuel.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQJSONConsumer.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    public RabbitMQJSONConsumer(final SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

//    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
//    public void consumeJsonMessage(Signal signal){
//        LOG.info("MESSAGE RECEIVED: {}",signal);
//        final String topic = "/topic/"+signal.getKey();
//        simpMessagingTemplate.convertAndSend(topic, signal);
//    }
@RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
public void consumeJsonMessage(final Message message){
    LOG.info("MESSAGE RECEIVED: {}",message);
    final String topic = "/topic/"+message.getTopic();
    simpMessagingTemplate.convertAndSend(topic, message);
}

}
