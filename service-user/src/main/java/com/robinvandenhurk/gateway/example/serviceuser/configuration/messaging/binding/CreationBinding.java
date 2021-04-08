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
 * File name: CreationBinding
 */

@Configuration
public class CreationBinding {

    private UserExchange exchange;
    private String routingKey = "ovp.user.creation";
    private String name = "ovp.user.creation";

    @Autowired
    public CreationBinding(UserExchange exchange) {
        this.exchange = exchange;
    }

    @Bean
    public Queue creationQueue() {
        return new Queue(name, true);
    }

    @Bean
    public Binding getCreationBinding() {
        return BindingBuilder.bind(creationQueue()).to(exchange.getUserExchange()).with(routingKey).noargs();
    }
}
