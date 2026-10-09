class Solution {
public int minInsertions(String s) {
int open = 0;
int insertions = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            open++;
        } else {
            // A closing pair requires two consecutive ')'
            if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                i++;
            } else {
                insertions++;
            }

            if (open > 0) {
                open--;
            } else {
                insertions++;
            }
        }
    }

    return insertions + 2 * open;
}


}
