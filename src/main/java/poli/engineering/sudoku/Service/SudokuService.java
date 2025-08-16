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

    @Override
    public String[][] generateSolvedBoard(int n) {
        String[][] board = new String[n][n];

        // Lógica de resolución (simplificada por ahora)
        // Llenamos la tabla con valores de ejemplo, aquí puedes implementar la lógica de resolución real.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (i + j + 1) % n == 0 ? String.valueOf((i + j) % n + 1) : "";
            }
        }

        return board;
    }

    // Método para validar si un número puede insertarse en una celda específica
    public boolean canInsertNumber(int[][] board, int row, int col, int num, int r, int c) {
        // Validación en la fila
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Validación en la columna
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Validación en el bloque
        int boxRowStart = row - row % r;
        int boxColStart = col - col % c;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[boxRowStart + i][boxColStart + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
