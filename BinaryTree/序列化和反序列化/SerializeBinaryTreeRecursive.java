package cn.jxau.bat.bianrytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 编程只服JAVA on 2017.07.09.
 */
public class SerializeBinaryTree {

    /**
     * 序列化二叉树（BinaryTree to String）
     * @param node
     * @param stringBuilder
     * @return
     */
    public static String tree2str(Node node, StringBuilder stringBuilder){
        if (node == null){
            stringBuilder.append("#!");
        }else {
            stringBuilder.append(node.value + "!");
            tree2str(node.left,stringBuilder);
            tree2str(node.right,stringBuilder);
        }
        return stringBuilder.toString();
    }

    /**
     * 反序列化二叉树（String to BinaryTree）
     * @param queue
     * @return
     */
    public static Node str2tree(Queue<String> queue){
        String value = queue.poll();
        if (value.equals("#"))
            return null;

        Node node = new Node(Integer.valueOf(value));
        node.left = str2tree(queue);
        node.right = str2tree(queue);

        return node;
    }

    /**
     * Testing
     * @param args
     */
    public static void main(String[] args) {
        Node node = new Node(12);
        Node node1 = new Node(13);
        Node node2 = new Node(14);

        node.left = node1;
        node.right = node2;
        node = str2tree(new LinkedList<String>(Arrays.asList("12!13!#!#!14!#!#!".split("!"))));
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(tree2str(node, stringBuilder));
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
