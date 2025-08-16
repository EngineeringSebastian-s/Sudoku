package poli.engineering.sudoku.Service;

import org.springframework.stereotype.Service;

import java.util.Random;

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

    // Generación del tablero resuelto con números aleatorios, respetando las reglas del Sudoku
    @Override
    public String[][] generateSolvedBoard(int n, int r, int c) {
        String[][] board = new String[n][n];
        Random rand = new Random();

        // Inicializamos el tablero vacío
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ""; // vacío
            }
        }

        // Llenamos el tablero con números aleatorios validando cada posición
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean inserted = false;
                while (!inserted) {
                    int num = rand.nextInt(n) + 1;  // Genera un número aleatorio entre 1 y n
                    if (canInsertNumber(board, i, j, num, r, c)) {
                        board[i][j] = String.valueOf(num); // insertamos el número
                        inserted = true;
                    }
                }
            }
        }

        return board;
    }

    // Método para validar si un número puede insertarse en una celda específica
    public boolean canInsertNumber(String[][] board, int row, int col, int num, int r, int c) {
        // Validación en la fila
        for (int i = 0; i < board.length; i++) {
            if (board[row][i].equals(String.valueOf(num))) {
                return false;
            }
        }

        // Validación en la columna
        for (int i = 0; i < board.length; i++) {
            if (board[i][col].equals(String.valueOf(num))) {
                return false;
            }
        }

        // Validación en el bloque (segmento)
        int boxRowStart = row - row % r;
        int boxColStart = col - col % c;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[boxRowStart + i][boxColStart + j].equals(String.valueOf(num))) {
                    return false;
                }
            }
        }

        return true;
    }
}
