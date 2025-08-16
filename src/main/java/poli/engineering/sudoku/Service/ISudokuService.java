package poli.engineering.sudoku.Service;

/**
 * Interfaz que define los métodos necesarios para gestionar la lógica
 * de generación de tableros de Sudoku, incluyendo la creación de tableros vacíos
 * y la generación de tableros resueltos, así como el cálculo de los segmentos.
 */
public interface ISudokuService {

    /**
     * Calcula las dimensiones de los segmentos del tablero de Sudoku
     * en función del tamaño N del tablero.
     *
     * El tablero tiene un tamaño de N×N, y la función calcula
     * las dimensiones del segmento (r × c), donde cada segmento debe
     * contener números de 1 hasta N sin repetirse.
     *
     * Ejemplos:
     * - Para un tablero de tamaño 9 (9x9), los segmentos son 3x3.
     * - Para un tablero de tamaño 16 (16x16), los segmentos son 4x4.
     * - Para un tablero de tamaño 32 (32x32), los segmentos podrían ser 4x8 o 8x4, dependiendo de las divisiones posibles.
     *
     * @param n Tamaño del tablero de Sudoku (N×N).
     * @return Un array de dos elementos, donde el primer valor es el número de filas de un segmento (r)
     *         y el segundo es el número de columnas del segmento (c).
     */
    int[] calculateSegmentRC(int n);

    /**
     * Genera un tablero vacío de Sudoku de tamaño N×N.
     *
     * Este método crea un tablero representado como una matriz de Strings, con un tamaño
     * de N×N donde cada celda está inicialmente vacía (un string vacío).
     *
     * @param n El tamaño del tablero (N×N).
     * @return Una matriz de Strings que representa un tablero vacío de Sudoku.
     */
    String[][] generateBoard(int n);

    /**
     * Genera un tablero de Sudoku resuelto de tamaño N×N.
     *
     * Este método llena un tablero de tamaño N×N con números aleatorios entre 1 y N, asegurándose
     * de que cada número colocado cumpla con las reglas del Sudoku: no se repiten en filas, columnas
     * ni en el segmento correspondiente.
     *
     * @param size El tamaño del tablero (N×N).
     * @param r El número de filas en cada segmento.
     * @param c El número de columnas en cada segmento.
     * @return Una matriz de Strings representando un tablero de Sudoku resuelto.
     */
    String[][] generateSolvedBoard(int size, int r, int c);
}
