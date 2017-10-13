
package biblioteca;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Categoría extends javax.swing.JFrame {
    
    boolean opciones=false;
    Transacciones transacciones;
    public Categoría() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        for (int i = 0; i < 3; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }        
        idcat.setVisible(false);
        categoria.setVisible(false);
        nombcat.setVisible(false);
        nombre.setVisible(false);
        descat.setVisible(false);
        descripcion.setVisible(false);
        transacciones= new Transacciones();
        BTModificar.setVisible(false);
        opclabel.setVisible(false);
        
        add.setVisible(false); 
             busqueda();
        
        close.setVisible(false);
    }
    
    //para el diseño de la tabla
        public class MyRenderer extends DefaultTableCellRenderer {

        Color background;
        Color foreground;

        public MyRenderer(Color background, Color foreground) {
            super();
            this.background = background;
            this.foreground = foreground;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            comp.setBackground(background);
            comp.setForeground(foreground);
            this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
            comp.setFont(new java.awt.Font("Verdana", 1, 11)); 
            return comp;
        }
    }
    
    //fin diseño de tabla
    
    public MysqlConn objConn = new MysqlConn();
    private boolean modifica = false, alta = false, elimina = false;

    private void llenarTabla(){
        String consulta = "select * from categoria";
        
        System.out.println(consulta);
        objConn.Consult(consulta);
        int n = 0;
        int i, j;
        if(objConn.rs!= null){
            //obtener numero de registros
            try{
                objConn.rs.last();
                n= objConn.rs.getRow();
                objConn.rs.first();
            }catch(Exception e){
            }
            //llenar la matriz de herramienta
            String [][] datos = new String [n][3];
                for (i = 0; i < n; i++) {
                    for (j = 0; j < 3; j++) {
                        try{
                            datos[i][j] = objConn.rs.getString(j+1);
                        }catch(Exception e){
                        }
                    }
                    try{
                        objConn.rs.next();
                    }catch(Exception e){
                    }
                } 
                //cargamos la informacion a la tabla
                String []columnas = new String []{
                 "#id","nombre","descripcion",
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    
    }
    private void busqueda(){
        String consulta = "select * from categoria where nombre like '"+ ETBuscar.getText().toString()+"%'";
        
        String consulta2="select * from categoria where nombre like '"+ ETBuscar.getText().toString()+"%'";
        System.out.println(consulta);
        objConn.Consult(consulta);
        int n = 0;
        int i, j;
        if(objConn.rs!= null){
            //obtener numero de registros
            try{
                objConn.rs.last();
                n= objConn.rs.getRow();
                objConn.rs.first();
            }catch(Exception e){
            }
            //llenar la matriz de herramienta
            String [][] datos = new String [n][3];
                for (i = 0; i < n; i++) {
                    for (j = 0; j < 3; j++) {
                        try{
                            datos[i][j] = objConn.rs.getString(j+1);
                        }catch(Exception e){
                        }
                    }
                    try{
                        objConn.rs.next();
                    }catch(Exception e){
                    }
                } 
                //cargamos la informacion a la tabla
                String []columnas = new String []{
                 "#id","nombre","descripcion",
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    }
    private void limpiaTxt(){
        categoria.setText("");
        nombre.setText("");
        descripcion.setText("");
        
    }
    
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);



    private void actualizaDatos(){
        int row = this.tblDatos.getSelectedRow();
        asignaTexto(categoria, row, 0);
        asignaTexto(nombre, row, 1);
        asignaTexto(descripcion, row, 2);
      //  asignaTexto(sinoplib, row, 3);
       
        //asignaTexto(sdf.format(date), row, 6);
       
        //asignaTexto(ubicacion, row, 7);
    }
    private void asignaTexto(javax.swing.JTextField tF, int row, int col){
        String texto = "";
        try{
            texto= tblDatos.getValueAt(row, col).toString();
        }catch(Exception e){
        }
        tF.setText(texto);
    }
    
    private void habilitaEdicion(boolean habilita){
        nombre.setEditable(habilita);
        descripcion.setEditable(habilita);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idcat = new javax.swing.JLabel();
        nombcat = new javax.swing.JLabel();
        descat = new javax.swing.JLabel();
        opclabel = new javax.swing.JLabel();
        addTool = new javax.swing.JButton();
        BTModificar = new javax.swing.JButton();
        add = new javax.swing.JButton();
        close = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        categoria = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        lblMessage1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ETBuscar = new javax.swing.JTextField();
        descripcion = new javax.swing.JTextField();
        close1 = new javax.swing.JButton();

        jLabel10.setText("CIUDAD");

        jLabel11.setText("CIUDAD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Categoría");

        idcat.setText("#id");
        idcat.setName(""); // NOI18N

        nombcat.setText("Nombre");

        descat.setText("Descripcion");

        opclabel.setText("Opciones");

        addTool.setBackground(new java.awt.Color(51, 51, 255));
        addTool.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        addTool.setForeground(new java.awt.Color(255, 255, 255));
        addTool.setText("Acciones para categorias");
        addTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToolActionPerformed(evt);
            }
        });

        BTModificar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BTModificar.setText("Modificar");
        BTModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTModificarActionPerformed(evt);
            }
        });

        add.setBackground(new java.awt.Color(0, 153, 0));
        add.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("agregar");
        add.setActionCommand("");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        close.setBackground(new java.awt.Color(255, 0, 51));
        close.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setText("ocultar");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        tblDatos.setBackground(Color.white);
        tblDatos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#id de la categoría", "Nombre", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDatos.setGridColor(new java.awt.Color(255, 204, 255));
        tblDatos.setOpaque(false);
        tblDatos.setShowVerticalLines(false);
        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDatosMouseReleased(evt);
            }
        });
        tblDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDatosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        close1.setBackground(new java.awt.Color(255, 0, 51));
        close1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        close1.setForeground(new java.awt.Color(255, 255, 255));
        close1.setText("Regresar");
        close1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(lblMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descat)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idcat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(175, 175, 175))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombcat)
                                .addGap(18, 18, 18)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(445, 445, 445))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ETBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(58, 58, 58)
                                .addComponent(addTool))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(124, 124, 124))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(close1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(opclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(542, 542, 542))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(close)
                        .addGap(112, 112, 112))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ETBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addTool))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcat)
                    .addComponent(nombcat)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(descat)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(lblMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(close)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTModificar)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close1))
                .addGap(127, 127, 127))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
       // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_buscarActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
       opciones=false;
        idcat.setVisible(false);
        categoria.setVisible(false);
        nombcat.setVisible(false);
        nombre.setVisible(false);
        descat.setVisible(false);
        descripcion.setVisible(false);
        BTModificar.setVisible(false);
        opclabel.setVisible(false);
        add.setVisible(false); 
        buscar.setVisible(true);
        add.setVisible(false);
        close.setVisible(false);
        addTool.setVisible(true);
        //actualizaDatos
       // lblMessage.setText("");
        alta=false;
        modifica=false;
        elimina=false;
    }//GEN-LAST:event_closeActionPerformed

    private void addToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToolActionPerformed
        // TODO add your handling code here:
        opciones=true;
        addTool.setVisible(false);
        habilitaEdicion(true);
        idcat.setVisible(true);
        categoria.setVisible(true);
        nombcat.setVisible(true);
        nombre.setVisible(true);
     
        BTModificar.setVisible(true);
        opclabel.setVisible(true);
        add.setVisible(true); 
        descat.setVisible(true);
        descripcion.setVisible(true);
       
        buscar.setVisible(true);
        
        add.setVisible(true);
        close.setVisible(true);
        alta = true;
    }//GEN-LAST:event_addToolActionPerformed

    private void BTModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTModificarActionPerformed
int ax = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas modificarlo?");
        if(ax == JOptionPane.YES_OPTION){
                transacciones.transaccion19(nombre.getText().toString(),descripcion.getText().toString(),categoria.getText().toString());
            llenarTabla();
           
        }
           
        
    }//GEN-LAST:event_BTModificarActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        System.out.println(transacciones.getNextIDCategoria()+"                              aqui esta");
                transacciones.T9(transacciones.getNextIDCategoria(),nombre.getText().toString(),descripcion.getText().toString(), "9");
   
        
        limpiaTxt();
        busqueda();
        for (int i = 0; i < 3; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }     
        this.tblDatos.setRowSelectionInterval(0,0);
       // lblMessage.setText("");
        actualizaDatos();
    }//GEN-LAST:event_addActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDatosMouseClicked

    private void tblDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDatosKeyReleased
        // TODO add your handling code here:
        actualizaDatos();
    }//GEN-LAST:event_tblDatosKeyReleased

    private void tblDatosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseReleased
        // TODO add your handling code here:
        actualizaDatos();
    }//GEN-LAST:event_tblDatosMouseReleased

    private void close1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close1ActionPerformed







  Frame ventana = new Principal();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();            // TODO add your handling code here:
    }//GEN-LAST:event_close1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Categoría.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Categoría.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Categoría.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Categoría.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Categoría().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTModificar;
    private javax.swing.JTextField ETBuscar;
    private javax.swing.JButton add;
    private javax.swing.JButton addTool;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField categoria;
    private javax.swing.JButton close;
    private javax.swing.JButton close1;
    private javax.swing.JLabel descat;
    private javax.swing.JTextField descripcion;
    private javax.swing.JLabel idcat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel nombcat;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel opclabel;
    private javax.swing.JTable tblDatos;
    // End of variables declaration//GEN-END:variables
}
