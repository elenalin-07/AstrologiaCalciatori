/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrologiacalciatori;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private ArrayList<String> output;
    private int max;
    
    /**
     * Costrutore
     * 
     * @param fc il percorso del file calciatori
     * @param fa il percorso del file astrologia(zodiaco)
     */
    public Gestore(String fc, String fa){
        fg = new FileManager();
        fileCalciatore = fc;
        fileAstrologia = fa;
        calciatori = new ArrayList<>();
        zodiaco = new ArrayList<>();
        scala = new int[12];
        output = new ArrayList<>();
    }
    
    /**
     * Legge i dati di file
     * 
     * @throws IOException se esiste errore di input/output
     */
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
    
    /**
     * Assegna a ciascun calciatore il segno zodiacale corrispondente
     */
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
    
    /**
     * Calcola il numero di goal totale di ciascun segno zodiacale
     */
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
    
    /**
     * Ordina l'array dei goal in ordine crescente e salva le posizioni originali degli elementi dopo l’ordinamento
     * 
     * @return ArrayList<Integer> contenente le posizioni originali degli elementi dopo l’ordinamento
     */
    public ArrayList<Integer> ordinaESalvaPos(){
        int[] originale = Arrays.copyOf(scala, scala.length);
        
        Arrays.sort(scala);
        
        ArrayList<Integer> posizioni = new ArrayList<>();
        
        for(int i = 0; i < scala.length; i++){
            for(int j = 0; j < originale.length; j++){
                if(scala[i] == originale[j]){
                    posizioni.add(j);
                }
            }
        }
        return posizioni;
    }
    
    /**
     * Ordina la lista dei segni zodiacali in base al numero di goal in ordine crescente
     */
    public void ordinaZodiaco(){
        ArrayList<Zodiaco> zodiacoOrdinato = new ArrayList<>();
        ArrayList<Integer> posizioni = ordinaESalvaPos();
        
        for(int i : posizioni){
            zodiacoOrdinato.add(zodiaco.get(i));
        }
        
        for(int i = 0; i < zodiaco.size(); i++){
            zodiaco.set(i, zodiacoOrdinato.get(i));
        }
    }
    
    /**
     * Genera un ArrayList di stringhe contenenti le barre di asterischi
     * 
     * @return ArrayList<String> contenente le barre di asterischi
     */
    public ArrayList<String> barreAsterischi(){
        setMax();
        String s;
        ArrayList<String> barreAsterischi = new ArrayList<>();
        for(int i = 0; i < zodiaco.size(); i++){
            s = asterischi(max, scala[i]);
            barreAsterischi.add(s);
        }
        
        return barreAsterischi;
    }
    
    public void istogramma(){
        ArrayList<String> istogramma = barreAsterischi();
        
        for(int i = 0; i < zodiaco.size(); i++){
            output.add(zodiaco.get(i).getNome() + "    \t(" + scala[i] + ")" + istogramma.get(i));
        }      
    }
    
    public void stampa(){
        for(int i = output.size() - 1; i >= 0; i--){
            System.out.println(output.get(i));
        }
    }
    
    /**
     * Costruisce un String contenente gli asterischi dell’istogramma che la barra più lunga abbia 50 asterischi 
     * 
     * @param max il numero massimo di goal tra tutti i segni
     * @param n il numero di goal del segno
     * @return una String contenente solo gli asterischi dell’istogramma, la cui lunghezza è proporzionale al numero di goal
     */
    public String asterischi(int max, int n){
        int size = 50 * n / max;
        String s = "";
        for(int i = 0; i < size; i++){
            s += "*";
        }
        return s;
    }
    
    public int[] getScala(){
        return scala;
    }
    
    public ArrayList<Zodiaco> getZodiaco(){
        return zodiaco;
    }
    
    public void setMax(){
        max = 0;
        for(int s : scala){
            if(s > max){
                max = s;
            }
        }
    }
    public int getMax(){
        return max;
    }
}
