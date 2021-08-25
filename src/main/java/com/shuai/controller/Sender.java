package com.shuai.controller;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Sender {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args){
        ConnectionFactory factory=new ConnectionFactory();
        factory.setPort(5672);
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message="simplest mode message";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("[x]Sent '"+message+"'");
        channel.close();
        connection.close();
    }
}
