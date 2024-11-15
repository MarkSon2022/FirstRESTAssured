package org.example;

public class StringAlgorithm {
    public static final char SPACE = ' ';
    public static final char TAB = '\t';
    public static final char BREAK_LINE = '\n';

    public String reverserInputString(String myInput) {
        if (myInput == null) return myInput;
        String revsereString = "";
        for (int index = myInput.length() - 1; index >= 0; index--) {
            revsereString = revsereString + myInput.charAt(index);
        }
        return revsereString;
    }

    public int countWords(String myInput) {
        if (myInput == null) return -1;
        int count = 0;
        int size = myInput.length();
        boolean notCounted = true;

        for (int index = 0; index < size; index++) {
            if (myInput.charAt(index) != SPACE && myInput.charAt(index) != TAB && myInput.charAt(index) != BREAK_LINE) {
                if (notCounted) {
                    count++;
                    notCounted = false;
                }
            } else {
                notCounted = true;
            }
        }
        return count;
    }

    public boolean validPalindrome(String input) {
        int size = input.length() - 1;
        for (int firstIndex = 0, lastIndex = size; firstIndex < lastIndex; firstIndex++, lastIndex--) {
            if (input.charAt(firstIndex) != input.charAt(lastIndex)) {
                return false;
            }
        }
        return true;
    }

}
