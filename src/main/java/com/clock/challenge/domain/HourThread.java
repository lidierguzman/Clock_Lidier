package com.clock.challenge.domain;

import com.clock.challenge.util.Utils;

import java.util.logging.Logger;

public class HourThread extends Thread{

    private final static Logger LOGGER = Logger.getLogger(HourThread.class.getName());

    private SecondsThread secondsThread;

    public HourThread(SecondsThread secondsThread){
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
                if(secondsThread.getMinutes() == Utils.MINUTES){
                    secondsThread.setMinutes(0);
                    System.out.println(Utils.BONG_MESSAGE);
                }
            }
        }
    }
}
