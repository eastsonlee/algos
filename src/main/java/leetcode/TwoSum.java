package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    // Sort: O(NlogN)
    public int[] twoSum1(int[] numbers, int target) {
        int[] index = new int[2];
        int N = numbers.length, lo = 0, hi = N-1;
        int[] numbersSort = Arrays.copyOf(numbers, numbers.length);
        // sort the array - O(NlogN)
        Arrays.sort(numbersSort);
        // search the sum - O(N)
        for (int i = 0; i < N; i++) {
            int sum = numbersSort[lo] + numbersSort[hi];
            if (sum > target)           hi = hi - 1;
            else if (sum < target)      lo = lo + 1;
            else                        break;
        }
        // search index - O(2N)
        for (int i = 0; i < N; i++)
            if (numbers[i] == numbersSort[lo])  index[0] = i;
        for (int i = 0; i < N; i++)
            if (numbers[i] == numbersSort[hi] && i != index[0]) index[1] = i;
        // put index in ascending order
        if (index[0] > index[1]) {
            int temp = index[0];
            index[0] = index[1];
            index[1] = temp;
        }
        index[0] = index[0] + 1;
        index[1] = index[1] + 1;
        return index;
    }

    // HashTable: O(N)
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();  // key - number, value - index
        for (int i = 0; i < numbers.length; i++) {
            int key1 = numbers[i];
            int key2 = target - numbers[i];
            if (h.containsKey(key2)) {
                return new int[] {h.get(key2) + 1, i + 1};
            }
            h.put(key1, i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] arg){
        TwoSum s = new TwoSum();
        int[] numbers = {2,1,9,4,4,56,90,3};
        int target = 8;
        int[] index = s.twoSum(numbers, target);
        System.out.println(Arrays.toString(numbers));
        System.out.println("index1 = " + index[0] + ", index2 = " + index[1]);
    }
}
