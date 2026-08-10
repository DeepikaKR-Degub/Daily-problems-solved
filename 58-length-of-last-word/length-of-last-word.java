class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;

        // Skip spaces at the end
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Count the last word
        int count = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }
}