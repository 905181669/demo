package com.example.callback;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: luozijian
 * @date: 9/1/21 09:45:04
 * @description:
 */
public class MyPromise {

    List<MyListener> listeners = Lists.newArrayList();

    private boolean isDone;

    public void addListener(MyListener listener){
        listeners.add(listener);

        if(isDone){
            notifyListeners();
        }
    }


    public boolean setValue(boolean result){

        isDone = true;
        notifyListeners();
        return isDone;
    }

    private void notifyListeners(){
        listeners.stream().forEach(e->e.onComplete());
    }
}