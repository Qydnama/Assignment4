import java.util.Iterator;
import java.util.Stack;


public class BST<K extends Comparable<K>, V> implements BST_Interface<K,V>  {
    private Node root;
    private class Node {
        private K key;
        private V val;
        private Node left,right;
        private int n;
        public Node(K key, V val,int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public class KV<K, V> {
        public K key;
        public V val;
        public int n;

        public KV(K key, V val,int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node node) {
        if (node == null) return 0;
        return node.n;
    }


    @Override
    public void put(K key, V val) {
        root = put(key,val,root);
    }
    private Node put(K key, V val, Node node) {
        if (node == null) return new Node(key,val,1);
        if (key.compareTo(node.key) < 0) node.left = put(key, val, node.left);
        else if (key.compareTo(node.key) > 0) node.right = put(key, val, node.right);
        else if (key.compareTo(node.key) == 0) node.val = val;
        node.n = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public V get(K key) {
        return get(key,root);
    }
    private V get(K key, Node node) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) return get(key, node.left);
        else if (key.compareTo(node.key) > 0) return get(key, node.right);
        else return node.val;
    }

    @Override
    public void delete(K key) {
        root = delete(root,key);
    }
    private Node delete(Node node, K key)
    {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) node.left = delete(node.left, key);
        else if (key.compareTo(node.key) > 0) node.right = delete(node.right, key);
        else
        {
            if (node.right == null) return node.left;
            else if (node.left == null) return node.right;
            Node temp = node;
            node = min(temp.right);
            node.right = minDelete(temp.right);
            node.left = temp.left;
        }
        node.n = 1 + size(node.left) + size(node.right);
        return node;
    }


    @Override
    public void getMax() {
        Node max = max(root);
        System.out.println("Max key: " + max.key + ", Value: " + max.val);
    }
    private Node max(Node node)
    {
        if (node.right == null) return node;
        return max(node.right);
    }


    @Override
    public void getMin() {
        Node min = min(root);
        System.out.println("Min key: " + min.key + ", Value: " + min.val);
    }
    private Node min(Node node)
    {
        if (node.left == null) return node;
        return min(node.left);
    }


    private Node minDelete(Node node)
    {
        if (node.left == null) return node.right;
        node.left = minDelete(node.left);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Iterable<KV<K, V>> iterator() {
        return new Iterable<KV<K, V>>() {
            @Override
            public Iterator<KV<K, V>> iterator() {
                return new BSTIterator(root);
            }
        };
    }



    private class BSTIterator implements Iterator<KV<K, V>> {
        private Stack<Node> traverse;

        private BSTIterator(Node node) {
            traverse = new Stack<>();
            insertLeft(node);
        }

        private void insertLeft(Node current)
        {
            while (current != null) {
                traverse.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !traverse.isEmpty();
        }

        @Override
        public KV<K, V> next() {
            Node current = traverse.pop();

            if (current.right != null)
                insertLeft(current.right);

            return new KV<>(current.key, current.val,current.n);
        }
    }

}
