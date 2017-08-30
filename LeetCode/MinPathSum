package cn.jxau.leetcode;

public class MinPathSum {

    public static void main(String[] args) {
        int a[][] = {{1,2},{3,4},{5,6}};
        System.out.println(minPathSum(a));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        //初始化dp[0][0]
        dp[0][0] = grid[0][0];
        //初始化dp数组的第0行和0列
        for (int i = 1; i < m; ++i)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < n; ++i)
            dp[0][i] = dp[0][i - 1] + grid[0][i];

        //状态转移公式：dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
        for (int i = 1; i < m; ++i)
            for (int j = 1; j < n; ++j)
                    dp[i][j]= Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];

        return dp[m - 1][n - 1];
    }
}
