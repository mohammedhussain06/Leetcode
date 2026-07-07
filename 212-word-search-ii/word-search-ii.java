class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();
    List<String> result = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        // Build Trie
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, root);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int row, int col, TrieNode node) {

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return;

        char ch = board[row][col];

        if (ch == '#')
            return;

        node = node.children[ch - 'a'];

        if (node == null)
            return;

        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Prevent duplicates
        }

        board[row][col] = '#';

        dfs(board, row + 1, col, node);
        dfs(board, row - 1, col, node);
        dfs(board, row, col + 1, node);
        dfs(board, row, col - 1, node);

        board[row][col] = ch;
    }
}