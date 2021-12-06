package com.clock.challenge.domain;

import com.clock.challenge.util.Utils;

import java.util.logging.Logger;

public class MinuteThread extends Thread{

    private final static Logger LOGGER = Logger.getLogger(MinuteThread.class.getName());

    private SecondsThread secondsThread;

    public MinuteThread(SecondsThread secondsThread){
        this.secondsThread = secondsThread;
    }

    @Override
    public void run() {
        synchronized (secondsThread){
            while (true){
                try {
                    secondsThread.wait();
                }catch (InterruptedException e){
                    LOGGER.warning(e.getMessage());
                }
                if(secondsThread.getSeconds() == Utils.SECONDS){
                    secondsThread.setSeconds(1);
                    secondsThread.setMinutes(secondsThread.getMinutes() + 1);
                    if(secondsThread.getMinutes() != Utils.MINUTES){
                        System.out.println(Utils.TOCK_MESSAGE);
                    }
                }
            }
        }
    }
}
