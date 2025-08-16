package poli.engineering.sudoku.Service;

import org.springframework.stereotype.Service;

@Service
public class SudokuService implements ISudokuService {

    @Override
    public int[] calculateSegmentRC(int n) {
        int root = (int) Math.floor(Math.sqrt(n));
        if (root * root == n) {
            return new int[]{root, root};

        }
        for (int d = root; d >= 2; d--) {
            if (n % d == 0) {
                return new int[]{d, n / d};
            }
        }
        return new int[]{1, n}; // fallback
    }

    @Override
    public String[][] generateBoard(int n) {
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ""; // vacío, para luego llenarlo si quieres
            }
        }
        return board;
    }
}
