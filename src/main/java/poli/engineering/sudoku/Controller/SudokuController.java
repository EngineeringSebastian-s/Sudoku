package poli.engineering.sudoku.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import poli.engineering.sudoku.Service.ISudokuService;

@Controller
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private ISudokuService sudokuService;

    @GetMapping("/{size}")
    public String generateSudoku(@PathVariable("size") int size, Model model) {
        int[] rc = sudokuService.calculateSegmentRC(size);
        String[][] board = sudokuService.generateBoard(size);

        model.addAttribute("size", size);
        model.addAttribute("rows", size);
        model.addAttribute("cols", size);
        model.addAttribute("r", rc[0]);
        model.addAttribute("c", rc[1]);
        model.addAttribute("board", board);

        return "sudoku/board";
    }
}
