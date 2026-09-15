import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";

        for (int length = 2; length <= 9; length++) {
            for (int start = 0; start <= 9 - length; start++) {
                int num = Integer.parseInt(digits.substring(start, start + length));
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}