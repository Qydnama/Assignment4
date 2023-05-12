public class Main {
    public static void main(String[] args) {
        BST<Integer,String> tree = new BST<>();
        tree.put(9,"slozhno");
        tree.put(10,"AAAAAAAA");
        tree.put(6,"zhe");
        tree.put(7,"eto");
        tree.put(1,"Kak");
        tree.put(14,"Ya ustal");
        tree.put(16,"idu spat");
        tree.put(6,"pomenial");
        tree.delete(10);



        for (var elem : tree.iterator()) {
            System.out.println("Key: " + elem.key + ", Value: " + elem.val + ", Size: " + elem.n);
        }

        System.out.println(tree.size());
        System.out.println(tree.get(14));
        tree.getMax();
        tree.getMin();
    }
//    Output
//    Key: 1, Value: Kak, Size: 1
//    Key: 6, Value: pomenial, Size: 3
//    Key: 7, Value: eto, Size: 1
//    Key: 9, Value: slozhno, Size: 6
//    Key: 14, Value: Ya ustal, Size: 2
//    Key: 16, Value: idu spat, Size: 1
//            6
//    Ya ustal
//    Max key: 16, Value: idu spat
//    Min key: 1, Value: Kak
}