package com.clock.challenge.domain;

import com.clock.challenge.util.Utils;

import java.util.logging.Logger;

public class SecondsThread extends Thread {

    private final static Logger LOGGER = Logger.getLogger(SecondsThread.class.getName());

    private int seconds = 1;
    private int minutes = 0;
    private boolean lockMinutes = false;
    private boolean lockSeconds = false;

    @Override
    public void run() {
        while (true){
            try {
                sleep(1000);
            }catch (InterruptedException e){
                LOGGER.warning(e.getMessage());
            }
            System.out.println(Utils.TICK_MESSAGE);
            setSeconds(seconds + 1);
        }
    }

    public synchronized int getSeconds() {
        while (lockSeconds){
            try{
                wait();
            }catch (InterruptedException e){}
        }
        return seconds;
    }

    public synchronized void setSeconds(int seconds) {
        lockSeconds = true;
        this.seconds = seconds;
        lockSeconds = false;
        notifyAll();
    }

    public synchronized void setMinutes(int minutes) {
        lockMinutes = true;
        this.minutes = minutes;
        lockMinutes = false;
        notifyAll();
    }

    public synchronized int getMinutes() {
        while (lockMinutes){
            try{
                wait();
            } catch (InterruptedException e){}
        }
        return minutes;
    }
}
