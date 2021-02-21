import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void Test1() {
        StudentArrayDeque<Integer> st = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ans = new ArrayDequeSolution<>();

        for (int i = 0; i < 40; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                st.addLast(i);
                ans.addLast(i);
                assertEquals("addLast(" + i +")", st.get(st.size() - 1), ans.get(ans.size() - 1));
            } else {
                st.addFirst(i);
                ans.addFirst(i);
                assertEquals("addFirst(" + i +")", st.get(0), ans.get(0));
            }
        }


        for (int i = 0; i < 20; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer x;
            Integer y;

            if (numberBetweenZeroAndOne < 0.5) {
                if (!ans.isEmpty()) {
                    x = st.removeFirst();
                    y = ans.removeFirst();
                    assertEquals("removeFirst()", x, y);
                }
            } else {
                if (!ans.isEmpty()) {
                    x = st.removeLast();
                    y = ans.removeLast();
                    assertEquals("removeLast()", x, y);
                }
            }
        }
    }
}
