package algos;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

    public String largestNumber(Integer[] nums) {
        Arrays.sort(nums, comparator);

        if (nums.length > 0 && nums[0] == 0) {
            return "0";
        }

        StringBuilder str = new StringBuilder();
        for (int val : nums) {
            str.append(val);
        }
        return str.toString();
    }

    public Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            String strA = Integer.toString(a);
            String strB = Integer.toString(b);
            String ab = strA + strB;
            String ba = strB + strA;

            for (int i = 0; i < ab.length(); i++) {
                char cAB = ab.charAt(i);
                char cBA = ba.charAt(i);
                if (cAB < cBA) {
                    return 1; // descent order
                } else if (cAB > cBA) {
                    return -1;
                }
            }
            return 0;
        }
    };
}
