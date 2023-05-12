public interface BST_Interface<K extends Comparable<K>,V> {
    int size();
    V get(K key);
    void delete(K key);
    void put(K key, V val);
    void getMin();
    void getMax();
}
