import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s, int left, int right) {

        Set<String> result = new HashSet<>();

        // Current expression being built through concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        int i = left;

        while (i <= right) {

            char ch = s.charAt(i);

            Set<String> part;

            // If we find a '{', find its matching '}'
            if (ch == '{') {

                int count = 0;
                int j = i;

                for (; j <= right; j++) {
                    if (s.charAt(j) == '{') {
                        count++;
                    } else if (s.charAt(j) == '}') {
                        count--;

                        if (count == 0) {
                            break;
                        }
                    }
                }

                // Parse the expression inside {}
                part = parse(s, i + 1, j - 1);

                i = j + 1;

            } else if (ch == ',') {

                // Comma means UNION
                result.addAll(current);

                current.clear();
                current.add("");

                i++;

                continue;

            } else {

                // Single lowercase letter
                part = new HashSet<>();
                part.add(String.valueOf(ch));

                i++;
            }

            // Concatenation:
            // combine everything in current with everything in part
            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : part) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        // Add the last concatenated part
        result.addAll(current);

        return result;
    }
}