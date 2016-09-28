package com.coral.practice.algorithm;

import java.util.HashMap;


/**
 * Created by qiuhai on 2016/7/26.
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class WordDictionary {

    private WordNode rootWordNode;

    public WordDictionary(){
        rootWordNode = new WordNode();
    }

    public void addWord(String word) {
        HashMap<Character,WordNode> children = rootWordNode.children;
        for(int i=0,length = word.length();i<length;i++){
            char c = word.charAt(i);
            WordNode t;
            if(children.containsKey(c)){
                t = children.get(c);
            }else{
                t = new WordNode(c);
                children.put(c,t);
            }
            children = t.children;
            if(i == length-1){
                t.isLeaf = true;
            }
        }
    }

    public boolean search(String word) {

        return search(rootWordNode.children,word,0);
    }


    /**
     * 通过递归实现
     * @param children 子节点MAP
     * @param word 查询字符
     * @param dept 查询深度
     * @return
     */
    public boolean search(HashMap<Character,WordNode> children,String word,int dept){

        //避免查询字符比字典树的字符短,查到一定深度时会造成下标越界
        if(word.length()==dept){
            if(children.size()==0){
                return true;
            }else {
                return false;
            }
        }
        char ch = word.charAt(dept);
        if(ch=='.'){
            for(char c:children.keySet()){
                if(dept == word.length()-1&&children.get(c).isLeaf){
                    return true;
                }
                //为点时匹配所有字符,所以需要继续往下查
                if(search(children.get(c).children,word,dept+1)){
                    return true;
                }
            }
            return false;
        }else{
            if(children.containsKey(ch)){
                if(dept == word.length()-1&&children.get(ch).isLeaf){
                    return true;
                }else{
                    return search(children.get(ch).children,word,dept+1);
                }
            }else{
                return false;
            }
        }


    }

    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
}

class WordNode{
    /**
    * 节点字符
    */
    char c;
    /**
    * 节点子节点
    */
    HashMap<Character,WordNode> children = new HashMap<Character, WordNode>();
    /**
    * 是否是末节点
    */
    boolean isLeaf;
    public WordNode(){}
    public WordNode(char c){
        this.c = c;
    }


}
