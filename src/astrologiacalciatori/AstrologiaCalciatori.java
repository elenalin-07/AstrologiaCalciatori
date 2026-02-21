/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package astrologiacalciatori;

import java.io.IOException;

/**
 *
 * @author lin.elena
 */
public class AstrologiaCalciatori {

    /**
     * @param args the command line arguments
     * @throws IOException se esiste errore di input/output
     */
    public static void main(String[] args) throws IOException {
        Gestore g = new Gestore("sportivi.csv", "zodiaco.csv");
        
        g.readFiles();
        g.setZodiaco();
        g.calcolaGoal();
        g.ordinaZodiaco();
        g.istogramma();
        g.stampa();
        
        Form f = new Form();
        f.setVisible(true);
    }
    
}
