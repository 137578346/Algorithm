package interview.algorithm;

import java.util.*;

/**
 * 根据给定的数组，找到所有不同的四人组的sum为target
 */
public class LeetCode_3Sum {

    public static void main(String[] args) {
//        int[] a = {-1, 0, 1, 2, -1, -4};
        int[] a = {0, 0, 0, 0, 0, 0};
        //List<List<Integer>> lists = threeSum(a, 0);
        List<List<Integer>> lists = threeSumBySort(a);
        for (List<Integer> list : lists)
            System.out.println(list);
    }

    /**
     * 暴力破解
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums, int target) {
        int len = nums.length;
        if (nums == null || nums.length < 3 )
            return new ArrayList<List<Integer>>();

        List<Integer> ns = null;
        HashSet<List<Integer>> set = new HashSet<>();

        //进行暴力搜索，尝试所有的三人组
        for (int i = 0; i < len; ++i)
        {
            for (int j = i + 1; j < len; ++j)
            {
                for (int k = j + 1; k < len; ++k)
                {
                    //filter the sum not equal target
                    if (nums[i] + nums[j] + nums[k] != target)
                    {
                        continue;
                    }

                    ns = new ArrayList<>();
                    ns.add(nums[i]);
                    ns.add(nums[j]);
                    ns.add(nums[k]);
                    //对三人组进行排序，方便利用HashSet去重
                    Collections.sort(ns);
                    //去重
                    set.add(ns);
                }
            }
        }

        List<List<Integer>> lists = new ArrayList<>();
        for (List<Integer> s : set)
            lists.add(s);

        return lists;
    }

    /**
     * 1、固定一个指针，然后转化为2sum问题。
     * 2、进行先排序，加快搜索速度，并去重
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSumBySort(int[] nums) {
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3 )
            return lists;

        //sort
        Arrays.sort(nums);
        int low = 0;
        int high = 0;

        //1,2,3,4(固定指针从index = 2 开始)
        for (int i = 0; i < len - 2; ++i)
        {
            //如果最小的数都大于0，break
            if (nums[i] > 0)
                break;

            //优化，去重
            if (i > 0)
            {
                if (nums[i] == nums[i -1])
                    continue;
            }
            low = i + 1;
            high = len - 1;
            while (low < high)
            {
                //优化，去重
                if (low > i + 1)
                {
                    if (nums[low] == nums[low - 1])
                    {
                        ++low;
                        continue;
                    }
                }
                //优化，去重
                if (high < len - 1)
                {
                    if (nums[high] == nums[high + 1])
                    {
                        --high;
                        continue;
                    }
                }

                if (nums[i] + nums[low] + nums[high] == 0)
                {
                    lists.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    ++low;
                    --high;
                }else if (nums[i] + nums[low] + nums[high] < 0) {
                    //如果小于0，则low index右移
                    ++low;
                }else {
                    //如果大于0，则high index左移
                    --high;
                }
            }
        }
        return lists;
    }
}
