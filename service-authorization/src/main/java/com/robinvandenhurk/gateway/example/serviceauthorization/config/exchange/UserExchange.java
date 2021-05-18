package com.robinvandenhurk.gateway.example.serviceauthorization.config.exchange;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Author:    Robin van den Hurk
 * Date:      17/05/2021
 * File name: ExchangeConfiguration
 */

@Component
public class UserExchange {

    @Bean
    public Exchange getUserExchange() {
        return ExchangeBuilder.directExchange("ovp.user").durable(true).build();
    }

}
