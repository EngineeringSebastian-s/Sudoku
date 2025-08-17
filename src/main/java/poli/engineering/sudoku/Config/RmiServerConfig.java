package poli.engineering.sudoku.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poli.engineering.sudoku.Service.ISudokuService;
import poli.engineering.sudoku.Service.SudokuService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Configuration
public class RmiServerConfig {

    @Bean
    public ISudokuService sudokuServiceRmi() throws Exception {
        // Iniciar el registro en el puerto 1099
        Registry registry = LocateRegistry.createRegistry(1099);

        // Crear la implementación remota
        ISudokuService service = new SudokuService();

        // Publicarla con el nombre "SudokuService"
        registry.rebind("SudokuService", service);

        System.out.println("✅ SudokuService RMI registrado en el puerto 1099");

        return service;
    }
}
