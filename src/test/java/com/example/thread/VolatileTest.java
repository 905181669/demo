package com.example.thread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: luozijian
 * @date: 1/21/21 14:09:14
 * @description:
 */
public class VolatileTest {



    public static void main(String[] args) throws Exception {

        State state = new State(true);

        Thread t1 = new Thread(new Task(state));

        Thread t2 = new Thread(new Task1(state));

        t1.start();

        Thread.sleep(1000);
        t2.start();




    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Task implements Runnable{

    State state;

    @Override
    public void run() {
        int count = 0;
        while(state.isState()){
            count++;
//            System.out.println("计数: " + count++);
            try{
//                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Task1 implements Runnable{

    State state;

    @Override
    public void run(){
        state.setState(false);

    }
}



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class State{
    private volatile boolean state;

}