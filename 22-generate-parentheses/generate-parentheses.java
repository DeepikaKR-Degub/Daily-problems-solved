import java.util.*;

public class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack(result, "", 0, 0, n);

        return result;
    }

    public void backtrack(List<String> result, String current,
                           int open, int close, int n) {

        // If we used all n pairs
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // We can add '(' if we still have some left
        if (open < n) {
            backtrack(result, current + "(", open + 1, close, n);
        }

        // We can add ')' only if there is an unmatched '('
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, n);
        }
    }
}