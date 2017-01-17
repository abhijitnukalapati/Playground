import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {

    private LinkedHashMap<K, V> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity,0.75f, true) {
            @Override
            public boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public V get(K key) {
        if(cache.containsKey(key)) {
            V value = cache.get(key);
            return value;
        } else {
            return null;
        }
    }
    
    public void set(K key, V value) {
        cache.put(key, value);
    }

    public void printCache() {
        for(V value : cache.values()) {
            System.out.println(value.toString());
        }
    }

}
