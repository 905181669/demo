package com.example.suanfa.redis;

/**
 * @author: luozijian
 * @date: 7/10/21 14:48:21
 * @description:
 */
public class Test {


    int REDIS_LRU_BITS = 24;
    int REDIS_LRU_CLOCK_MAX =  ((1<<REDIS_LRU_BITS)-1); /* Max value of obj->lru */
    int REDIS_LRU_CLOCK_RESOLUTION = 1000; /* LRU clock resolution in ms */

    long updateLRUClock() {

        long time = System.currentTimeMillis();

        long res = (time / REDIS_LRU_CLOCK_RESOLUTION) &
                REDIS_LRU_CLOCK_MAX;

        /**
         * REDIS_LRU_CLOCK_RESOLUTION精度，REDIS_LRU_CLOCK_RESOLUTION越大，刻度也就越大，
         * lru时间更新就越不频繁
         */

        System.out.println(time);
        return res;
    }


    @org.junit.Test
    public void testUpdateLRUClock() throws Exception{

        int i = 0;
        long first = 0;
        while(true){

            long res = updateLRUClock();
            if(i == 0){
                first = res;
            }

            System.out.println((i++) +"--"+ res);
//            Thread.sleep(1000);

            if(first != res){
                break;
            }


        }
    }


    @org.junit.Test
    public void test(){
        for(int i = 0; i < 10000; i++){
            System.out.println(i & 15);
        }
    }

}
