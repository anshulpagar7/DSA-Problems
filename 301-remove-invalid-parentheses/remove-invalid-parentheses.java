import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int remOpen = 0;
        int remClose = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                remOpen++;
            } else if (c == ')') {
                if (remOpen > 0) {
                    remOpen--;
                } else {
                    remClose++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, remOpen, remClose, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int balance, int remOpen, int remClose, StringBuilder current, Set<String> result) {
        if (balance < 0) {
            return;
        }

        if (index == s.length()) {
            if (remOpen == 0 && remClose == 0 && balance == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = current.length();

        if (c == '(') {
            if (remOpen > 0) {
                backtrack(s, index + 1, balance, remOpen - 1, remClose, current, result);
            }
            current.append(c);
            backtrack(s, index + 1, balance + 1, remOpen, remClose, current, result);
            current.setLength(len);
        } else if (c == ')') {
            if (remClose > 0) {
                backtrack(s, index + 1, balance, remOpen, remClose - 1, current, result);
            }
            current.append(c);
            backtrack(s, index + 1, balance - 1, remOpen, remClose, current, result);
            current.setLength(len);
        } else {
            current.append(c);
            backtrack(s, index + 1, balance, remOpen, remClose, current, result);
            current.setLength(len);
        }
    }
}