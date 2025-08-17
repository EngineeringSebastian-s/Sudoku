package poli.engineering.sudoku.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                board[i][j] = "-";
            }
        }
        return board;
    }

    @Override
    public String[][] generateSolvedBoard(int n, int r, int c) {
        if (r * c != n) {
            throw new IllegalArgumentException(
                    "Dimensiones inválidas: r * c debe ser igual a n (" + r + " * " + c + " != " + n + ")"
            );
        }

        int[][] board = new int[n][n];

        // Inicializamos el tablero vacío
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }

        // Resolver con backtracking
        if (solveBoard(board, n, r, c)) {
            // Convertimos a String[][] porque tu contrato lo pide
            String[][] result = new String[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = String.valueOf(board[i][j]);
                }
            }
            return result;
        } else {
            throw new RuntimeException("No se pudo generar un tablero válido.");
        }
    }

    // Backtracking para resolver el tablero
    private boolean solveBoard(int[][] board, int n, int r, int c) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 0) {
                    List<Integer> nums = getShuffledNumbers(n);
                    for (int num : nums) {
                        if (canInsert(board, row, col, num, r, c)) {
                            board[row][col] = num;

                            if (solveBoard(board, n, r, c)) {
                                return true;
                            }

                            // backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // ningún número válido
                }
            }
        }
        return true; // tablero lleno
    }

    // Genera lista aleatoria de 1..n (para variedad en las soluciones)
    private List<Integer> getShuffledNumbers(int n) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        Collections.shuffle(nums);
        return nums;
    }

    // Método para validar si un número puede insertarse en una celda específica
    @Override
    public boolean canInsert(int[][] board, int row, int col, int num, int r, int c) {
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

        // Validación en el bloque (segmento)
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
