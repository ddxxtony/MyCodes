/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql;

import java.util.ArrayList;

/**
 *
 * @author ellui
 */
public class ListaTemas {
  
    ArrayList <Temas> lista;

    public ListaTemas() {
        ArrayList <Temas> lista=new ArrayList();
    }
    
    

    public void setLista(ArrayList<Temas> lista) {
        this.lista = lista;
    }

    public ArrayList<Temas> getLista() {
        return lista;
    }
    
    
    
    
}





