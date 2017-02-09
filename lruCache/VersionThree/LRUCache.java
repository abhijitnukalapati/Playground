import java.util.Map;
import java.util.HashMap;

public class LRUCache<K, V> {

    public static class Node<V> {
        V data;
        Node<V> next;
        Node<V> prev;

        public static <V> Node<V> newNode(V data) {
            Node<V> node = new Node<>();
            node.data = data;
            node.next = null;
            node.prev = null;
            return node;
        }
    }

    private final int capacity;
    private Node<V> head, tail;
    private Map<K, Node<V>> map;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public void set(K key, V value) {
        if(map.size() == capacity) {
            Node<V> newHead = head.next;
            newHead.prev = null;

            head.next = null;
            head.prev = null;

            head = newHead;
        }

        Node<V> node = Node.newNode(value);
        
        Node<V> temp = head;
        if(temp == null) {
            head = tail = node;   
        } else {
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
            node.prev = temp;
            tail = node;
        }
       
        map.put(key, node);
    }

    public V get(K key) {
        if(map.containsKey(key)) {
            Node<V> node = map.get(key);
           
            if(tail == node) {
                return node.data; 
            } else if(head == node) {
                Node<V> newHead = head.next;
                newHead.prev = null;
                head.next = null;
                head.prev = null;
                head = newHead;
                
                tail.next = node;
                node.next = null;
                node.prev = tail;
                tail = node;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;

                tail.next = node;
                node.next = null;
                node.prev = tail;
                tail = node;
            }
            return node.data;     
        } else {
            return null;
        }
    } 

    public void print() {
        Node<V> temp = head;
        
        while(temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

}
