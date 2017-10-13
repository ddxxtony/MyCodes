/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.awt.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ellui
 */
public class Transacciones {
    
    public MysqlConn objConn = new MysqlConn();
    
    public  boolean transaccion1(String idl, String tit, String edit, String sinop,String idio, 
                            String pais, String fecha, String numpag, String idcat,String Ubicacion){ //T1 (Alta Libro)
        String consulta = "insert into libro2 values("+
                        "'" + getNextIDLibro() + "', '" + tit +"', '" + edit + "', '" + sinop + "', '" + 
                        idio + "', '" + pais + "', '" + fecha + "', " + numpag + ", '" + idcat + "','"+Ubicacion+"' ); ";
        System.out.println(consulta);
        objConn.Update(consulta);
        
        return false;
    }

public  String getNextIDLibro( ){ //T1 (Alta Libro)
       String consulta = "select MAX(a)+1 from (select SPLIT_STR(idlibro,'-', 2) AS a from libro) AS x;";
       
        objConn.Consult(consulta);
        String resultado="";
        try {
            if(objConn.rs.getString(1) != null){
                System.out.println("ENTRO NO NULL");
                String id= objConn.rs.getString(1).toString();
                int idmax=Integer.parseInt(id);
                resultado="r-"+idmax;
            }
            else {
                System.out.println("ENTRO NULL");
                resultado = "r-1";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
      
  public void T10(String s_c_i,String s_id,String Ctit,int eje,int vol,int anio){
                  try {
           
           objConn.Update("delete from libro_has_autor where Libro_Subcategoria_Categoria_idCategoria = '" + s_c_i + "' "
                            + "AND Libro_Subcategoria_idSub= '" + s_id +"' "
                            + "AND Libro_Ctitulo= '" + Ctit + "' "
                            + "AND Libro_numEjemplar= " + eje +" "
                            + "AND Libro_Volumen= " + vol + " "
                            + "AND Libro_fechaPubli= " + anio + ";");
                     
            int n=objConn.Update("delete from libro3 where Subcategoria_Categoria_idCategoria = '" + s_c_i + "' "
                            + "AND Subcategoria_idSub= '" + s_id +"' "
                            + "AND Ctitulo= '" + Ctit + "' "
                            + "AND numEjemplar= " + eje + " "
                            + "AND Volumen= " + vol + " "
                            + "AND fechaPubli= " + anio + ";");
            if(n==0){
                JOptionPane.showMessageDialog(null, "No puedes borrar este libro");
            }else{
                JOptionPane.showMessageDialog(null, "Se borró satisfactoriamente");
            } 
        } catch (Exception ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);  
        }
}

        public void transaccion3(String idus, String name, String app, String apm, String calle, 
                            String num,String colonia, String ciud, String cp, String tel,String tipo, String email){ //T3 (Agregar usuario)
            
              InetAddress IP;
     
          
        String consulta="";
        try {
            IP = InetAddress.getLocalHost();
                     String dir[]=IP.toString().split("/");
          if(dir[1].equals("192.168.0.150")){
           
        consulta = "insert into usuario2 values('" +idus + "',"
                + " '" + name + "', "
                + "'" + app + "',"
                + " '" + apm + "',"
                + " '" + calle + "', "
                + "'" +  num + "', "
                + "'" + colonia + "', "
                + "'" + ciud + "',"
                + " " + cp + ", "
                + "" + tel + ","
                + "'"+tipo+"',"
                + "'"+email+"');";
        
       }else  if(dir[1].equals("192.168.0.155")){
           
             consulta = "insert into usuario values('" +idus + "',"
                     + " '" + name + "', "
                     + "'" + app + "', "
                     + "'" + apm + "', "
                     + "'" + calle + "', "
                     + "'" + num + "',"
                     + " '" + colonia + "',"
                     + " '" + ciud + "', "
                     + "" + cp + ", "
                     + "" + tel + ","
                     + "'"+tipo+"',"
                     + "'"+email+"');";
       }else{
       consulta = "insert into usuario3 values("
               + "'" +idus + "', "
               + "'" + name + "',"
               + " '" + app + "', "
               + "'" + apm + "', "
               + "'" + calle + "', "
               + "'" + num + "',"
               + " '" + colonia + "',"
               + " '" + ciud + "',"
               + " " + cp + ", "
               + "" + tel + ","
               + "'"+tipo+"',"
               + "'"+email+"');";
       
       }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    
                
        System.out.println(consulta);
        objConn.Update(consulta);
    }
    
        
      public void transaccion20(String idl, String tit, String edit, String sinop,String idio, 
                            String pais, String fecha, String numpag, String idcat,String Ubicacion){ //T20(modificar algun libro existente)
        String consulta = "update libro set " +
                        "titulo = '" + tit + "' ," +
                        "editorial = '" + edit + "' ," +
                        "sinopsis = '" + sinop + "', " +
                        "idioma = '" + idio + "', " +
                        "pais = '" + pais + "' ," +
                        "fechapubli = '" + fecha + "' ," +
                        "numpag = " + numpag+ "," +
                        "ubicacion = '" + Ubicacion + "' " +
                        "where idlibro = '" + idl + "';";
        System.out.println(consulta);
        objConn.Update(consulta);
    }
      
          public void transaccion19(String nom, String desc, String idc){ //T19(Editar Categoria)
        String consulta = "update categoria set " +
                        "nombre = '" + nom + "', " +
                        "descripcion = '" + desc + "' " +
                        "where idcategoria = '" + idc + "';";
        String consulta2 = "update categoria2 set " +
                        "nombre = '" + nom + "', " +
                        "descripcion = '" + desc + "' " +
                        "where idcategoria = '" + idc + "';";
        String consulta3 = "update categoria3 set " +
                        "nombre = '" + nom + "', " +
                        "descripcion = '" + desc + "' " +
                        "where idcategoria = '" + idc + "';";
       // System.out.println(consulta);
        objConn.Update(consulta);
        objConn.Update(consulta2);
        objConn.Update(consulta3);
    }
          
       public void transaccion17(String idu){ //T17 (Dar de baja usuario)

           int id=Integer.parseInt(idu);
        String consulta = "";
        
        if (id>=1000000 && id<2000000){//rincon
         consulta = "delete from usuario2 " +
                        "where idusuario = '" + idu + "';";
         
        }else if(id>3000000){//aguascalientes
                        consulta = "delete from usuario " +
                        "where idusuario = '" + idu + "';";
        }else{//san jo
            consulta = "delete from usuario3 " +
                        "where idusuario = '" + idu + "';";
        
        }

        System.out.println(consulta);
        objConn.Update(consulta);
    }


public void transaccion18(String idus, String name, String app, String apm, String calle, 
                            String num,String colonia, String ciud, String cp, String tel,String tipo, String email){ //T18 (Modificar informacion del usuario)
        int id = Integer.parseInt(idus);
        String consulta = "";
         if (id<100000){//rincon
           consulta = "update usuario2 set " +
                        "nombre = '" + name + "', " +
                        "appat = '" + app + "', " +
                        "apmat = '" + apm + "', " +
                        "calle = '" + calle + "' ," +
                        "num = '" + num + "', " +
                        "colonia = '" + colonia + "', " +
                        "ciudad = '" + ciud + "', " +
                        "cp = " + cp + ", " +
                        "tel = " + tel + ", " +
                        "tipo = '" + tipo + "', " +
                        "email = '" + email + "'  " +
                        "where idusuario = '" + idus + "';";
           
        }else if(id>30000){//aguascalientes
                            consulta = "update usuario set " +
                        "nombre = '" + name + "', " +
                        "appat = '" + app + "', " +
                        "apmat = '" + apm + "', " +
                        "calle = '" + calle + "' ," +
                        "num = '" + num + "', " +
                        "colonia = '" + colonia + "', " +
                        "ciudad = '" + ciud + "', " +
                        "cp = " + cp + ", " +
                        "tel = " + tel + ", " +
                        "tipo = '" + tipo + "', " +
                        "email = '" + email + "'  " +
                        "where idusuario = '" + idus + "';";
        }else{//san jo
                   consulta = "update usuario3 set " +
                        "nombre = '" + name + "', " +
                        "appat = '" + app + "', " +
                        "apmat = '" + apm + "', " +
                        "calle = '" + calle + "' ," +
                        "num = '" + num + "', " +
                        "colonia = '" + colonia + "', " +
                        "ciudad = '" + ciud + "', " +
                        "cp = " + cp + ", " +
                        "tel = " + tel + ", " +
                        "tipo = '" + tipo + "', " +
                        "email = '" + email + "'  " +
                        "where idusuario = '" + idus + "';";
        
        }

       
        System.out.println(consulta);
        objConn.Update(consulta);
    }
    
              
              public  boolean T9(String id,String nombre, String Descripcion, String idlibro){
            try {
             //String idcategoria;
            int n=0;
            n=  objConn.Update("insert into categoria values ('"+id+"','"+nombre+"','"+Descripcion+"')");
            objConn.Update("insert into categoria2 values ('"+id+"','"+nombre+"','"+Descripcion+"')");
            objConn.Update("insert into categoria3 values ('"+id+"','"+nombre+"','"+Descripcion+"')");
          //    objConn.Consult("select idCategoria from categoria orderby idCategoria Desc limit 1");
          // idcategoria=  objConn.rs.getString(1); 
            //  objConn.Update("update libro set Categoria_idCategoria="+ idcategoria+"where idLibro="+idlibro);
            if (n!=0){
             return true;
            }else{
            return false;}
            
        } catch (Exception ex) {
                    Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   
   }
      public  String getNextIDCategoria( ){ //T1 (Alta Libro)
        String consulta = "select MAX(a) from (select SPLIT_STR(idcategoria,'-', 2) AS a  from categoria) as allcats";
       
         objConn.Consult(consulta);
         String resultado="";
        try {
              if(objConn.rs.getString(1) != null){
            String id= objConn.rs.getString(1).toString();
            int idmax=Integer.parseInt(id);
             idmax+=1;
            resultado="c-"+idmax;
              }   else {
            resultado="c-1";
           
              }
        } catch (SQLException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return resultado;
    }
      
    public  String getNextIDUsuario( ){ 

        String consulta="";        
        
        InetAddress IP;
        try {
            IP = InetAddress.getLocalHost();
             String dir[]=IP.toString().split("/");
     
             
       if(dir[1].equals("192.168.0.150")){
           
        consulta = "select MAX(idUsuario)+1 from usuario2";
       }else  if(dir[1].equals("192.168.0.155")){
           
             consulta = "select MAX(idUsuario)+1 from usuario";
       }else{
         consulta = "select MAX(idUsuario)+1 from usuario3";;
       
       }
        
       
       
        objConn.Consult(consulta);
        String resultado="";
        try {
            if(objConn.rs.getString(1) != null){
                System.out.println("ENTRO NO NULL");
                String id= objConn.rs.getString(1).toString();
                int idmax=Integer.parseInt(id);
                resultado=""+idmax;
            }
            else {
                System.out.println("ENTRO NULL");
                resultado = "1";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    } catch (UnknownHostException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
      return " ";
    }
      
      public  String getNextIDAutor( ){ //T1 (Alta Libro)
        String consulta = "select MAX(a)+1 from (select SPLIT_STR(idautor,'-', 2) AS a  from autor) as allcats";
       
         objConn.Consult(consulta);
         String resultado="";
        try {
            String id= objConn.rs.getString(1).toString();
            int idmax=Integer.parseInt(id);
            resultado="a-"+idmax;
        } catch (SQLException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return resultado;
    }
        

        
       public  String getNextIDTrabajador(String ciudad ){ //T1 (Alta Libro)
        String consulta = "select MAX(a)+1 from (select SPLIT_STR(idtrabajador,'-', 2) AS a  from trabajador) as allcats";
       
         objConn.Consult(consulta);
         String resultado="";
           System.out.println(ciudad);
        try {
            String id= objConn.rs.getString(1).toString();
            int idmax=Integer.parseInt(id);
            
            if(ciudad.equals("Aguascalientes")){
            return resultado="a-"+idmax;
            }else if (ciudad.equals("Rincón de Romos")){
           return resultado="r-"+idmax;
            }else if (ciudad.equals("San José de Gracia")){
              return  resultado="s-"+idmax;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return resultado;
    }
        
        
        
   public void transaccion1(String s_c_i,String s_id,String autor,String Ctit,int eje,int vol,int anio,String idi, String tit, String edi, String sin, String pais, int num){ 
//T1 (Alta Libro)
String consulta = "insert into libro3 values('" + s_c_i + "', '" + s_id+ "', '" + Ctit + "', " + eje + ", " + 
                            vol + ", " + anio + ", '" + idi + "', '" + tit + "', '" + edi + "', '" + sin + "', '" + pais + "', " + num + "); ";
                    System.out.println(consulta);
                    objConn.Update(consulta);

                    String consulta2 = "insert into libro_has_autor values('" + s_c_i + "','" + s_id + "','" + Ctit + "'," + eje + "," + 
                                    vol + "," + anio + ",'" + autor + "');";
                    System.out.println(consulta2);
                    objConn.Update(consulta2);
                    JOptionPane.showMessageDialog(null, "Se agregó satisfactoriamente el libro ");
 }


    
    
    
    public void transaccion4(String idTRab, String nombre, String appat, String apmat,
                 String Calle,String num,String col, String ciudad,String cp, String tel,
                 String email,  String sueldo, String permisop,String psw,String puesto){ //T4 (Agregar Trabajador y Asignar ubicacion )
        String consulta = "insert into trabajador values ('" +
                        idTRab + "', '" + nombre + "', '" + appat + "', '" + apmat + "', '" + Calle + "', '" 
                        + num + "', '" + col+ "', '" + ciudad + "', " + cp + ", " + tel + ", '" + email 
                        + "', " + sueldo + ", " + permisop + " " +
                        ",'"+psw+"', '"+puesto+"');";
        
        System.out.println(consulta);
        objConn.Update(consulta);
    }

    
    public ResultSet transaccion5(){ //T5(Lista de libros prestados de todas las bibliotecas involucradas )
        String consulta = "select l.idlibro, l.titulo, l.editorial, l.sinopsis, l.cantidad, l.idioma, " +
                        "l.pais, l.fechapubli, l.numpag, p.fechaprestamo, p.fechadevolucion " +
                        "from libro l, prestamo p, libro_has_prestamo lp " +
                        "where l.idlibro = lp.libro_idlibro " +
                        "and lp.prestamo_idprestamo = p.idprestamo;";
        System.out.println(consulta);
        objConn.Consult(consulta);
        return objConn.rs;
    }
    
    public ResultSet transaccion6(String ubica){ //T6 (Listado de trabajadores por ubicacion o en todas las ilbiotecas involucradas)
        if(ubica == null){
            String consulta = "select * from trabajador order by ubicacion;";
            System.out.println(consulta);
            objConn.Consult(consulta);
        }
        else if(ubica != null){
            String consulta = "select * from trabajador where ubicacion='" + ubica + "';";
            System.out.println(consulta);
            objConn.Consult(consulta);
        }
        return objConn.rs;
    }
    
    public ResultSet transaccion7(){ //T7 (Búsqueda de libro en biblioteca local y/o todas las bibliotecas involucradas )
        String consulta = "select * from usuario ";
        System.out.println(consulta);
        objConn.Consult(consulta);
        return objConn.rs;
    }
    
    public void transaccion15(String idl, String idp){ //T15 (cancelar prestamo )
        String consulta = "delete from libro_has_prestamo " +
                        "where libro_idlibro = '" + idl + "' " +
                        "and prestamo_idprestamo = '" + idp + "';";
        System.out.println(consulta);
        objConn.Consult(consulta);
        
        consulta = "delete from prestamo " +
                    "where idprestamo = '" + idp + "';";
        System.out.println(consulta);
        objConn.Consult(consulta);
    }
    
 public void transaccion20(String s_c_i,String s_id,String Ctit,int eje,int vol,int anio,String tit, String edi, String sin, String idi, String pais, int num, String autor){ //T20
        String consulta = "update libro3 set titulo = '" + tit + "', " +"editorial = '" + edi + "', " +
                        "sinopsis = '" + sin + "', " +
                        "idioma = '" + idi + "', " +
                        "pais = '" + pais + "', " +
                        "numpag = " + num + " where Subcategoria_Categoria_idCategoria = '" + s_c_i + "' AND Subcategoria_idSub= '" + s_id+ "' AND Ctitulo= '" + Ctit + "' AND numEjemplar= " + eje + " AND Volumen= " + vol + " AND fechaPubli= " + anio + ";";
        System.out.println(consulta);
        objConn.Update(consulta);
     
         String consulta2 = "update libro_has_autor set Autor_CAutor = '" + autor + "' where Libro_Subcategoria_Categoria_idCategoria = '" + s_c_i + "' AND Libro_Subcategoria_idSub= '" + s_id + "' AND Libro_Ctitulo= '" + Ctit + "' AND Libro_numEjemplar= " + eje + " AND Libro_Volumen= " + vol + " AND Libro_fechaPubli= " + anio + ";";
        
        System.out.println(consulta2);
        objConn.Update(consulta2);
}

    
    public void transaccion21(String nom, String desc, String ida){ //T21(Modificar autor existente)
        String consulta = "update autor set " +
                        "nombre = '" + nom + "' " +
                        "appaterno = '" + nom + "' " +
                        "apmaterno = '" + nom + "' " +
                        "lugarnacimiento = '" + desc + "' " +
                        "where idautor = '" + ida + "';";
        System.out.println(consulta);
        objConn.Consult(consulta);
    }
    
    public void T14(String idTRab, String nombre, String appat, String apmat,
                 String Calle,String num,String col, String ciudad,String cp, String tel,
                 String email,  String sueldo, String permisop,String psw,String puesto){
                         try {
            int n=0;
                    System.out.println("update trabajador set Nombre='"+nombre+"', ApPaterno='"+ appat+
                    "', ApMaterno='"+apmat+"', calle='"+Calle+"', num='"+num+"', colonia='"+col+"',"
                    + " ciudad='"+ciudad+"', cp="+cp+", tel="+tel+", email='"+email+"'"
                    + ", sueldo="+sueldo+", permisoPrestamo="+permisop+" , psw='"+ 
                    psw+"', puesto='"+puesto+"' where idtrabajador='"+
                    idTRab+"';");
            n=objConn.Update("update trabajador set Nombre='"+nombre+"', ApPaterno='"+ appat+
                    "', ApMaterno='"+apmat+"', calle='"+Calle+"', num='"+num+"', colonia='"+col+"',"
                    + " ciudad='"+ciudad+"', cp="+cp+", tel="+tel+", email='"+email+"'"
                    + ", sueldo="+sueldo+", permisoPrestamo="+permisop+" , contrasena='"+ 
                    psw+"', puesto='"+puesto+"' where idtrabajador='"+
                    idTRab+"';");
            /*if (n!=0){
             return true;
            }else{
            return false;}*/
            
        } catch (Exception ex) {
            /*Logger.getLogger(transaciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;*/
        }
   
   }
    
    public void T13(String idTRabajador){
        try {
             String idcategoria;
            int n=0;
            objConn.Update("delete from trabajador where idtrabajador='"+idTRabajador+"'");
            /*if (n!=0){
             return true;
            }else{
            return false;}*/
            
        } catch (Exception ex) {
            /*Logger.getLogger(transaciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;*/
        }
    }

public void transaccion2(String idl, String idu, String idt, String tipou, String ubicacion){ //T2 (. Realizar un préstamo de libro existente en cualquiera de las bibliotecas desde cualquier biblioteca  a usuario existente)
        //verificar que no exista idp, si no existe, agregar a prestamo, si ya existe mandar mensaje de existente
        JOptionPane.showMessageDialog(null, "UBICACION " + ubicacion);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        System.out.println(dia);
        String consulta = "";
        String consulta2 = "";
        int dias = 0;
        try{
            switch(tipou){
                case "Estudiante":
                    dias = 3;
                    break;
                    
                case "Profesor":
                    dias = 4;
                    break;
                    
                case "General":
                    dias = 3;
                    break;
                    
                case "Biblioteca":
                    dias = 30;
                    break;
            }
            
            switch(ubicacion){
                case "Aguas":
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "0, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Prestado": case "Reservado":
                                    JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                                    break;

                                case "Devuelto": case "Cancelado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo set " +
                                                        "situacion = 'Prestado', " +
                                                        "fechaprestamo = '"+dia+"', " +
                                                        "fechadevolucion = addDate(fechaprestamo, interval "+dias+" day) " +
                                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        JOptionPane.showMessageDialog(null, "¡El libro ha sido prestado!");
                                    }
                            }
                        }
                        objConn.closeRsStmt();
                    break;
                    
                case "Rincon":
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo2) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            consulta2 = "insert into prestamo2 values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "0, 'Reservado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Prestado": case "Reservado":
                                    JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                                    break;

                                case "Devuelto": case "Cancelado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo2) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo2 set " +
                                                        "situacion = 'Reservado', " +
                                                        "fechaprestamo = '"+dia+"', " +
                                                        "fechadevolucion = addDate(fechaprestamo, interval "+dias+" day) " +
                                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        JOptionPane.showMessageDialog(null, "¡El libro ha sido reservado!");
                                    }
                            }
                        }
                        objConn.closeRsStmt();
                    break;
                
                case "Sanjo":
                    JOptionPane.showMessageDialog(null, "SANJO");
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo3) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            consulta2 = "insert into prestamo3 values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "0, 'Reservado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Prestado": case "Reservado":
                                    JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                                    break;

                                case "Devuelto": case "Cancelado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo3) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo3 set " +
                                                        "situacion = 'Reservado', " +
                                                        "fechaprestamo = '"+dia+"', " +
                                                        "fechadevolucion = addDate(fechaprestamo, interval "+dias+" day) " +
                                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        JOptionPane.showMessageDialog(null, "¡El libro ha sido reservado!");
                                    }
                            }
                        }
                        objConn.closeRsStmt();
                    break;
            }
            
        } catch (Exception e){}
    }


    
     public void transaccion15(String idl, int accion, String ubicacion){ //T2 (. Cancelar o Devolver un préstamo de libro existente en cualquiera de las bibliotecas desde cualquier biblioteca  a usuario existente)
        //verificar que no exista idp, si no existe, agregar a prestamo, si ya existe mandar mensaje de existente
        JOptionPane.showMessageDialog(null, "UBICACION " + ubicacion);
        String hacer = "";
        if(accion == 1) hacer = "Cancelado";
        else if(accion == 2) hacer = "Devuelto";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        System.out.println(dia);
        String consulta = "";
        String consulta2 = "";
        int dias = 0;
        try{
            switch(ubicacion){
                case "Aguas":
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            /*consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "null, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);*/    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Devuelto": case "Cancelado":
                                    if(accion == 1) JOptionPane.showMessageDialog(null, "El libro ya ha sido cancelado.");
                                    else if(accion == 2) JOptionPane.showMessageDialog(null, "El libro ya fue devuelto.");
                                    break;

                                case "Prestado": case "Reservado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo set " +
                                                    "situacion = '"+hacer+"' " +
                                                    "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                    "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                    "and libro_ctitulo = '"+idsa[2]+"' " +
                                                    "and libro_numejemplar = "+idsa[3]+" " +
                                                    "and libro_volumen = "+idsa[4]+" " +
                                                    "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        if(accion == 1) JOptionPane.showMessageDialog(null, "¡Cancelación existosa!");
                                        else if(accion == 2){
                                            consulta = "update prestamo set " +
                                                        "fechaentrego = now() " +
                                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                            System.out.println(consulta);
                                            objConn.Update(consulta);
                                            
                                            consulta = "update prestamo set " +
                                                        "costomulta = costomulta+10 " +
                                                        "where fechadevolucion < fechaentrego " +
                                                        "and libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                            System.out.println(consulta);
                                            objConn.Update(consulta);
                                            JOptionPane.showMessageDialog(null, "El libro fue devuelto");
                                        }
                                    }
                            }
                        }
                    break;
                    
                case "Rincon":
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo2) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            /*consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "null, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);*/    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Devuelto": case "Cancelado":
                                    if(accion == 1) JOptionPane.showMessageDialog(null, "El libro ya ha sido cancelado.");
                                    else if(accion == 2) JOptionPane.showMessageDialog(null, "El libro ya fue devuelto.");
                                    break;

                                case "Prestado": case "Reservado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo2) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "SET collation_connection = latin1_spanish_ci; update prestamo2 set " +
                                                    "situacion = '"+hacer+"' " +
                                                    "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                    "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                    "and libro_ctitulo = '"+idsa[2]+"' " +
                                                    "and libro_numejemplar = "+idsa[3]+" " +
                                                    "and libro_volumen = "+idsa[4]+" " +
                                                    "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        if(accion == 1) JOptionPane.showMessageDialog(null, "¡Cancelación existosa!");
                                        else if(accion == 2){
                                            consulta = "update prestamo set " +
                                                        "fechaentrego = now() " +
                                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                            System.out.println(consulta);
                                            objConn.Update(consulta);
                                            
                                            consulta = "update prestamo set " +
                                                        "costomulta = costomulta+10 " +
                                                        "where fechadevolucion < fechaentrego " +
                                                        "and libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                            System.out.println(consulta);
                                            objConn.Update(consulta);
                                            JOptionPane.showMessageDialog(null, "El libro fue devuelto");
                                        }
                                    }
                            }
                        }
                    break;
                
                case "Sanjo":
                    JOptionPane.showMessageDialog(null, "SANJO");
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo3) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            /*consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "null, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);*/    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Devuelto": case "Cancelado":
                                    if(accion == 1) JOptionPane.showMessageDialog(null, "El libro ya ha sido cancelado.");
                                    else if(accion == 2) JOptionPane.showMessageDialog(null, "El libro ya fue devuelto.");
                                    break;

                                case "Prestado": case "Reservado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo3) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo3 set " +
                                                    "situacion = '"+hacer+"' " +
                                                    "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                    "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                    "and libro_ctitulo = '"+idsa[2]+"' " +
                                                    "and libro_numejemplar = "+idsa[3]+" " +
                                                    "and libro_volumen = "+idsa[4]+" " +
                                                    "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        if(accion == 1) JOptionPane.showMessageDialog(null, "¡Cancelación existosa!");
                                        else if(accion == 2){
                                            consulta = "update prestamo set " +
                                                        "fechaentrego = now() " +
                                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                            System.out.println(consulta);
                                            objConn.Update(consulta);
                                            
                                            consulta = "update prestamo set " +
                                                        "costomulta = costomulta+10 " +
                                                        "where fechadevolucion < fechaentrego " +
                                                        "and libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                                        "and libro_numejemplar = "+idsa[3]+" " +
                                                        "and libro_volumen = "+idsa[4]+" " +
                                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                            System.out.println(consulta);
                                            objConn.Update(consulta);
                                            JOptionPane.showMessageDialog(null, "El libro fue devuelto");
                                        }
                                    }
                            }
                        }
                    break;
            }
            objConn.closeRsStmt();
            
        } catch (Exception e){}
    }
     
     
public void transaccion16(String cantDias, String idl, String ubicacion){ //T16 (Renovar prestamo de libro)
        //verificar que no exista idp, si no existe, agregar a prestamo, si ya existe mandar mensaje de existente
        JOptionPane.showMessageDialog(null, "UBICACION " + ubicacion);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dia = dateFormat.format(date);
        System.out.println(dia);
        String consulta = "";
        String consulta2 = "";
        int dias = 0;
        try{
            switch(ubicacion){
                case "Aguas":
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            /*consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "null, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);*/    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Prestado": 
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo set " +
                                        "fechaprestamo = now(), " +
                                        "fechadevolucion = addDate(fechaprestamo, interval "+cantDias+" day) " +
                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                        "and libro_numejemplar = "+idsa[3]+" " +
                                        "and libro_volumen = "+idsa[4]+" " +
                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        JOptionPane.showMessageDialog(null, "¡Renovación Existosa!");
                                    }
                                    break;

                                case "Devuelto": case "Cancelado": case "Reservado":
                                    JOptionPane.showMessageDialog(null, "El libro no está disponible.");
                            }
                        }
                        objConn.closeRsStmt();
                    break;
                    
                case "Rincon":
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo2) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            /*consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "null, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);*/    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Devuelto": case "Cancelado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo2) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo2 set " +
                                        "fechaprestamo = now(), " +
                                        "fechadevolucion = addDate(fechaprestamo, interval "+cantDias+" day) " +
                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                        "and libro_numejemplar = "+idsa[3]+" " +
                                        "and libro_volumen = "+idsa[4]+" " +
                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        JOptionPane.showMessageDialog(null, "¡Renovación Existosa!");
                                    }
                                    break;

                                case "Prestado": case "Reservado":
                                    JOptionPane.showMessageDialog(null, "El libro no está disponible.");
                            }
                        }
                        objConn.closeRsStmt();
                    break;
                
                case "Sanjo":
                    JOptionPane.showMessageDialog(null, "SANJO");
                    consulta = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                        "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid, situacion from prestamo3) as tablaconc " +
                        "where tablaconc.concid = '" + idl + "'; ";
                        System.out.println(consulta);
                        objConn.Consult(consulta);
                        
                        if(objConn.rs.getRow() == 0){
                            /*consulta2 = "insert into prestamo values (SPLIT_STR('"+idl+"',' ', 1), SPLIT_STR('"+idl+"',' ', 2), "+
                                        "SPLIT_STR('"+idl+"',' ', 3), SPLIT_STR('"+idl+"',' ', 4), SPLIT_STR('"+idl+"',' ', 5), "+
                                        "SPLIT_STR('"+idl+"',' ', 6), '"+idu+"', '"+dia+"', addDate(fechaprestamo, interval "+dias+" day), "+
                                        "null, 'Prestado', null, '"+idt+"');";
                            System.out.println(consulta2);
                            objConn.Update(consulta2);*/    
                        }
                        else if(objConn.rs.getRow()== 1){
                            //JOptionPane.showMessageDialog(null, "El libro con código "+idl+" ya ha sido prestado.");
                            System.out.println("SITUACION " + objConn.rs.getString(2));
                            System.out.println("ID " + objConn.rs.getString(1));
                            String situation = objConn.rs.getString(2);
                            System.out.println("SITUACION " + situation);
                            switch(situation){
                                case "Devuelto": case "Cancelado":
                                    String consulta3 ="";
                                    String consulta4 ="";

                                    consulta3 = "select * from (select concat_ws(' ',libro_subcategoria_categoria_idcategoria,"+
                                                "libro_subcategoria_idsub,libro_ctitulo,libro_numejemplar,libro_volumen,libro_fechapubli) as concid from prestamo3) as tablaconc " +
                                                "where tablaconc.concid = '" + idl + "'; "; //Consulta que verifica si existe algun préstamo
                                    objConn.Consult(consulta3);

                                    if(objConn.rs.getRow() > 0){
                                        String idsa[] = idl.split(" ");
                                        consulta4 = "update prestamo3 set " +
                                        "fechaprestamo = now(), " +
                                        "fechadevolucion = addDate(fechaprestamo, interval "+cantDias+" day) " +
                                        "where libro_subcategoria_categoria_idcategoria = '"+idsa[0]+"' " +
                                        "and libro_subcategoria_idsub = '"+idsa[01]+"' " +
                                        "and libro_ctitulo = '"+idsa[2]+"' " +
                                        "and libro_numejemplar = "+idsa[3]+" " +
                                        "and libro_volumen = "+idsa[4]+" " +
                                        "and libro_fechapubli = "+idsa[5]+" ;";
                                        System.out.println(consulta4);
                                        objConn.Update(consulta4);
                                        JOptionPane.showMessageDialog(null, "¡Renovación Existosa!");
                                    }
                                    break;

                                case "Prestado": case "Reservado":
                                    JOptionPane.showMessageDialog(null, "El libro no está disponible.");
                            }
                        }
                        objConn.closeRsStmt();
                    break;
            }
            
        } catch (Exception e){}
    }
    
}

    

