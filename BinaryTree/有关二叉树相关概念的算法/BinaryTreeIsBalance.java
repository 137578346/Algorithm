package cn.jxau.bat.bianrytree;

/**
 * Created by 编程只服JAVA on 2017.07.10.
 */

/**
 * 判断一个二叉树是否平衡
 */
public class BinaryTreeIsBalance {

    /**
     * 为了在一次遍历中判断二叉树的平衡性，采用后序遍历是一个不错的想法。
     * @param head
     * @return
     */
    public static boolean isBalance(Node head){
        return isBalance(head, 0);
    }

    public static boolean isBalance(Node head, int depth){
        if(head == null){
            depth = 0;
            return true;
        }

        int left = 0, right = 0;
        if (isBalance(head.left, left) && isBalance(head.right, right)){
            int diff = left - right;
            if(diff <= 1 && diff >= -1){
                depth = left > right ? left + 1 : right + 1;
                return true;
            }
        }

        return false;
    }

    /**
     * ★缺点：该方法在求该结点的的左右子树深度时遍历一遍树，
     *         再次判断子树的平衡性时又遍历一遍树结构，造成遍历多次。
     * ★不推荐
     * @param head
     * @return
     */
    public static boolean isBalanceRecursive(Node head){
        if (head == null)
            return true;

        int nleft = treeDepth(head.left);
        int nright = treeDepth(head.right);
        int differ = nleft - nright;

        if(differ > 1 || differ < -1)
            return false;

        return isBalanceRecursive(head.left) && isBalanceRecursive(head.right);
    }

    public static int treeDepth(Node head){
        if (head == null)
            return 0;

        int leftDepth = treeDepth(head.left);
        int rightDepth = treeDepth(head.right);

        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    public static void main(String[] args) {
        Node node = new Node("12");
        node.left = new Node("13");
        node.right = new Node("14");

        System.out.println(isBalanceRecursive(node));
        System.out.println(isBalance(node));
    }

    private static class Node{
        private String value;
        private Node left;
        private Node right;

        public Node(String value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
