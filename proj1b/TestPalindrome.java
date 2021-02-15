import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("aaabaaaaabaaa"));
    }

    @Test
    public void testPalindrome() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
    }

    @Test
    public void testPalindromeByN() {
        OffByN offByN = new OffByN(10);
        assertTrue(palindrome.isPalindrome("auk", offByN));
    }
}
