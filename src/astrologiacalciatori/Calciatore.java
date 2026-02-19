/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrologiacalciatori;

/**
 *
 * @author lin.elena
 */
public class Calciatore {
    private String nome, dataNascita, nazionalita, zodiaco;
    private int goal;
    
    public Calciatore(String[] dati){
        nome = dati[0];
        goal = Integer.parseInt(dati[1]);
        nazionalita = dati[2];
        dataNascita = formatterData(dati[3]);
    }
    
    public String formatterData(String s){
        String data = s.replace("/", "");
        data = data.substring(0, 4);
        String g = data.substring(0, 2);
        String m = data.substring(2, 4);
        data = m + g;
        return data;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getGoal(){
        return goal;
    }
    
    public String getDataNascita(){
        return dataNascita;
    }
    
    public void setZodiaco(String z){
        zodiaco = z;
    }
    
    public String getZodiaco(){
        return zodiaco;
    }
}
