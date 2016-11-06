package com.wcy123.parsec.stage6;

import org.wcy123.list.Cons;
import org.wcy123.list.Functions;

import rx.functions.Func0;
import rx.functions.Func2;

public class List<T> {
    public final Func2<Parsec<T>, Parsec<Cons<T>>, Parsec<Cons<T>>> cons =
            Monad.lift2(Functions::cons);
    public final Func0<Parsec<Cons<T>>> nil =
            Monad.lift0(Cons::<T>nil);

    public List() {}
}
