class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                low++;
                high++;
            }

            else if (ch == ')') {
                low--;
                high--;
            }

            else { // '*'
                low--;   // '*' acts as ')'
                high++;  // '*' acts as '('
            }

            // We cannot have negative possible open brackets
            if (high < 0) {
                return false;
            }

            // low cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        return low == 0;
    }
}