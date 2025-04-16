package me.peak.algo;

public class WordSearch {
    public static void main(String[] args) {
        char[][] board =
                {{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        String word = "ABCCSEE";
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return;
        }
        boolean isWordExist = false;
        for (int i = 0; i < board.length && !isWordExist; i++) {
            for (int j = 0; j < board[0].length && !isWordExist; j++) {
                isWordExist = findWord(board, i, j, word, 0);
            }
        }
        System.out.println(isWordExist);

    }

    static boolean findWord(char[][] board, int i, int j, String word, int index) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || index >= word.length()) {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        char temp = board[i][j];
        board[i][j] = '0';
        if(findWord(board, i+1, j, word, index+1)){
            return true;
        }
        if(findWord(board, i-1, j, word, index+1)){
            return true;
        }
        if(findWord(board, i, j+1, word, index+1)){
            return true;
        }
        if(findWord(board, i, j-1, word, index+1)){
            return true;
        }
        board[i][j] = temp;
        return false;
    }
}
