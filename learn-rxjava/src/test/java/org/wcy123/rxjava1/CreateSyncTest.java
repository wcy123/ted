package org.wcy123.rxjava1;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

@Slf4j
public class CreateSyncTest {
    @Test
    public void main1() throws Exception {
        Observable.range(2, 6)
                .flatMap(total -> Observable.create(SyncOnSubscribe.createSingleState(
                        () -> new AtomicInteger(total),
                        (counter, observer) -> {
                            if (counter.get() < 0) {
                                log.info("on complete: " + total);
                                observer.onCompleted();
                            } else {
                                Utils.sleep(1000);
                                log.info("on next " + counter.get() + "/" + total);
                                observer.onNext(counter.get());
                            }
                            counter.decrementAndGet();
                        })).subscribeOn(Schedulers.io()))
                .map(i -> {
                    log.info("i is " + i);
                    return i;
                })
                .subscribe();
        Utils.sleep(10000);
    }
}
