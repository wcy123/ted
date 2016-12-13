package org.wcy123.fp;

import java.util.function.Function;

public class Cont<T> {
    final Function<Action<T>, Integer>  runCont;

    public Cont(Function<Action<T>, Integer> runCont) {
        this.runCont = runCont;
    }

    public <R> Cont<R> bind(Function<T, Cont<R>> f) {
        Function<Action<R>, Integer> runContR =
                k -> {
                    Action<T> ff = a -> f.apply(a).runCont.apply(k);
                    return runCont.apply(ff);
                };

        return new Cont(runContR);
    }

    static <T> Cont<T> ret(T a){
        Function<Action, Integer> runCont = k -> k.apply(a);
        return new Cont(runCont);
    }
}
interface Action<T> {
    Integer apply(T a);
}
