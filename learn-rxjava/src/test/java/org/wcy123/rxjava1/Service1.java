package org.wcy123.rxjava1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.schedulers.Schedulers;

public class Service1 {
    private static final Logger logger = LoggerFactory.getLogger(Service1.class);

    public Observable<String> operation() {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing slow task in Service 1");
            Utils.sleep(1000);
            s.onNext("data a");
            Utils.sleep(1000);
            s.onNext("data b");
            Utils.sleep(1000);
            s.onNext("data c");
            Utils.sleep(1000);
            logger.info("End: Executing slow task in Service 1");
            s.onCompleted();
        }).subscribeOn(Schedulers.computation());
    }

}
