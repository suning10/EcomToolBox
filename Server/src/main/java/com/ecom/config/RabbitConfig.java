//package com.ecom.config;
//
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class RabbitConfig {
//
//    @Value("${ecom.rabbitmq.queue}")
//    private String queueName;
//
//    @Value("${ecom.rabbitmq.exchange}")
//    private String exchangeName;
//
//    @Value("${ecom.rabbitmq.routing-key}")
//    private String routingKey;
//
//    @Bean
//    public Queue queue(){
//        return new Queue(queueName,true);
//    }
//
//    @Bean
//    public DirectExchange exchange(){
//        return new DirectExchange(exchangeName);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, DirectExchange directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
//    }
//
//    @Bean
//    public MessageConverter jsonConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate rabbitTempalte(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonConverter());
//        log.info("start register rabbitMQ");
//        log.info(this.queueName);
//        log.info(this.exchangeName);
//        return rabbitTemplate;
//    }
//}
