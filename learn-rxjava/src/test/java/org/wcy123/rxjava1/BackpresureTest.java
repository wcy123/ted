package org.wcy123.rxjava1;

import java.util.Arrays;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;

public class BackpresureTest {
    @Test
    public void main1() throws Exception {
        final Observable<Integer> from = Observable.from(Arrays.asList(1, 2, 3, 4, 5));
        from.subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                request(1);
            }

            @Override
            public void onCompleted() {
                // gracefully handle sequence-complete
            }

            @Override
            public void onError(Throwable e) {
                // gracefully handle error
            }

            @Override
            public void onNext(Integer n) {
                // do something with the emitted item "n"
                // request another item:
                System.out.println("n is " + n);
                request(1);
            }
        });
    }

}
