package interview.algorithm.recursionAlg;

import java.util.Scanner;

/**
 * 利用快排中parition算法，找到第k大数，平均时间复杂度为O(n)
 */
public class TopK
{
    public static void main(String[] args)
    {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
        scanner.close();

        int[] a = {2, 6, 3, 10, 45, 12, 5, 6, 5, 6};
        if (k >= 0 && k < a.length) {
            findMaxK(a, 0, a.length - 1, k);
            System.out.println("第"+ (k+1) +"大数为：" + a[k]);
        } else
            System.out.println("are you kidding me ?");
    }

    private static void findMaxK(int[] a, int low, int high, int k)
    {
        int p = parition(a, low, high);
        if (p == k)
        {
            return;
        }
        if (p < k)
        {
            findMaxK(a, p + 1, high, k);
        }else{
            findMaxK(a, low, p - 1, k);
        }
    }

    private static int parition(int[] a, int low, int high)
    {
        int position = a[high];
        int i = low - 1;
        for (int j = low; j < high; ++j)
        {
            if (a[j] <= position)
            {
                ++i;
                exchange(a, i, j);
            }
        }
        exchange(a, i + 1, high);
        return i + 1;
    }

    static void exchange(int[] a, int i, int j)
    {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
