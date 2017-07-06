package cn.jxau.yuan.sharWeb.service;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 编程只服JAVA on 2017.05.27.
 */

/**
 * 敏感词过滤服务
 * 1.利用字典树算法，高效的完成文本的敏感词过滤
 */
@Service
public class SensitiveWordService implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordService.class);
    //默认的敏感词替换词
    private static final String DEFAULT_REPLACE_SENSITIVE = "***";

    //根节点，为空，可以添加子节点
    private TrieNode root = new TrieNode();

    /**
     * 核心算法一：构建字典树
     * 根据输入的字符串，逐步构建字典树
     * @param textLine
     */
    private void addDirTreeNode(String textLine){
        //边界处理
        if(textLine == null)
            return;
        //临时结点指向根结点
        TrieNode tempNode = root;
        for(int i = 0; i < textLine.length(); i++){
            char charWord = textLine.charAt(i);

            //直接跳过非法文字
            if (isSymbol(charWord))
                continue;

            TrieNode node = tempNode.getSubNode(charWord);
            if (node == null){
                //说明tempNode第一次碰到该关键字结点
                node = new TrieNode();
                tempNode.addSubNode(charWord , node);
            }

            //tempNode指向下一个结点，开始下一次循环
            tempNode = node;

            //到敏感词的最后一个字时，标记为红色（关键词结尾）
            if (i == textLine.length() - 1)
                tempNode.setKeyWordEnd(true);
        }
    }

    /**
     * 核心算法二：
     * 过滤文本中的敏感词汇
     * @param text
     * @return
     */
    public String filterWords(String text){
        if (StringUtils.isBlank(text))
            return text;

        StringBuilder results = new StringBuilder();
        TrieNode tempNode = root;
        int begin = 0;//回滚数
        int position = 0;//当前比较的位置

        while (position < text.length()){
            char word = text.charAt(position);

            if (isSymbol(word)){
                if (tempNode == root){
                    results.append(word);
                    ++begin;
                }
                ++position;
                continue;
            }

            tempNode = tempNode.getSubNode(word);

            if (tempNode == null){
                //以begin开始的字符串不存在敏感词
                results.append(text.charAt(begin));
                position = begin + 1;
                begin = position;
                tempNode = root;
            }else if (tempNode.isKeyWordEnd()){
                results.append(DEFAULT_REPLACE_SENSITIVE);
                position = position + 1;
                begin = position;
                tempNode = root;
            }else {
                ++position;
            }
        }

        results.append(text.substring(begin));
        return results.toString();
    }
    //初始化“敏感词汇”
    @Override
    public void afterPropertiesSet() throws Exception {
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String nextLine;
            while((nextLine = bufferedReader.readLine())!=null){
                addDirTreeNode(nextLine.trim());
            }
        }catch (Exception e){
            logger.error("读取敏感词文件失败",e.getMessage());
        }finally {
            bufferedReader.close();
        }
    }

    /**
     * 判断是否是非法字符（即不可能存在敏感词汇的字）
     * @param character
     * @return true：非法字符
     */
    private boolean isSymbol(Character character){
        int ic = (int)character;
        //东亚文字 0x2e80 —— 0x9fff
        return !CharUtils.isAsciiAlphanumeric(character) && (ic < 0x2e80 || ic > 0x9fff);
    }

    //字典树（前缀书）结点
    private class TrieNode{

        /**
         * 标识当前结点是否是一个“关键词”的最后一个结点
         * true 关键词的终结 false 继续
         */
        private boolean isEnd = false;

        /**
         * 用map来存储当前结点的所有子节点，非常的方便
         * key 下一个字符 value 对应的结点
         */
        private Map<Character , TrieNode> subNodes = new HashMap<>();

        /**
         * 向指定位置添加结点树
         * @param key
         * @param node
         */
        public void addSubNode(Character key , TrieNode node){
            subNodes.put(key , node);
        }

        /**
         * 根据key获得相应的子节点
         * @param key
         * @return
         */
        public TrieNode getSubNode(Character key){
            return subNodes.get(key);
        }

        //判断是否是关键字的结尾
        public boolean isKeyWordEnd(){
            return isEnd;
        }

        //设置为关键字的结尾
        public void setKeyWordEnd(boolean isEnd){
            this.isEnd = isEnd;
        }
    }

    public static void main(String[] args){
        String string1 = "sb";
        String string2 = "zz";

        SensitiveWordService sensitiveWordService = new SensitiveWordService();
        sensitiveWordService.addDirTreeNode(string1);
        sensitiveWordService.addDirTreeNode(string2);
        String result = sensitiveWordService.filterWords("klsfjkzzzlsO(∩_∩)Odjflksbj");
        System.out.println(result);
    }
}
