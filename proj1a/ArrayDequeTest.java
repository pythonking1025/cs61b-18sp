public class ArrayDequeTest {
    public static void main(String args[]) {
        ArrayDeque<Integer> T = new ArrayDeque();
        T.addFirst(1);
        T.addFirst(2);
        T.addLast(4);
        T.addLast(5);
        T.removeLast();
        T.removeFirst();
        T.addFirst(3);
        T.addFirst(3);
        T.removeLast();
        T.removeFirst();
        T.size();
        T.addLast(4);
        T.addLast(5);
        T.addFirst(3);
        T.addFirst(3);
        T.removeLast();
        T.removeFirst();
        T.addLast(4);
        T.addLast(5);

    }
}
