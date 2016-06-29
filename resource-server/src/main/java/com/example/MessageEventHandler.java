package com.example;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * イベントハンドラ。データの操作イベントとして呼び出される
 *
 * Created by jun on 2016/06/29.
 */
@Component
@RepositoryEventHandler(Message.class)
public class MessageEventHandler {

    @HandleBeforeCreate
    public void beforeCreate(Message message) {
        message.text = message.text + "****";
        message.createdAt = new Date();
        message.username = SecurityContextHolder.getContext().getAuthentication().getName();
    }
}