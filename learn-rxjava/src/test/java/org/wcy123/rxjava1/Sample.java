package org.wcy123.rxjava1;

import org.junit.Test;

import rx.functions.Action1;
import rx.functions.Func1;

public class Sample {
    @Test
    public void main1() throws Exception {
        final Observable<String> observable = new Observable<>(subscriber -> {
            subscriber.onNext("hello world");
            subscriber.onCompleted();
        });
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("byebye");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("> " + s);
            }

            @Override
            public void unsubscribe() {

            }

            @Override
            public boolean isUnsubscribed() {
                return false;
            }
        };
        observable.subscribe(subscriber);
    }

    @Test
    public void main2() throws Exception {
        final Observable<Integer> observable = new Observable<>(subscriber -> {
            for (int i = 0; i < 5; ++i) {
                subscriber.onNext(i);
            }
            subscriber.onCompleted();
        });
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("byebye");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("> " + s);
            }

            @Override
            public void unsubscribe() {

            }

            @Override
            public boolean isUnsubscribed() {
                return false;
            }
        };
        observable.map(i -> "value: " + i).subscribe(subscriber);
    }

    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
    }
    public interface Observer<T> {
        void onCompleted();

        void onError(Throwable e);

        void onNext(T t);
    }
    public interface Subscription {
        void unsubscribe();

        boolean isUnsubscribed();
    }

    public class Observable<T> {
        final OnSubscribe<T> onSubscribe;

        public Observable(OnSubscribe<T> onSubscribe) {
            this.onSubscribe = onSubscribe;
        }

        public final Subscription subscribe(Subscriber<? super T> subscriber) {
            onSubscribe.call(subscriber);
            return subscriber;
        }

        public <R> Observable<R> map(Func1<T, R> func1) {
            return new Observable<R>(subscriber -> this.subscribe(new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }

                @Override
                public void onError(Throwable e) {
                    subscriber.onError(e);
                }

                @Override
                public void onNext(T t) {
                    R r = null;
                    try {
                        r = func1.call(t);
                    } catch (Throwable e) {
                        unsubscribe();
                        return;
                    }
                    subscriber.onNext(r);
                }

                @Override
                public void unsubscribe() {
                    subscriber.unsubscribe();
                }

                @Override
                public boolean isUnsubscribed() {
                    return subscriber.isUnsubscribed();
                }
            }));
        }
    }

    public abstract class Subscriber<T> implements Observer<T>, Subscription {
    }
}
