package com.example.contend;

/**
 * @author: luozijian
 * @date: 6/17/21 21:27:31
 * @description:
 */
public class ContendTest {

    private static final int ITER = 1000000000;

    public static void main(String[] args) throws Exception {

        VolatileLong volatileLong = new VolatileLong(0, 0, 0, 0);
        Runnable update1 = () -> {
            for (int i = 0; i < ITER; i++) {
                volatileLong.setValue1(i);
            }
        };
        Runnable update2 = () -> {
            for (int i = 0; i < ITER; i++) {
                volatileLong.setValue2(i);
            }
        };
        Runnable update3 = () -> {
            for (int i = 0; i < ITER; i++) {
                volatileLong.setValue3(i);
            }
        };
        Runnable update4 = () -> {
            for (int i = 0; i < ITER; i++) {
                volatileLong.setValue4(i);
            }
        };

        Thread[] threads = new Thread[10];


        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = (i & 2) == 0 ? new Thread(update1) : new Thread(update2);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("1 elapsed: " + (System.currentTimeMillis() - start));



        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = (i & 2) == 0 ? new Thread(update3) : new Thread(update4);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("2 elapsed: " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = (i & 2) == 0 ? new Thread(update1) : new Thread(update3);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("3 elapsed: " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = (i & 2) == 0 ? new Thread(update2) : new Thread(update4);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("4 elapsed: " + (System.currentTimeMillis() - start));
    }

    /**
     * 开启 -XX:-RestrictContended
     * 1 elapsed: 8302
     * 2 elapsed: 9000
     * 3 elapsed: 8372
     * 4 elapsed: 9033
     */


    /**
     * 不开启Contended -XX:+RestrictContended
     * 1 elapsed: 8166
     * 2 elapsed: 13457
     * 3 elapsed: 7912
     * 4 elapsed: 13371
     */

}


