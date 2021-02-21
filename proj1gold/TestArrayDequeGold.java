import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void Test1() {
        String message = "";
        StudentArrayDeque<Integer> st = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ans = new ArrayDequeSolution<>();

        for (int i = 0; i < 40; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                st.addLast(i);
                ans.addLast(i);
                assertEquals(st.get(st.size() - 1), ans.get(ans.size() - 1));
                message += "\naddLast(" + i +")";
            } else {
                st.addFirst(i);
                ans.addFirst(i);
                assertEquals(st.get(0), ans.get(0));
                message += "\naddFirst(" + i +")";
            }
        }


        for (int i = 0; i < 20; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer x;
            Integer y;

            if (numberBetweenZeroAndOne < 0.5) {
                x = st.removeFirst();
                y = ans.removeFirst();
                message += "\nremoveFirst()";

            } else {
                x = st.removeLast();
                y = ans.removeLast();
                message += "\nremoveLast()";
            }

            assertEquals(message, x, y);
        }
    }
}
