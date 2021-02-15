public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> a = new ArrayDeque<Character>() {
        };
        for (int i = 0; i < word.length(); i++) {
            a.addLast(word.charAt(i));
        }
        return a;
    }

    private boolean isPalindromeHelper(Deque<Character> word) {
        if (word.size() <= 1) {
            return true;
        }
        char first = word.removeFirst();
        char last = word.removeLast();
        if (first != last) {
            return false;
        }
        return isPalindromeHelper(word);
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    private boolean isPalindromeHelper(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        }
        char first = word.removeFirst();
        char last = word.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        } else {
            return isPalindromeHelper(word, cc);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }
}
