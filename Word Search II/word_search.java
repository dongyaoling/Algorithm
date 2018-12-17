/* Given a 2D board and a list of words from the dictionary, 
find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.*/

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie map = new Trie();
        for (String w : words){
            Trie p = map;
            for (char c : w.toCharArray()){
                if (p.next[c - 'a'] == null)
                    p.next[c - 'a'] = new Trie();
                p = p.next[c - 'a'];
            }
            p.word = w;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[0].length; j ++)
                check(board, i, j, res, map);
        }
        return res;
    }
    
    private void check(char[][] board, int i, int j, List<String> res, Trie map){
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1)
            return;
        char c = board[i][j];
        if (c == '#' || map.next[c - 'a'] == null)
            return;
        map = map.next[c - 'a'];
        if (map.word != null){
            res.add(map.word);
            map.word = null;
        }
        board[i][j] = '#';
        check(board, i + 1, j, res, map);
        check(board, i - 1, j, res, map);
        check(board, i, j + 1, res, map);
        check(board, i, j - 1, res, map);
        board[i][j] = c;
    }
}

class Trie {
    Trie[] next = new Trie[26];
    String word;
}
