import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class LRUCache<K, V> {

    int capacity;
    List<V> list;
    Map<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new LinkedList<V>();
        cache = new HashMap<K, V>();
    }
    
    public V get(K key) {
        if(cache.containsKey(key)) {
            V value = cache.get(key);
            list.remove(value);
            list.add(0, value);
            return value;
        } else {
            return null;
        }
    }
    
    public void set(K key, V value) {
        if(list.size() == capacity) {
            list.remove(list.size() - 1);
        }

        cache.put(key, value);
        list.add(0, value);
    }

    public void printCache() {
        for(V value : list) {
            System.out.println(value.toString());
        }
    }

}
