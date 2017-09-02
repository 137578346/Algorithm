package interview.algorithm;

/**
 * 编写应用程序，分别使用while和for循环计算8+88+888+n前10项之和。
 */
public class Sum8 {
    public static void main(String[] args) {
        int base = 8;
        int sum = 0;
        int s = 0;
        for (int i = 0; i < 10; ++i)
        {
            s = s*10 +base;
            sum += s;
        }
        System.out.println(sum);
        while8();
    }

    public static void while8() {
        int base = 8;
        int sum = 0;
        int s = 0;
        int i = 0;
        while (i < 10){
            s = s*10 +base;
            sum += s;
            ++i;
        }
        System.out.println(sum);
    }
}
