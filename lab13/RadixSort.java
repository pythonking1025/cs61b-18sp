import java.util.Arrays;
import java.util.SplittableRandom;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < asciis.length; i ++ )
        {
            if (asciis[i].length() > max) {
                max = asciis[i].length();
            }
        }
        String[] res = Arrays.copyOf(asciis, asciis.length);
        for (int i = max - 1; i >= 0; i --) {
            sortHelperLSD(res, i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int N = 256;
        int[] counts = new int[N + 1];
        for (String x : asciis) {
            int pos = get_pos(x, index);
            counts[pos] ++;
        }
        int pos = 0;
        int[] start = new int[N + 1];
        for (int i = 0; i < N + 1; i ++) {
            start[i] = pos;
            pos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i ++) {
            String x = asciis[i];
            pos = get_pos(x, index);
            sorted[start[pos]] = x;
            start[pos] ++;
        }
        for (int i = 0; i < asciis.length; i ++) {
            asciis[i] = sorted[i];
        }
    }

    private static int get_pos(String x, int index) {
        if (index >= x.length() || index < 0) {
            return 0;
        } else {
            return x.charAt(index) + 1;
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = new String[] {"123", "2", "455621", "235", "33", "43526"};
        String[] res = RadixSort.sort(asciis);
        for (String x : res) {
            System.out.println(x);
        }

        String[] asciis1 = new String[] {"     ", " ", "  ", "   "};
        String[] res1 = RadixSort.sort(asciis1);
        for (String x : res1) {
            System.out.println('|' + x + '|');
        }
    }
}
