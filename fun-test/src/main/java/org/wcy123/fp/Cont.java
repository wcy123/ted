package org.wcy123.fp;

import java.util.function.Function;

public class Cont<T> {
    final Function<ContAction<T>, Integer>  runCont;

    public Cont(Function<ContAction<T>, Integer> runCont) {
        this.runCont = runCont;
    }

    public <R> Cont<R> bind(Function<T, Cont<R>> f) {
        Function<ContAction<R>, Integer> runContR =
                k ->
                    runCont.apply(a -> f.apply(a).runCont.apply(k));
        return new Cont(runContR);
    }

    static <T> Cont<T> ret(T a){
        Function<ContAction, Integer> runCont = k -> k.apply(a);
        return new Cont(runCont);
    }
}
