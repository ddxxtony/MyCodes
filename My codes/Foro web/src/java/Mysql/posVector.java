package Mysql;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ellui
 */
public class posVector {
    
   int i;

    public posVector() {
        i=0;
    }
    
   public int Getposicion(ListaTemas x,ListaTemas y , int pos){
       
       for (int j = 0; j < y.getLista().size(); j++) {
          if( x.getLista().get(pos).id==y.getLista().get(j).getId()){
             i=j;
             return i;
          }
           
           
       }
      
       
       
       
       return i;
   
   }
   
   
   
   
  
    
}
