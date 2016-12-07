package org.wcy123.fp;

import java.util.Map;
import java.util.stream.Stream;

import org.wcy123.fp.imp.ConstantPattern;
import org.wcy123.fp.imp.ListPattern;
import org.wcy123.fp.imp.MapPattern;
import org.wcy123.fp.imp.PatternVariable;

public interface Pattern {

    static Pattern p(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("object cannot be null");
        }
        if (o instanceof Pattern) {
            return (Pattern) o;
        } else {
            return new ConstantPattern(o);
        }
    }

    static Pattern from(Object... objects) {
        return new ListPattern(Stream.of(objects));
    }

    static <K, V> Pattern fromMap(Map<K, V> map) {
        return new MapPattern(map.entrySet().stream());
    }

    static Pattern var() {
        return new PatternVariable();
    }

    boolean match(Object any);

}
