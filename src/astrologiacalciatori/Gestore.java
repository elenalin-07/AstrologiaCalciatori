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
    private ArrayList<Integer> scala;
    
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
                if(nascita >= dataStart && nascita <= dataEnd){
                    c.setZodiaco(z.getNome());
                }
            }
        }
    }
    
    public void calcolaGoal(){
        int i = 0, goal;
        for(Zodiaco z : zodiaco){
            goal = 0;
            for(Calciatore c : calciatori){
                if(c.getZodiaco().equals(z.getNome())){
                    goal += c.getGoal();
                }
            }
            scala.set(i, goal);
            i++;
        }
    }
    
    public void stampa(){
        int max = 0;
        for(int s : scala){
            if(s > max){
                max = s;
            }
        }
        
        String s;
        ArrayList<String> risultato = new ArrayList<>();
        for(int i = 0; i < zodiaco.size(); i++){
            s = asterischi(max, scala.get(i));
            risultato.add(s);
        }
        
        for(int i = 0; i < zodiaco.size(); i++){
            System.out.println(zodiaco.get(i).getNome() + "(" + scala.get(i) + ")" + risultato.get(i));
        }
    }
    
    public String asterischi(int max, int n){
        int size = 50 * n / max;
        String s = "";
        for(int i = 0; i < size; i++){
            s += "*";
        }
        return s;
    }
}
