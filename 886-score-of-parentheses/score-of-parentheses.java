class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {

            if (c == '(') {
                stack.push(0);
            } else {
                int current = stack.pop();

                if (current == 0) {
                    current = 1;       // ()
                } else {
                    current = 2 * current;  // (A)
                }

                stack.push(stack.pop() + current);
            }
        }

        return stack.pop();
    }
}