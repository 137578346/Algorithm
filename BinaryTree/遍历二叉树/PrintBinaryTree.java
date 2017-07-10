package cn.jxau.bat.bianrytree;

/**
 * Created by 编程只服JAVA on 2017.07.10.
 */

import java.util.Stack;

/**
 * 先序遍历二叉树
 */
public class PrintBinaryTree {

    /**
     * 非递归方法实现先序遍历二叉树
     */
    public static void printBinaryTree(Node node){
        //工程方面不要使用Stack，这是java中过时的api，DeQueue是一个更好的选择
        Stack<Node> stack = new Stack<>();// only for convenient

        Node current = null;
        //1.将头节点head压入stack中
        stack.push(node);
        while (!stack.isEmpty()){
            current = stack.pop();
            System.out.print(current.value + " ");

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    /**
     * 递归的方法实现先序遍历二叉树
     */
    public static void printBinaryTreeRecursive(Node node){
        if (node == null)
            return;

        System.out.print(node.value + " ");
        printBinaryTreeRecursive(node.left);
        printBinaryTreeRecursive(node.right);
    }

    public static void main(String[] args) {
        Node node = new Node("12");
        node.left = new Node("13");
        node.right = new Node("14");

        System.out.println("递归遍历二叉树：");
        printBinaryTreeRecursive(node);
        System.out.println();
        System.out.println("非递归遍历二叉树：");
        printBinaryTree(node);
    }

    private static class Node{
        private String value;
        private Node left;
        private Node right;

        public Node(String value){
            this.value = value;
            left = null;
            right = null;
        }
    }
}
