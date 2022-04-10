package com.example.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 2/26/21 10:33:55
 * @description:
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new MyTask(), 1000, 1000000);

    }

}

class MyTask extends TimerTask{
    @Override
    public void run() {
        System.out.println(new Date());
    }
}
