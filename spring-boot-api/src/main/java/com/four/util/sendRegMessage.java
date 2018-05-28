package com.four.util;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/28.
 */
@Component
@RabbitListener(queues = "regMessage")
public class sendRegMessage {

    @RabbitHandler
    public void process(String telPhone) throws GeneralSecurityException {
        Date da = new Date();
        long time = da.getTime();
        System.out.println("消费者接收到  : " + telPhone + "消息"+time);
        String str = telPhone + "@163.com";
        SendEmail.zhuCheChengGong(str, telPhone);
    }

}
