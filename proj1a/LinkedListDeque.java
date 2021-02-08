public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node p, Node n) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node scd = sentinel.next;
        sentinel.next = new Node(item, sentinel, scd);
        scd.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        Node scd = sentinel.prev;
        sentinel.prev = new Node(item, scd, sentinel);
        scd.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        int i = 0;
        while (i < size) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
            i = i + 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        size -= 1;
        return first.item;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size -= 1;
        return last.item;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node ptr = sentinel.next;
        while (index != 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.item;
    }

    private T getRHelper(Node sen, int index) {
        if (index == 0) {
            return sen.next.item;
        }
        return getRHelper(sen.next, index - 1);
    }
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return getRHelper(sentinel, index);
    }
}
