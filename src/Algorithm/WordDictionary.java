package Algorithm;

class WordDictionary {

    private Tree root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new Tree();
    }
    
    public void addWord(String word) {
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
    
    public boolean search(String word) {
        return search(word,root);
    }

    public boolean search(String word,Tree root){
        Tree r = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.'){
                for (int j = 0; j < 26; j++) {
                    if (r.children[j] != null){
                        if(search(word.substring(i+1),r.children[j])){
                            return true;
                        }
                    }
                }

                return false;
            }
            if (r.children[chars[i]-97] != null){
                r = r.children[chars[i]-97];
            }else {
                return false;
            }

        }
        return r.isEnd;
    }

    class Tree{
        char data;
        boolean isEnd = false;
        Tree[] children = new Tree[26];
    }
}