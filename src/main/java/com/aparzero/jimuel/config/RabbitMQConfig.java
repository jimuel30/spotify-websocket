package com.aparzero.jimuel.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {


    private final String queueName;

    private final String jsonQueueName;
    private final String exchangeName;

    private final String routingKey;

    private final String routingJsonKey;

    public RabbitMQConfig(@Value("${rabbitmq.queue.name}") final String queueName,
                          @Value("${rabbitmq.json.queue.name}") final String jsonQueueName,
                          @Value("${rabbitmq.routing.json.key}") final String routingJsonKey,
                          @Value("${rabbitmq.exchange.name}") final String exchangeName,
                          @Value("${rabbitmq.routing.key}") final String routingKey) {
        this.queueName = queueName;
        this.jsonQueueName = jsonQueueName;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.routingJsonKey = routingJsonKey;
    }

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public Queue jsonQueue(){
            return new Queue(jsonQueueName);
    }


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory ){
        RabbitTemplate  rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }


}
