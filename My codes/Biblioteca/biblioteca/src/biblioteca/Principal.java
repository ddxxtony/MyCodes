package biblioteca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class Principal extends javax.swing.JFrame {
    
    
    
    public Principal() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        ImageIcon autor = new javax.swing.ImageIcon(getClass().getResource("/images/iautor.png"));
        Image imautor = autor.getImage();
        ImageIcon iautor = new ImageIcon (imautor.getScaledInstance(100,90,Image.SCALE_SMOOTH));
        jLabel1.setIcon(iautor);
        //la de biblioteca
        ImageIcon biblio = new javax.swing.ImageIcon(getClass().getResource("/images/biblioteca.png"));
        Image imbiblio = biblio.getImage();
        ImageIcon ibiblio = new ImageIcon (imbiblio.getScaledInstance(100,90,Image.SCALE_SMOOTH));
        jLabel3.setIcon(ibiblio);
        
        //la de libro
        ImageIcon libro = new javax.swing.ImageIcon(getClass().getResource("/images/libro.png"));
        Image imlibro = libro.getImage();
        ImageIcon ilibro = new ImageIcon (imlibro.getScaledInstance(130,130,Image.SCALE_SMOOTH));
        jLabel4.setIcon(ilibro);
        
        //la de préstamo
        ImageIcon pres = new javax.swing.ImageIcon(getClass().getResource("/images/prestamo.png"));
        Image impres = pres.getImage();
        ImageIcon ipres = new ImageIcon (impres.getScaledInstance(100,90,Image.SCALE_SMOOTH));
        jLabel5.setIcon(ipres);
        
        //la de trabajadores
        ImageIcon trab = new javax.swing.ImageIcon(getClass().getResource("/images/trabajadores.png"));
        Image imtrab = trab.getImage();
        ImageIcon itrab = new ImageIcon (imtrab.getScaledInstance(110,110,Image.SCALE_SMOOTH));
        jLabel7.setIcon(itrab);
        
        //la de ususarios
        ImageIcon usua = new javax.swing.ImageIcon(getClass().getResource("/images/usuarios.png"));
        Image imusua = usua.getImage();
        ImageIcon iusua = new ImageIcon (imusua.getScaledInstance(110,110,Image.SCALE_SMOOTH));
        jLabel6.setIcon(iusua);
        
         //la de categoría
        ImageIcon cate = new javax.swing.ImageIcon(getClass().getResource("/images/categorías.png"));
        Image imcate = cate.getImage();
        ImageIcon icate = new ImageIcon (imcate.getScaledInstance(70,70,Image.SCALE_SMOOTH));
        jLabel8.setIcon(icate);
        
        //cerrar sesion
        ImageIcon sesion = new javax.swing.ImageIcon(getClass().getResource("/images/cerrarSision.png"));
        Image imsesion = sesion.getImage();
        ImageIcon isesion = new ImageIcon (imsesion.getScaledInstance(30,30,Image.SCALE_SMOOTH));
        jLabel9.setIcon(isesion);
    }
  
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close = new javax.swing.JButton();
        close1 = new javax.swing.JButton();
        close2 = new javax.swing.JButton();
        close3 = new javax.swing.JButton();
        close4 = new javax.swing.JButton();
        close5 = new javax.swing.JButton();
        close6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1129, 642));
        setResizable(false);

        close.setBackground(new java.awt.Color(255, 255, 255));
        close.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close.setForeground(new java.awt.Color(51, 153, 255));
        close.setText("Libros");
        close.setBorderPainted(false);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        close1.setBackground(new java.awt.Color(255, 255, 255));
        close1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close1.setForeground(new java.awt.Color(51, 153, 255));
        close1.setText("Trabajadores");
        close1.setBorderPainted(false);
        close1.setFocusPainted(false);
        close1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close1ActionPerformed(evt);
            }
        });

        close2.setBackground(new java.awt.Color(255, 255, 255));
        close2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close2.setForeground(new java.awt.Color(51, 153, 255));
        close2.setText("Autor");
        close2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        close2.setBorderPainted(false);
        close2.setContentAreaFilled(false);
        close2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        close2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close2ActionPerformed(evt);
            }
        });

        close3.setBackground(new java.awt.Color(255, 255, 255));
        close3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close3.setForeground(new java.awt.Color(51, 153, 255));
        close3.setText("Biblioteca");
        close3.setBorderPainted(false);
        close3.setRequestFocusEnabled(false);
        close3.setRolloverEnabled(false);
        close3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close3ActionPerformed(evt);
            }
        });

        close4.setBackground(new java.awt.Color(255, 255, 255));
        close4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close4.setForeground(new java.awt.Color(51, 153, 255));
        close4.setText("Préstamos");
        close4.setBorderPainted(false);
        close4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close4ActionPerformed(evt);
            }
        });

        close5.setBackground(new java.awt.Color(255, 255, 255));
        close5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close5.setForeground(new java.awt.Color(51, 153, 255));
        close5.setText("Categorías");
        close5.setBorderPainted(false);
        close5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close5ActionPerformed(evt);
            }
        });

        close6.setBackground(new java.awt.Color(255, 255, 255));
        close6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        close6.setForeground(new java.awt.Color(102, 153, 255));
        close6.setText("Usuarios");
        close6.setBorderPainted(false);
        close6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Elige una opción");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("cerrar sesión");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 301, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close3)
                    .addComponent(close4)
                    .addComponent(close))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(close5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(close1))
                .addGap(260, 260, 260))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(close2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(close1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(close3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(close6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(14, 14, 14)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(111, 111, 111))
                                .addComponent(close5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(258, 258, 258)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addComponent(close4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(117, 117, 117))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
 Frame ventana = new Libro();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();    
       
    }//GEN-LAST:event_closeActionPerformed

    private void close1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close1ActionPerformed
 Frame ventana = new Trabajador();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_close1ActionPerformed

    private void close2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close2ActionPerformed
        Frame ventana = new Autor();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();        
    }//GEN-LAST:event_close2ActionPerformed

    private void close3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close3ActionPerformed
 Frame ventana = new Biblioteca();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_close3ActionPerformed

    private void close4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close4ActionPerformed
 Frame ventana = new Préstamo();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_close4ActionPerformed

    private void close5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close5ActionPerformed
    Frame ventana = new Categoría();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_close5ActionPerformed

    private void close6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close6ActionPerformed
    Frame ventana = new Usuario();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_close6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         this.dispose();
         Biblioteca biblio = new Biblioteca();
         biblio.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JButton close1;
    private javax.swing.JButton close2;
    private javax.swing.JButton close3;
    private javax.swing.JButton close4;
    private javax.swing.JButton close5;
    private javax.swing.JButton close6;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
