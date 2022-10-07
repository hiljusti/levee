/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package so.dang.cool.levee;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Stream helpers for the {@link Map.Entry} class.
 */
public final class MapEntries {
    private MapEntries() {} // Utility class, no instances needed.

    /**
     * A helper for functions like {@link Stream#filter(Predicate)}.
     */
    public static <K, V> Predicate<Map.Entry<K,V>> onKey(Predicate<K> keyPredicate) {
        return entry -> keyPredicate.test(entry.getKey());
    }

    /**
     * A helper for functions like {@link Stream#filter(Predicate)}.
     */
    public static <K, V> Predicate<Map.Entry<K,V>> onValue(Predicate<V> valuePredicate) {
        return entry -> valuePredicate.test(entry.getValue());
    }
}
