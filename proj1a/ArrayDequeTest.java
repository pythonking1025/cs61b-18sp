public class ArrayDequeTest {
    public static void main(String args[]) {
        ArrayDeque<Integer> T = new ArrayDeque();
        T.addLast(5);
        T.removeLast();
        T.addLast(1);
        T.removeLast();
        T.addFirst(10);
        T.removeFirst();
        T.addFirst(10);
        T.addFirst(10);
        T.addFirst(10);
        T.addFirst(10);
        T.addFirst(103);
        T.printDeque();
        int x = T.get(0);
    }
}
