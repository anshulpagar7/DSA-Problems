import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] present = new boolean[101];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int x : nums) {
            present[x] = true;
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = min + 1; i < max; i++) {
            if (!present[i]) {
                result.add(i);
            }
        }

        return result;
    }
}