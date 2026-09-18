import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put((long) num, count.getOrDefault((long) num, 0) + 1);
        }

        int maxLen = 1;

        if (count.containsKey(1L)) {
            int ones = count.get(1L);
            maxLen = Math.max(maxLen, ones % 2 == 1 ? ones : ones - 1);
        }

        for (long x : count.keySet()) {
            if (x == 1L) {
                continue;
            }

            long curr = x;
            int len = 0;

            while (count.getOrDefault(curr, 0) >= 2) {
                len += 2;
                curr = curr * curr;
            }

            if (count.getOrDefault(curr, 0) == 1) {
                len += 1;
            } else {
                len -= 1;
            }

            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}