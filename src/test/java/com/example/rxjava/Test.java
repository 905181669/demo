package com.example.rxjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

import java.util.Collections;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 6/5/21 17:55:24
 * @description:
 * Rxjava线程切换流程
 * Observal.subsscribe()-->RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber)-->
 * Func1.call()-->NewThreadWorker.schedule()-->NewThreadWorker.scheduleActual()-->
 * f = this.executor.submit(run)
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        System.out.println(Thread.currentThread().getName() + "先执行call");

                        //call中回调订阅者的onNext方法
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        ).subscribeOn(Schedulers.io());



        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName() + "-" + s + "mySubscriber");
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };


        Subscriber<String> mySubscriber1 = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName() + "-" + s + "mySubscriber1");
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };


        /**
         * mySubscriber订阅myObservable流事件
         * subscribeOn() 控制事件产生的线程，即onSubscribe.call()执行的线程
         * observeOn()控制事件消费的线程，即(subscriber.onNext())执行的线程
         */
        myObservable.subscribe(mySubscriber);
//        myObservable.subscribe(mySubscriber1);





//        Observable.just("Hello, world!")
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(s ->
//                        System.out.println(Thread.currentThread().getName() + "-" + s))
//        ;


//        boolean bool = get(new Func0<Boolean>() {
//            @Override
//            public Boolean call() {
//                return true;
//            }
//        });
//
//        System.out.println(bool);




//        Observable.just("this", "is", "a", "sentence")
////                .subscribeOn(Schedulers.newThread())
//                .map(s -> s.toUpperCase() + " ")
//                .toList()
//                .map(strings -> {
//                    Collections.reverse(strings);
//                    return strings.toString();
//        }).subscribe(s->{
//            System.out.println(Thread.currentThread().getName() + "-" + s);
//        });

    }

    public static boolean get(Func0<Boolean> shouldInterruptThread){
        if(shouldInterruptThread.call()){
            System.out.println("执行get");
            return true;
        }else {
            return false;
        }
    }

}