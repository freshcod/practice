package com.coral.practice.algorithm;

import java.util.HashMap;

/**
 * Created by qiuhai on 2016/7/26.
 * 字典树实现
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 */
public class Trie {

    private TrieNode rootTriNode;

    public Trie(){
        rootTriNode = new TrieNode();
    }

    public void insert(String word){
        HashMap<Character,TrieNode> children = rootTriNode.children;
        for(int i=0,length = word.length();i<length;i++){
            char c = word.charAt(i);
            TrieNode t;
            if(children.containsKey(c)){
                t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c,t);
            }
            children = t.children;
            if(i == length-1){
                t.isLeaf = true;
            }
        }
    }


    public boolean search(String word){
        TrieNode trieNode = searchNode(word);
        if(trieNode!=null&&trieNode.isLeaf) return true;
        return false;
    }

    public boolean startsWith(String prefix){
        TrieNode trieNode = searchNode(prefix);
        if(trieNode!=null) return true;
        return false;
    }


    /**
     * 搜索节点
     * @param word
     * @return
     */
    public TrieNode searchNode(String word){
        HashMap<Character,TrieNode> children = rootTriNode.children;
        TrieNode trieNode = null;
        for(int i = 0,length = word.length();i<length;i++){
            char c = word.charAt(i);
            if(children.containsKey(c)){
                trieNode = children.get(c);
                children = trieNode.children;
            }else{
                return null;
            }
        }

        return trieNode;
    }

    public static void main(String [] args){
        String word = "abc";
        Trie trie = new Trie();
        trie.insert(word);
        boolean result = trie.search("");
        System.out.println(result);

    }
}

class TrieNode{
    /**
     * 节点字符
     */
    char c;
    /**
     * 节点子节点
     */
    HashMap<Character,TrieNode> children = new HashMap<Character, TrieNode>();
    /**
     * 是否是末节点
     */
    boolean isLeaf;
    public TrieNode(){}
    public TrieNode(char c){
        this.c = c;
    }
}