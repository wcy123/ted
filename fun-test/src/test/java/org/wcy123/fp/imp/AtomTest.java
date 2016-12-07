package org.wcy123.fp.imp;

import org.junit.Assert;
import org.junit.Test;

public class AtomTest {
    @Test
    public void main1() throws Exception {
        Atom a1 = new Atom("a-symbol");
        Atom a2 = new Atom("a-symbol");
        Assert.assertTrue(a1.equals(a2));
    }
}
