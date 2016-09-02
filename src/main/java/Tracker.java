import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.*;


public class Tracker <K, V> {
    private Map<V, Set<K>> table = new HashMap<V, Set<K>>();

    public void addTrack(K key, List<V> values) {
        for (V value : values) {
            Set<K> got = table.get(value);
            if (got == null) {
                Set<K> keySet = new HashSet<K>();
                keySet.add(key);
                table.put(value, keySet);
            } else {
                got.add(key);
            }
        }
    }

    public Set<K> requestTrack(V value) {
        return unmodifiableSet(table.get(value));
    }
}
