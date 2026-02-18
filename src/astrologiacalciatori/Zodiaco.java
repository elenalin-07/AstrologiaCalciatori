/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrologiacalciatori;

/**
 *
 * @author lin.elena
 */
public class Zodiaco {
    private String nome, dataStart, dataEnd;
    
    public Zodiaco(String[] dati){
        nome = dati[0];
        dataStart = formatterData(dati[1]);
        dataEnd = formatterData(dati[2]);
    }
    
    public String formatterData(String s){
        String data = s.replace("/", "");
        data = data.substring(0, 4);
        return data;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getDataStart(){
        return dataStart;
    }
    
    public String getDataEnd(){
        return dataEnd;
    }
}
