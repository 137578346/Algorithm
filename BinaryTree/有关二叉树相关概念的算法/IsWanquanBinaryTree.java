package cn.jxau.bat.bianrytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 编程只服JAVA on 2017.07.10.
 */
public class IsWanquanBinaryTree {

    /**
     * 判断是否是完全二叉树
     * @param node
     */
    public static boolean isGoodBinaryTree(Node node){
        if (node == null)
            return false;
        Queue<Node> queue = new LinkedList<>();

        Node current = null;
        queue.offer(node);
        while(!queue.isEmpty()){
            //出队列元素
            current = queue.poll();

            if (current != null){
                if (current.left != null)
                    queue.offer(current.left);

                if (current.right != null)
                    queue.offer(current.right);

                //1.如果当前结点有右孩子，但是没有左孩子，直接返回false
                if (current.left == null && current.right !=null)
                    return false;

                //2.如果当前结点并不是左右孩子全有，那之后的节点必须都为叶节点，否则返回false
                if (current.left != null && current.right == null){
                    if (current.left.left != null || current.left.right != null)
                        return false;
                }
            }
        }
        //3.遍历过程如果不返回false，遍历结束后返回true即可
        return true;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        node.left = node1;
        node.right = new Node(3);
        //node1.left = new Node(4);
        node1.right = new Node(5);

        System.out.println(isGoodBinaryTree(node));
    }

    private static class Node{
        private int value;
        private Node left;
        private Node right;

        Node(int value){
            this.value = value;
            left = null;
            right = null;
        }
    }
}
