public class ArrayDequeTest {
    public static void main(String args[]) {
        ArrayDeque<Integer> T = new ArrayDeque();
        T.addFirst(0);
        T.addFirst(1);
        T.addFirst(2);
        T.addFirst(3);
        T.addFirst(4);
        T.addFirst(5);
        T.addFirst(6);
        T.addFirst(7);
        T.addFirst(8);
        T.addFirst(9);
        T.removeLast();
        T.printDeque();
        int x = T.get(0);
    }
}
