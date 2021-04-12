package com.robinvandenhurk.gateway.example.serviceuser.configuration.messaging.binding;

import com.robinvandenhurk.gateway.example.serviceuser.configuration.messaging.exchange.UserExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: DeletionBinding
 */

@Configuration
public class DeletionBinding {

    private UserExchange exchange;
    private String routingKey = "ovp.user.deletion";
    private String name = "ovp.user.deletion";

    @Autowired
    public DeletionBinding(UserExchange exchange) {
        this.exchange = exchange;
    }

    @Bean
    public Queue deletionQueue() {
        return new Queue(name, true);
    }

    @Bean
    public Binding getDeletionBinding() {
        return BindingBuilder.bind(deletionQueue()).to(exchange.getUserExchange()).with(routingKey).noargs();
    }
}
