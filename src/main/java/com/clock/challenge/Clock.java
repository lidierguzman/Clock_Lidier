package com.clock.challenge;

import com.clock.challenge.domain.HourThread;
import com.clock.challenge.domain.MinuteThread;
import com.clock.challenge.domain.SecondsThread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Clock {

    public static void main(String[] args) {

        SecondsThread taskSeconds = new SecondsThread();
        MinuteThread taskMinutes = new MinuteThread(taskSeconds);
        HourThread taskHours = new HourThread(taskSeconds);

        taskHours.start();
        taskMinutes.start();
        taskSeconds.start();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Clock time stop");
            }
        };
        long duration = TimeUnit.HOURS.toMillis(3);
        Timer timer = new Timer();
        timer.schedule(timerTask, duration);
    }
}
