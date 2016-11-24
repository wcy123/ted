package org.wcy123.rxjava1;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.observables.ConnectableObservable;

@Slf4j
public class Service1Test {
    @Test
    public void service1() throws InterruptedException {
        Service1 service1 = new Service1();
        Observable<String> op1 = service1.operation();
        CountDownLatch latch = new CountDownLatch(1);

        op1.subscribe(s -> log.info("From Subscriber 1: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> {
                    log.info("finished");
                    latch.countDown();
                });

        latch.await();
    }

    @Test
    public void service2() throws InterruptedException {
        Service1 service1 = new Service1();
        Observable<String> op1 = service1.operation();

        CountDownLatch latch = new CountDownLatch(3);

        op1.subscribe(s -> log.info("From Subscriber 1: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> latch.countDown());

        op1.subscribe(s -> log.info("From Subscriber 2: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> latch.countDown());

        op1.subscribe(s -> log.info("From Subscriber 3: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> latch.countDown());

        latch.await();
    }

    @Test
    public void service3() throws InterruptedException {
        Service1 service1 = new Service1();
        Observable<String> op1 = service1.operation();

        ConnectableObservable<String> connectableObservable = op1.publish();

        CountDownLatch latch = new CountDownLatch(3);
        log.info("hello");
        connectableObservable.subscribe(s -> log.info("From Subscriber 1: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> latch.countDown());

        connectableObservable.subscribe(s -> log.info("From Subscriber 2: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> latch.countDown());

        connectableObservable.subscribe(s -> log.info("From Subscriber 3: {}", s),
                e -> log.error(e.getMessage(), e),
                () -> latch.countDown());
        log.info("start to connect");
        connectableObservable.connect();
        log.info("start to waiting");
        latch.await();
        log.info("byebye");
    }
}
