package org.wcy123.fp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangchunye on 12/13/16.
 */
public class ContTest {
    @Test
    public void main1() throws Exception {
        final Cont<Integer> c1 = Cont.<Integer>ret(1);
        c1.bind(a -> (Cont.<Integer>.ret(10))
    }
}
