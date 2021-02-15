public class ArrayDeque<T> implements Deque<T> {
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
        head = 0;
        tail = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0, j = head; i < size; i++) {
            a[i] = items[j];
            j++;
            if (j == cap) {
                j = 0;
            }
        }
        head = 0;
        tail = size;
        items = a;
        cap = capacity;
    }

    public void addFirst(T item) {
        if (size == cap) {
            resize(size * 2);
        }
        if (head == 0) {
            head = cap - 1;
        } else {
            head = head - 1;
        }
        items[head] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == cap) {
            resize(size * 2);
        }
        items[tail] = item;
        if (tail == cap - 1) {
            tail = 0;
        } else {
            tail = tail + 1;
        }
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
            System.out.print(items[i] + " ");
            if (i == cap - 1) {
                i = -1;
            }
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
        if (ratio < R && cap >= 16) {
            resize(cap / 2);
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (tail == 0) {
            tail = cap - 1;
        } else {
            tail = tail - 1;
        }
        T item = items[tail];
        items[tail] = null;
        size -= 1;
        double ratio = (double) size / cap;
        if (ratio < R && cap >= 16) {
            resize(cap / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(head + index) % cap];
    }
}



