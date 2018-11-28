package algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalOps {

    private String getRange(int lower, int upper) {
        return lower == upper ? Integer.toString(lower) : (lower + "->" + upper);
    }

    // assume range includes array
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();

        if (nums.length == 0) {
            ranges.add(getRange(lower,upper));
            return ranges;
        }

        long lowerL = new Long(lower);
        long upperL = new Long(upper);
        long prev = lowerL - 1;
        for (int i = 0; i <= nums.length; i++) {
            long curr = i == nums.length ? upperL + 1: nums[i];
            if (curr - prev <= 1) {
                prev = curr;
                continue;
            }
            ranges.add(getRange((int) (prev + 1), (int) (curr - 1)));
            prev = curr;
        }

        return ranges;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        if (nums.length == 0) {
            return ranges;
        }

        long prev = nums[0];
        long curr = nums[0];
        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prev = curr;
            curr = (long) nums[i];
            if (curr - prev <= 1) {
                continue;
            }
            ranges.add(getRange(start, (int) prev));
            start = (int) curr;
        }
        ranges.add(getRange(start, nums[nums.length - 1]));

        return ranges;
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0; end = 0;
        }

        Interval(int s, int e) {
            start = s; end = e;
        }
    }

    // assume intervals are sorted by start
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> list = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (newInterval.end < curr.start) {
                list.add(newInterval);
                list.addAll(intervals.subList(i, intervals.size()));
                return list;
            } else if (newInterval.start > curr.end) {
                list.add(curr);
                continue;
            } else {
                newInterval.start = Math.min(curr.start, newInterval.start);
                newInterval.end = Math.max(curr.end, newInterval.end);
            }
        }
        list.add(newInterval);
        return list;
    }

    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval o1, Interval o2) {
            if (o1.start < o2.start) {
                return -1;
            } else if (o1.start > o2.start) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // assume intervals are unsorted
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> newIntervals = new ArrayList<>();

        // sort intervals
        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(intervals, comparator);

        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            if (prev.end < curr.start) {
                newIntervals.add(prev);
                prev = curr;
            } else if (prev.end < curr.end) {
                prev = new Interval(prev.start, curr.end);
            }
        }
        newIntervals.add(prev);

        return newIntervals;
    }

    public static void main(String[] args) {
        testFindMissingRange(new IntervalOps());

        testSummaryRanges(new IntervalOps());

        testInsert(new IntervalOps());

        testMerge(new IntervalOps());
    }

    private static void testSummaryRanges(IntervalOps intOps) {
        System.out.println("\ntestSummaryRanges");
        System.out.println(intOps.summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(intOps.summaryRanges(new int[]{-2147483648,-2147483647,2147483647}));
        System.out.println(intOps.summaryRanges(new int[]{}));
        System.out.println(intOps.summaryRanges(new int[]{1}));
    }

    private static void testFindMissingRange(IntervalOps intOps) {
        System.out.println("\ntestFindMissingRange");
        System.out.println(intOps.findMissingRanges(new int[]{1, 3, 8}, 0, 10));
        System.out.println(intOps.findMissingRanges(new int[]{2, 3, 9}, 0, 10));
        System.out.println(intOps.findMissingRanges(new int[]{0, 3, 10}, 0, 10));
        System.out.println(intOps.findMissingRanges(new int[]{}, 0, 10));
        System.out.println(intOps.findMissingRanges(new int[]{1, 1, 1}, 1, 2));
        System.out.println(intOps.findMissingRanges(new int[]{2147483647}, 0, 2147483647));
        System.out.println(intOps.findMissingRanges(new int[]{2147483647}, -2147483648, 2147483647));
        System.out.println(intOps.findMissingRanges(new int[]{-2147483648, 2147483647}, -2147483648, 2147483647));
//        System.out.println(2147483647 - (-2147483648) == -1);
//        System.out.println(2147483647 - (-1) == -2147483648);
//        System.out.println(2147483647 + 1 == -2147483648);
//        System.out.println(-2147483648 - 1 == 2147483647);
//        System.out.println(-2147483648 - 2147483647 == 1);
//        System.out.println(-2147483648 < 2147483647);
//        System.out.println(2147483647 + 1 - 0 == -2147483648);
    }

    private static void testInsert(IntervalOps intOps) {
        System.out.println("\ntestInsert");
        printIntervalList(intOps.insert(
                new ArrayList<>(Arrays.asList(new Interval(1,2), new Interval(3,5), new Interval(6,7), new Interval(8,10), new Interval(12,16))),
                new Interval(4,9)));
    }

    private static void testMerge(IntervalOps intOps) {
        System.out.println("\ntestMerge");
        printIntervalList(intOps.merge(
                new ArrayList<>(Arrays.asList(new Interval(1,3), new Interval(2,6), new Interval(8,10), new Interval(15,18)))));
    }

    private static void printIntervalList(List<Interval> intervals) {
        for (Interval interval : intervals) {
            System.out.println(interval.start + "," + interval.end);
        }
    }
}
