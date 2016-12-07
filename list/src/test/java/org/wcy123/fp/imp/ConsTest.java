package org.wcy123.fp.imp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.wcy123.fp.imp.List.fromArray;

import java.util.HashMap;
import java.util.stream.IntStream;

import org.junit.Test;

import com.wcy123.test.logger.TestWithLogger;

public class ConsTest extends TestWithLogger {

    @Test
    public void car() throws Exception {
        final Cons<Integer> a = List.cons(1, Cons.nil());
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
        assertTrue("equals", fromArray(1, 2, 3, 4).equals(List.reverse(fromArray(4, 3, 2, 1))));
    }

    @Test
    public void testEql() throws Exception {
        assertFalse("not equals", List.eql(fromArray(1, 2, 3, 4), fromArray(4, 3, 2, 1)));
        assertFalse("not equals", List.eql(fromArray(1, 2, 3), fromArray(1, 2, 3, 4)));
        assertFalse("not equals", fromArray(1, 2, 3).equals(1));
    }

    @Test
    public void testMap() throws Exception {
        assertTrue("equals",
                List.eql(fromArray(1, 2, 3, 4), List.map(x -> x + 1, fromArray(0, 1, 2, 3))));
        assertTrue("equals", List.eql(fromArray("1", "2", "3", "4"),
                List.map(Object::toString, fromArray(1, 2, 3, 4))));
    }

    @Test
    public void testConcat() throws Exception {
        final Cons<Integer> list12 = fromArray(1, 2);
        final Cons<Integer> list34 = fromArray(3, 4);
        final Cons<Integer> list1234 = fromArray(1, 2, 3, 4);
        final Cons<Integer> listConcat = List.concat(list12, list34);

        // it is hard equal.
        final Cons<Integer> newList = List.concat(Cons.nil(), list12);
        assertTrue("equals", list12 == newList);

        assertTrue("equals", List.eql(listConcat, list1234));
        assertTrue("source not changed", List.eql(list12, fromArray(1, 2)));
        final Cons<Integer> newList12 = List.concat(Cons.nil(), fromArray(1, 2));
        assertTrue("equals", List.eql(list12, newList12));

    }

    @Test
    public void testConcatMap() throws Exception {
        final Cons<Integer> list1234 = fromArray(1, 2, 3, 4);
        // from(IntStream.range(0, x).iterator())
        final Cons<Integer> newList =
                List.concatMap(x -> List.fromIterator(IntStream.range(0, x).iterator()),
                list1234);
        final Cons<Integer> expected = fromArray(0, 0, 1, 0, 1, 2, 0, 1, 2, 3);
        assertTrue("equals", List.eql(expected, newList));

        assertTrue("equals", List.eql(Cons.nil(), List.concatMap(x -> Cons.nil(), list1234)));
        assertTrue("equals", List.eql(Cons.nil(), List.concatMap(x -> Cons.nil(), Cons.nil())));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void hashCodeTest() throws Exception {
        final Cons<Integer> list12 = fromArray(1, 2);
        final HashMap<Cons<Integer>, String> map = new HashMap<>();
        map.put(list12, "test");
    }
}
