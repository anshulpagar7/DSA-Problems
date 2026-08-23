class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] inResult = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            if (inResult[idx]) {
                continue;
            }

            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {
                inResult[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(c);
            inResult[idx] = true;
        }

        return sb.toString();
    }
}