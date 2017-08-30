package cn.jxau.leetcode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        //如果左右结点为null，此时到达叶子结点
        if (root.left == null && root.right == null && sum - root.val == 0)
            return true;

        //递归判断
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
