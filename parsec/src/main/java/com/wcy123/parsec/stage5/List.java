package com.wcy123.parsec.stage5;

import org.wcy123.fp.imp.Cons;

import rx.functions.Func0;
import rx.functions.Func2;

public class List<T> {
    public final Func2<Parsec<T>, Parsec<Cons<T>>, Parsec<Cons<T>>> cons =
            Monad.lift2(org.wcy123.fp.imp.List::cons);
    public final Func0<Parsec<Cons<T>>> nil =
            Monad.lift0(Cons::<T>nil);

    public List() {}
}
