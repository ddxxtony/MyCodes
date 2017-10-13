/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinofinal;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
//import com.sun.xml.internal.stream.buffer.sax.Properties;
//import com.sun.xml.internal.fastinfoset.sax.Properties;
//import com.sun.xml.internal.ws.api.message.Packet;
import java.sql.SQLException;
//import java.sql.SQLInput;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import mysqlpackage.MySqlConn;


public class ArduinoFinal {
  static boolean  flag=true;
  //static String mensaje ="El paciente no tomó sus medicinas";
  static String subject = "Falta de toma";
  //static String to = "ntx.mail95@hotmail.com";
  static String username ="mitiendachia1995@gmail.com";
  static String  password = "mitiendachia1995";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
         MySqlConn sql = new MySqlConn();
         PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
         PanamaHitek_MultiMessage msg = new PanamaHitek_MultiMessage(2, arduino);
         SerialPortEventListener spel = new SerialPortEventListener() {

                @Override
                public void serialEvent(SerialPortEvent spe) {
                    try {
                        if (arduino.isMessageAvailable()){
                            //System.out.println("GETBACK:"+arduino.printMessage());
                            String text=arduino.printMessage();
                            System.out.println("TEXT:"+text);
                            if(text.equals("1")){
                                     sql.Update("insert into android.log values (default,now(),1,1);");
                                     sql.Consult("select * from android.medicamento where fechayhorado"
                + " between adddate(now(),interval -5 minute) and adddate(now(),interval 5 minute);");
                                try {
                                    int n2 =sql.rs.getRow();
                                    sql.rs.first();
                                    String color;
                                    for (int i =0;i<n2;i++){
                                           color=sql.rs.getString(4);
                                           switch(color){
                                               case "Rojo":
                                                   arduino.sendData("2");
                                                   break;
                                               case "Verde":
                                                   arduino.sendData("3");
                                                   break;
                                               case "Amarillo":
                                                   arduino.sendData("4");
                                                   break;
                                               case "Azul":
                                                   arduino.sendData("5");
                                                   break;
                                                  
                                           }//Fin de switch
                                           sql.rs.next();
                                            try{
                                                   Thread.sleep(1500);
                                                   }catch(Exception  ex){}
                                    
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(ArduinoFinal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    
                            }else if(text.equals("0")){
                                sql.Consult("select nombre from paciente where id=1;");
                                String namae = sql.rs.getString(1);
                                sql.Consult("select correo from responsable;");
                                int n = sql.rs.getRow();
                                sql.rs.first();
                                for (int i =0;i<n;i++){
                                SendMail("El Paciente"+namae+"ha olvidado tomar su medicina",sql.rs.getString(1));
                                sql.rs.next();
                                }
                                System.out.println("ANS1");
                            }
                            flag=true;
                                     sql.Update("update android.medicamento set fechayhorado = "
                                + "adddate(fechayhorado,interval intervalo minute)"
                                + " where idMedicamento=1;");
                              
                        }
                    } catch (SerialPortException ex) {
                        Logger.getLogger(ArduinoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ArduinoException ex) {
                        Logger.getLogger(ArduinoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ArduinoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                };
         
         
          try {
              arduino.arduinoRXTX("/dev/ACMtty0", 9600,spel);
            }catch (ArduinoException ex) {
            ex.printStackTrace();
            }
          
            sql.Connect();
        try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
        while (true){
              sql.Consult("select * from android.medicamento where fechayhorado"
                + " between adddate(now(),interval -5 minute) and adddate(now(),interval 5 minute);");
              System.out.println("Consulté");
                
        try{
            int n= sql.rs.getRow();
            
            if(n>0){
                int id = sql.rs.getInt(1);
                
                try {                  
                    if(flag==true){
                        arduino.sendData("1"); 
                        flag =false;
                        System.out.println("Envié Arduino");
                    // arduino.sendData("1");
                    }
                    
                    
                     
                
                    try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                } catch (ArduinoException ex) {} 
                catch (SerialPortException ex) {}
    
            }else{
            
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException ex) {
                }
            
            }
            // TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(ArduinoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public static void SendMail(String mensaje, String to) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(mensaje);
 
            Transport.send(message);
            //JOptionPane.showMessageDialog(this, "Su mensaje ha sido enviado");
            System.out.println("Su mensaje ha sido enviado");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
