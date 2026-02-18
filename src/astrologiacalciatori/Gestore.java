/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrologiacalciatori;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lin.elena
 */
public class Gestore {
    private FileManager fg;
    private String fileCalciatore, fileAstrologia;
    private ArrayList<Calciatore> calciatori;
    private ArrayList<Zodiaco> zodiaco;
    private ArrayList<String> scala;
    
    public Gestore(String fc, String fa){
        fg = new FileManager();
        fileCalciatore = fc;
        fileAstrologia = fa;
        calciatori = new ArrayList<>();
        zodiaco = new ArrayList<>();
        scala = new ArrayList<>();
    }
    
    public void readFiles() throws IOException{
        ArrayList<String> lines =  new ArrayList<>();
        lines = fg.readFile(fileCalciatore);
        
        for(String line : lines){
            String[] dati = line.split(",");
            calciatori.add(new Calciatore(dati));
        }
        
        lines = fg.readFile(fileAstrologia);
        
        for(String line : lines){
            String[] dati = line.split(",");
            zodiaco.add(new Zodiaco(dati));
        }
    }
    
    public void setZodiaco(){
        int dataStart, dataEnd, nascita;
        for(Zodiaco z : zodiaco){
            dataStart = Integer.parseInt(z.getDataStart());
            dataEnd = Integer.parseInt(z.getDataEnd());
            for(Calciatore c : calciatori){
                nascita = Integer.parseInt(c.getDataNascita());
            }
        }
    }
}
