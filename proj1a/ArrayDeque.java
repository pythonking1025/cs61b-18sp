public class ArrayDeque<T> {
    private static final double R = 0.25;

    private T[] items;
    private int size;
    private int head;
    private int tail;
    private int cap;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        cap = 8;
        head = -1;
        tail = -1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(T item) {
        if (size == cap) {
            resize(size * 2);
            cap = cap * 2;
        }
        if (head == 0) {
            head = cap - 1;
        } else if (head == -1) {
            head = 0;
            tail = 0;
        } else {
            head = head - 1;
        }
        items[head] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == cap) {
            resize(size * 2);
            cap = cap * 2;
        }
        if (tail == cap - 1) {
            tail = 0;
        } else if (tail == -1) {
            head = 0;
            tail = 0;
        } else {
            tail = tail + 1;
        }
        items[tail] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = head; i != tail; i++) {
            if (i == cap) {
                i = 0;
            }
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[head];
        items[head] = null;
        if (head == cap - 1) {
            head = 0;
        } else {
            head = head + 1;
        }
        size -= 1;
        double ratio = (double) size / cap;
        if (ratio < R) {
            resize(cap / 2);
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = items[tail];
        items[tail] = null;
        if (tail == 0) {
            tail = cap - 1;
        } else {
            tail = tail - 1;
        }
        size -= 1;
        double ratio = (double) size / cap;
        if (ratio < R) {
            resize(cap / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[(head + index - 1) % cap];
    }
}



