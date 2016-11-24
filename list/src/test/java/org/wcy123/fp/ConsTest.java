package org.wcy123.fp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.wcy123.fp.List.concat;
import static org.wcy123.fp.List.concatMap;
import static org.wcy123.fp.List.cons;
import static org.wcy123.fp.List.eql;
import static org.wcy123.fp.List.fromArray;
import static org.wcy123.fp.List.fromIterator;
import static org.wcy123.fp.List.map;
import static org.wcy123.fp.List.reverse;

import java.util.HashMap;
import java.util.stream.IntStream;

import org.junit.Test;

import com.wcy123.test.logger.TestWithLogger;

public class ConsTest extends TestWithLogger {

    @Test
    public void car() throws Exception {
        final Cons<Integer> a = cons(1, Cons.nil());
        assertEquals("a", new Integer(1), a.car());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("toString", "[1,2,3,4]", fromArray(new Integer[] {1, 2, 3, 4}).toString());
        assertEquals("toString", "[4]", fromArray(new Integer[] {4}).toString());
        assertEquals("toString", "[]", Cons.nil().toString());
    }

    @Test
    public void testReverse() throws Exception {
        assertTrue("equals", fromArray(1, 2, 3, 4).equals(reverse(fromArray(4, 3, 2, 1))));
    }

    @Test
    public void testEql() throws Exception {
        assertFalse("not equals", eql(fromArray(1, 2, 3, 4), fromArray(4, 3, 2, 1)));
        assertFalse("not equals", eql(fromArray(1, 2, 3), fromArray(1, 2, 3, 4)));
        assertFalse("not equals", fromArray(1, 2, 3).equals(1));
    }

    @Test
    public void testMap() throws Exception {
        assertTrue("equals", eql(fromArray(1, 2, 3, 4), map(x -> x + 1, fromArray(0, 1, 2, 3))));
        assertTrue("equals", eql(fromArray("1", "2", "3", "4"),
                map(Object::toString, fromArray(1, 2, 3, 4))));
    }

    @Test
    public void testConcat() throws Exception {
        final Cons<Integer> list12 = fromArray(1, 2);
        final Cons<Integer> list34 = fromArray(3, 4);
        final Cons<Integer> list1234 = fromArray(1, 2, 3, 4);
        final Cons<Integer> listConcat = concat(list12, list34);

        // it is hard equal.
        final Cons<Integer> newList = concat(Cons.nil(), list12);
        assertTrue("equals", list12 == newList);

        assertTrue("equals", eql(listConcat, list1234));
        assertTrue("source not changed", eql(list12, fromArray(1, 2)));
        final Cons<Integer> newList12 = concat(Cons.nil(), fromArray(1, 2));
        assertTrue("equals", eql(list12, newList12));

    }

    @Test
    public void testConcatMap() throws Exception {
        final Cons<Integer> list1234 = fromArray(1, 2, 3, 4);
        // from(IntStream.range(0, x).iterator())
        final Cons<Integer> newList = concatMap(x -> fromIterator(IntStream.range(0, x).iterator()),
                list1234);
        final Cons<Integer> expected = fromArray(0, 0, 1, 0, 1, 2, 0, 1, 2, 3);
        assertTrue("equals", eql(expected, newList));

        assertTrue("equals", eql(Cons.nil(), concatMap(x -> Cons.nil(), list1234)));
        assertTrue("equals", eql(Cons.nil(), concatMap(x -> Cons.nil(), Cons.nil())));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void hashCodeTest() throws Exception {
        final Cons<Integer> list12 = fromArray(1, 2);
        final HashMap<Cons<Integer>, String> map = new HashMap<>();
        map.put(list12, "test");
    }
}
