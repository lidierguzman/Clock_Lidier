package com.clock.challenge.domain;

public class Message {

    private static Message instance = null;
    private String message;

    public synchronized static void setInstance(Message instance) {
        Message.instance = instance;
    }

    public synchronized String getMessage() {
        return message;
    }

    public synchronized static Message getInstance() {
        if(null == instance){
            instance = new Message();
        }
        return instance;
    }
}
