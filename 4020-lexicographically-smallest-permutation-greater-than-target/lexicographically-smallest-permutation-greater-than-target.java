class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] tempCounts = counts.clone();
            boolean possiblePrefix = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (tempCounts[c] > 0) {
                    tempCounts[c]--;
                } else {
                    possiblePrefix = false;
                    break;
                }
            }

            if (!possiblePrefix) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (tempCounts[c] > 0) {
                    tempCounts[c]--;

                    char[] result = new char[n];
                    for (int j = 0; j < i; j++) {
                        result[j] = target.charAt(j);
                    }
                    result[i] = (char) ('a' + c);

                    int idx = i + 1;
                    for (int k = 0; k < 26; k++) {
                        while (tempCounts[k] > 0) {
                            result[idx++] = (char) ('a' + k);
                            tempCounts[k]--;
                        }
                    }

                    return new String(result);
                }
            }
        }

        return "";
    }
}