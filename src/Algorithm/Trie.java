package Algorithm;

import java.util.Arrays;

class Trie {

    private Tree root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Tree();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Tree r = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (r.children[chars[i]-97] == null){
                Tree tree = new Tree();
                tree.data = chars[i];
                r.children[chars[i]-97] = tree;
            }
            r = r.children[chars[i]-97];
        }
        r.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Tree r = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (r.children[chars[i]-97] != null){
                r = r.children[chars[i]-97];
            }else {
                return false;
            }
        }

        return r.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Tree r = root;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (r.children[chars[i]-97] != null){
                r = r.children[chars[i]-97];
            }else {
                return false;
            }
        }

        return true;
    }


    class Tree{
        char data;
        boolean isEnd = false;
        Tree[] children = new Tree[26];
    }
}