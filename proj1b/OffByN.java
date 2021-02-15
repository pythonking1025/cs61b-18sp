public class OffByN implements CharacterComparator {
    private int size;

    public OffByN(int n) {
        this.size = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == size ? true : false;
    }
}
