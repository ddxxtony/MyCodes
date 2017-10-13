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
public class ListaRespuestas {

    ArrayList<Respuestas> lista;

    public ListaRespuestas() {
        this.lista = new ArrayList<Respuestas>();
    }

    public ArrayList<Respuestas> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Respuestas> lista) {
        this.lista = lista;
    }

}
