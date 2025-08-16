package poli.engineering.sudoku.Service;

public interface ISudokuService {
    /**
     * Calcula el valor de r y c de los segmentos a partir del tamaño N.
     * Ejemplo: 9 -> 3x3, 16 -> 4x4, 32 -> 4x8
     */
    int[] calculateSegmentRC(int n);

    /**
     * Genera una matriz vacía de N×N para el tablero de sudoku
     */
    String[][] generateBoard(int n);
}
