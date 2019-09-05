class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int length = word.length();
        Deque deque = new LinkedListDeque();
        for (int i = 0; i < length; i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        } else if (a == null | b == null) {
            return true;
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(a.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i),
                    word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}