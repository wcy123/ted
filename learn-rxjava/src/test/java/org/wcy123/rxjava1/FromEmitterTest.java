package org.wcy123.rxjava1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import rx.AsyncEmitter;
import rx.Observable;

@Slf4j
public class FromEmitterTest {

    @Test
    public void main1() throws Exception {
        final ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(3 * 4);
        Observable.fromEmitter(
                emitter -> IntStream.range(0, 3).boxed().forEach(
                        threadIndex -> service.submit(
                                () -> {
                                    for (int i = 0; i < 4; ++i) {
                                        emitter.onNext("thread + " + threadIndex
                                                + " i = " + i);
                                        Utils.sleep(1000);
                                        latch.countDown();
                                    }
                                    if (threadIndex == 2) {
                                        emitter.onCompleted();
                                    }
                                })),
                AsyncEmitter.BackpressureMode.BUFFER)
                .subscribe(s -> log.info("item {}", s));
        log.info("提前打印这里, subscribe 没有阻塞住");
        log.info("开始等待解锁");
        latch.await();
        log.info("解锁完毕");
    }
}
