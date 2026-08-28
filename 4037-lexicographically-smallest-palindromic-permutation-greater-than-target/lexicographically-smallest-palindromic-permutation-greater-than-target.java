import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        boolean canMatchExact = true;
        int[] tempHalf = halfCount.clone();
        for (int i = 0; i < halfLen; i++) {
            int c = target.charAt(i) - 'a';
            if (tempHalf[c] > 0) {
                tempHalf[c]--;
            } else {
                canMatchExact = false;
                break;
            }
        }

        if (canMatchExact) {
            char[] cand = new char[n];
            for (int i = 0; i < halfLen; i++) {
                cand[i] = target.charAt(i);
                cand[n - 1 - i] = target.charAt(i);
            }
            if (n % 2 != 0) {
                cand[halfLen] = (char) ('a' + oddChar);
            }

            String candStr = new String(cand);
            if (candStr.compareTo(target) > 0) {
                return candStr;
            }
        }

        for (int i = halfLen - 1; i >= 0; i--) {
            int[] curHalf = halfCount.clone();
            boolean prefixValid = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (curHalf[c] > 0) {
                    curHalf[c]--;
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (curHalf[c] > 0) {
                    curHalf[c]--;

                    char[] cand = new char[n];
                    for (int j = 0; j < i; j++) {
                        cand[j] = target.charAt(j);
                        cand[n - 1 - j] = target.charAt(j);
                    }

                    cand[i] = (char) ('a' + c);
                    cand[n - 1 - i] = (char) ('a' + c);

                    int idx = i + 1;
                    for (int charCode = 0; charCode < 26; charCode++) {
                        while (curHalf[charCode] > 0) {
                            cand[idx] = (char) ('a' + charCode);
                            cand[n - 1 - idx] = (char) ('a' + charCode);
                            idx++;
                            curHalf[charCode]--;
                        }
                    }

                    if (n % 2 != 0) {
                        cand[halfLen] = (char) ('a' + oddChar);
                    }

                    return new String(cand);
                }
            }
        }

        return "";
    }
}