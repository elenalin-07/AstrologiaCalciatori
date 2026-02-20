/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrologiacalciatori;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lin.elena
 */
public class FileManager {
    
    /**
     * Legge il file
     * 
     * @param filePath il percorso del file in cui viene letto
     * @return ArrayList<String> contenente i dati del file
     * @throws FileNotFoundException se il file non esiste o non è accessibile
     * @throws IOException se esiste errore di input/output
     */
    public ArrayList<String> readFile(String filePath) throws FileNotFoundException, IOException{
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        }
        return lines;
    }
}
