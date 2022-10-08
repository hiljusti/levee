/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package so.dang.cool.levee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static so.dang.cool.levee.MapEntries.entryOf;
import static so.dang.cool.levee.MapEntries.invert;
import static so.dang.cool.levee.MapEntries.invertMany;
import static so.dang.cool.levee.MapEntries.keyTo;
import static so.dang.cool.levee.MapEntries.onKey;
import static so.dang.cool.levee.MapEntries.onValue;
import static so.dang.cool.levee.MapEntries.toEntry;
import static so.dang.cool.levee.MapEntries.toMap;
import static so.dang.cool.levee.MapEntries.toMapOfLists;
import static so.dang.cool.levee.MapEntries.toMapOfSets;
import static so.dang.cool.levee.MapEntries.valueTo;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MapEntriesTest {
    @Test
    void test_onKey() {
        Map.of(
                "apple", "bad",
                "banana", "good",
                "aardvark", "bad"
            ).entrySet()
            .stream()
            .filter(onKey(k -> k.startsWith("b")))
            .forEach(entry -> assertEquals("good", entry.getValue()));
    }

    @Test
    void test_onValue() {
        Map.of(
                "wrong", 2,
                "perfect", 6,
                "bad", 3,
                "also bad", 8
            ).entrySet()
            .stream()
            .filter(onValue(v -> v == 6))
            .forEach(entry -> assertEquals("perfect", entry.getKey()));
    }

    @Test
    void test_invert() {
        var inverted = Map.of(
                "one", 1,
                "two", 2
            ).entrySet()
            .stream()
            .map(invert())
            .collect(toMap());

        assertEquals("one", inverted.get(1));
        assertEquals("two", inverted.get(2));
    }

    @Test
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    void test_invertMany() {
        var inverted = Map.of(
                "bird", List.of("chicken", "duck", "pigeon"),
                "fish", List.of("cod", "salmon", "fugu")
            ).entrySet()
            .stream()
            .flatMap(invertMany())
            .collect(toMap());

        assertEquals("bird", inverted.get("chicken"));
        assertEquals("bird", inverted.get("duck"));
        assertEquals("bird", inverted.get("pigeon"));
        assertEquals("fish", inverted.get("cod"));
        assertEquals("fish", inverted.get("salmon"));
        assertEquals("fish", inverted.get("fugu"));
    }

    @Test
    void test_keyTo() {
        var inverted = Map.of(
                "z", "uno",
                "zz", "dos",
                "zzz", "tres"
            ).entrySet()
            .stream()
            .map(keyTo(String::length))
            .collect(toMap());

        assertEquals("uno", inverted.get(1));
        assertEquals("dos", inverted.get(2));
        assertEquals("tres", inverted.get(3));
    }

    @Test
    void test_valueTo() {
        var inverted = Map.of(
                "scream", "augh",
                "shriek", "eeek"
            ).entrySet()
            .stream()
            .map(valueTo(v -> v.toUpperCase(Locale.US)))
            .collect(toMap());

        assertEquals("AUGH", inverted.get("scream"));
        assertEquals("EEEK", inverted.get("shriek"));
    }

    @Test
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    void test_toMap() {
        var map = List.of(
                "duplicate",
                "clone"
            ).stream()
            .map(toEntry(s -> s, s -> s))
            .collect(toMap());
        
        assertEquals("duplicate", map.get("duplicate"));
        assertEquals("clone", map.get("clone"));
    }

    @Test
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    void test_toMapOfLists() {
        var map = List.of(
                entryOf("a", 1),
                entryOf("b", 2),
                entryOf("b", 3)
            ).stream()
            .collect(toMapOfLists());

        assertEquals(List.of(1), map.get("a"));
        assertEquals(List.of(2, 3), map.get("b"));
    }

    @Test
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    void test_toMapOfSets() {
        var map = List.of(
                entryOf("multi", "slo"),
                entryOf("multi", "bro"),
                entryOf("single", "k")
            ).stream()
            .collect(toMapOfSets());

        assertEquals(Set.of("k"), map.get("single"));
        assertEquals(Set.of("slo", "bro"), map.get("multi"));
    }

    @Test
    void test_entryOf() {
        var mapped = List.of(
                "gracias:de nada",
                "ty:yw",
                "doumo:iiyo"
            ).stream()
            .map(s -> s.split(":"))
            .map(parts -> entryOf(parts[0], parts[1]))
            .collect(toMap());

        assertEquals("de nada", mapped.get("gracias"));
        assertEquals("yw", mapped.get("ty"));
        assertEquals("iiyo", mapped.get("doumo"));
    }
}
