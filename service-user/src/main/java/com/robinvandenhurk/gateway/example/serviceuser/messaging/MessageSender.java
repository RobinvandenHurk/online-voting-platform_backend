package com.robinvandenhurk.gateway.example.serviceuser.messaging;

import com.robinvandenhurk.gateway.example.serviceuser.domain.messaging.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: MessageSender
 */

@Service
public class MessageSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Message message) {
        rabbitTemplate.convertAndSend(message.getRoutingKey(), message);
    }
}
