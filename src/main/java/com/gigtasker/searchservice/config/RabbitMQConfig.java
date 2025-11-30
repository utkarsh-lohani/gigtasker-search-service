package com.gigtasker.searchservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.MessagingMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String TASK_EXCHANGE_NAME = "task-exchange";
    public static final String SEARCH_QUEUE_NAME = "search.task.events.queue";
    // Listen to all task events (created, updated, etc.)
    public static final String ROUTING_KEY = "task.#";

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(TASK_EXCHANGE_NAME);
    }

    @Bean
    public Queue searchQueue() {
        // boolean durable = true
        return new Queue(SEARCH_QUEUE_NAME, true);
    }

    @Bean
    public Binding searchBinding() {
        return BindingBuilder.bind(searchQueue())
                .to(taskExchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        MessagingMessageConverter converter = new MessagingMessageConverter();
        converter.setPayloadConverter(new JacksonJsonMessageConverter());
        return converter;
    }
}
