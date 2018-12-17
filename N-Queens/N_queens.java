/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[] col = new boolean[n];
        boolean[] ang = new boolean[2 * n - 1];
        boolean[] anti = new boolean[2 * n - 1];
        helper(n, new ArrayList<>(), res, col, ang, anti);
        return res;
    }
    private void helper(int n, List<String> temp, List<List<String>> res, boolean[] col, boolean[] ang, boolean[] anti){
        if (n == temp.size()){
            res.add(new ArrayList<>(temp));
            return;
        }
        char[] row = new char[n];
        int level = temp.size();
        for (int i = 0; i < n; i ++) row[i] = '.';
        for (int i = 0; i < n; i ++){
            int angid = n - 1 - level + i;
            int antid = i + level;
            if (!col[i] && !ang[angid] && !anti[antid]){
                col[i] = true;
                ang[angid] = true;
                anti[antid] = true;
                row[i] = 'Q';
                temp.add(String.valueOf(row));
                helper(n, temp, res, col, ang, anti);
                temp.remove(temp.size() - 1);
                col[i] = false;
                ang[angid] = false;
                anti[antid] = false;
                row[i] = '.';
            }
        }
        
    }
}
