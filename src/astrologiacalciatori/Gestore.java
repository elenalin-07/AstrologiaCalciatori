/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrologiacalciatori;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author lin.elena
 */
public class Gestore {
    private FileManager fg;
    private String fileCalciatore, fileAstrologia;
    private ArrayList<Calciatore> calciatori;
    private ArrayList<Zodiaco> zodiaco;
    private int[] scala;
    
    public Gestore(String fc, String fa){
        fg = new FileManager();
        fileCalciatore = fc;
        fileAstrologia = fa;
        calciatori = new ArrayList<>();
        zodiaco = new ArrayList<>();
        scala = new int[12];
    }
    
    public void readFiles() throws IOException{
        ArrayList<String> lines = new ArrayList<>();
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
        String dataStart, dataEnd, nascita;
        int isBefore, isAfter, i = 0;
        for(Zodiaco z : zodiaco){
            dataStart = z.getDataStart();
            dataEnd = z.getDataEnd();
            for(Calciatore c : calciatori){
                nascita = c.getDataNascita();
                isAfter = nascita.compareTo(dataStart);
                isBefore = nascita.compareTo(dataEnd);
                if(isAfter >= 0 && isBefore <= 0){
                    c.setZodiaco(z.getNome());
                }
                else if(nascita.compareTo("0120") <= 0 || nascita.compareTo("1222") >= 0)
                    c.setZodiaco("Capricorno");
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
            scala[i] = goal;
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
            s = asterischi(max, scala[i]);
            risultato.add(s);
        }
        ArrayList<String> risultatoFinal  = new ArrayList<>();
        for(int i = 0; i < zodiaco.size(); i++){
            risultatoFinal.add(zodiaco.get(i).getNome() + "(" + scala[i] + ")" + risultato.get(i));
        }
        risultatoFinal.sort(Comparator.comparingInt(String::length).reversed());
        for(String line : risultatoFinal){
            System.out.println(line);
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
