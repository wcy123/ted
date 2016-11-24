package org.wcy123.rxjava1;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.observers.Observers;

public class JustTest {
    @Test
    public void main1() throws Exception {
        final Observable<String> observable = Observable.just("hello world");
        final Observer<Object> observer = Observers.create(System.out::println);
        observable.subscribe(observer);
    }

    @Test
    public void main2() throws Exception {
        final Observable<Integer> observable = Observable.range(1, 10);
        final Observer<Integer> observer = Observers.create(System.out::println);
        observable.subscribe(observer);
    }

    @Test
    public void main3() throws Exception {
        final Observable<Integer> observable = Observable.range(1, 10);
        final Observer<Integer> observer = Observers.create(System.out::println);
        observable.subscribe(observer);
    }

    @Test
    public void main4() throws Exception {
        final Observable<String> observable = Observable.empty();
        final Observer<String> observer = new SimpleObserver();
        observable.subscribe(observer);
        System.out.println("hi");
    }

    @SuppressWarnings("WeakerAccess")
    static class SimpleObserver implements Observer<String> {
        @Override
        public void onCompleted() {}

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onNext(String s) {}
    }

}
