package org.wcy123.fp.imp;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.wcy123.fp.Pattern;

public class MapPattern<K, V> implements Pattern {
    final Map<Pattern, Pattern> map;

    public MapPattern(Stream<Map.Entry<K, V>> stream) {
        map = stream.collect(Collectors.toMap(Pattern::p, Pattern::p));
    }

    private static boolean matchEnty(Map<Object, Object> anyMap,
            Map.Entry<Pattern, Pattern> entry) {
        return anyMap.entrySet().stream().anyMatch(
                pair -> entry.getKey().match(pair.getKey())
                        && entry.getValue().match(pair.getValue()));
    }

    @Override
    public boolean match(Object any) {
        if (!(any instanceof Map)) {
            return false;
        }
        final Map<Object, Object> anyMap = (Map<Object, Object>) any;
        return map.entrySet().stream().allMatch(e -> MapPattern.matchEnty(anyMap, e));
    }
}
