
package biblioteca;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Usuario extends javax.swing.JFrame {
    Transacciones transaccion;
    
    public Usuario() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        for (int i = 0; i < 10; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }      
        transaccion=new Transacciones();
       iduser.setVisible(false);
       // usuarioid.setVisible(false);
        nomuser.setVisible(false);
        nombre.setVisible(false);
        apPat.setVisible(false);
        apePat.setVisible(false);
        ciudad.setVisible(false);
        ciudaduser.setVisible(false);
        numero.setVisible(false);
        tel.setVisible(false);
        teluser.setVisible(false);
        cp.setVisible(false);
        cpuser.setVisible(false);
        apMat.setVisible(false);
        apeMat.setVisible(false);
        calleUser.setVisible(false);
        calle.setVisible(false);
        numCalle.setVisible(false);
        sptipo.setVisible(false);
        etcolonia.setVisible(false);
        etcorreo.setVisible(false);
        tvcolonia.setVisible(false);
        tvcorreo.setVisible(false);
        tvtipo.setVisible(false);
        llenaDatos();
        
        add.setVisible(false);
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

    private void busqueda(){
        
        String consulta = "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia, ciudad) as dir, cp, tel,tipo, 'Aguascalientes' as ubicacion from usuario " + 
                            "where nombre like '"+etbusqueda.getText()+"%' union " +
                
                            "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia, ciudad) as dir, cp, tel,tipo, 'Aguascalientes' as ubicacion from usuario2 " + 
                            "where nombre like '"+etbusqueda.getText()+"%' union " +
                
                            "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia, ciudad) as dir, cp, tel,tipo, 'Aguascalientes' as ubicacion from usuario3 " + 
                            "where nombre like '"+etbusqueda.getText()+"%';";
        
      /*  String consulta=  "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia, ciudad) as dir, cp, tel from usuario2 " + 
                            "where nombre like '"+etbusqueda.getText()+"%'";
        */
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
            String [][] datos = new String [n][8];
                for (i = 0; i < n; i++) {
                    for (j = 0; j < 7; j++) {
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
                 "#idUsuario","nombre","direccion","cp","telefono","tipo","lugar de registro"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
       
    }
    private void limpiaTxt(){
               nombre.setText("");
            apePat.setText("");
            apeMat.setText("");
            calle.setText("");
            numero.setText("");
            ciudad.setText("");
            cpuser.setText("");
            tel.setText("");
            iduser.setText("ID-");
            etcorreo.setText("");
            etcolonia.setText("");
            sptipo.setSelectedItem("");

    }
    
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);
    

    


    public void llenaDatos(){
        
         String consulta = "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia,ciudad) as dir, cp, tel,tipo, 'Aguascalientes' as ubicacion from usuario " + 
                            " union " +
                
                            "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia,ciudad) as dir, cp, tel,tipo, 'Rincón de Romos' as ubicacion from usuario2 " + 
                            " union " +
                
                            "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,ciudad) as dir, cp, tel, tipo, 'San Jóse de Gracía' as ubicacion from usuario3 " + 
                            ";";
        
       
     /*  String consulta=  "select idusuario,concat_ws(' ', nombre, appat,apmat) username, " +
                            "concat_ws(', ',calle,num,colonia,ciudad) as dir, cp, tel from usuario2 ";
       */
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
            String [][] datos = new String [n][8];
                for (i = 0; i < n; i++) {
                    for (j = 0; j < 7; j++) {
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
                 "#idUsuario","nombre","direccion","cp","telefono","tipo","lugar de registro"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    
    }
    private void actualizaDatos(){
        int row = this.tblDatos.getSelectedRow();
       iduser.setText("ID-"+tblDatos.getValueAt(row,0).toString());
        MysqlConn con = new MysqlConn();
          int row2 = this.tblDatos.getSelectedRow();
      String idusr=tblDatos.getValueAt(row2,0).toString();
        con.Consult("select * from usuario where idusuario='"+idusr+"' "
                + "union select * from usuario2 where idusuario='"+idusr+"' "
                + "union select * from usuario3 where idusuario='"+idusr+"'");
         try {

        if(con.rs!=null){
           
            nombre.setText(con.rs.getString(2));
            apePat.setText(con.rs.getString(3));
            apeMat.setText(con.rs.getString(4));
            calle.setText(con.rs.getString(5));
            numero.setText(con.rs.getString(6));
            ciudad.setText(con.rs.getString(8));
            cpuser.setText(con.rs.getString(9));
            tel.setText(con.rs.getString(10));
            
            etcorreo.setText(con.rs.getString(12));
            etcolonia.setText(con.rs.getString(7));
            sptipo.setSelectedItem(con.rs.getString(11));
        
        }

        } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }        //asignaTexto(nombre, row, 1);
        //asignaTexto(apePat, row, 2);
      //  asignaTexto(sinoplib, row, 3);
       // asignaTexto(apeMat, row, 4);
        //asignaTexto(calle, row, 5);
        //asignaTexto(sdf.format(date), row, 6);       
        //asignaTexto(ubicacion, row, 7); 
    }
    
    
    public void muestraDat(){
      addTool.setVisible(false);
        iduser.setVisible(true);
        nomuser.setVisible(true);
        nombre.setVisible(true);
        apPat.setVisible(true);
        Clean.setVisible(true);
        apePat.setVisible(true);
       ciudad.setVisible(true);
        ciudaduser.setVisible(true);
        numero.setVisible(true);
        tel.setVisible(true);
        teluser.setVisible(true);
        cp.setVisible(true);
        cpuser.setVisible(true);
        apMat.setVisible(true);
        apeMat.setVisible(true);
        calleUser.setVisible(true);
        calle.setVisible(true);
        numCalle.setVisible(true);
         sptipo.setVisible(true);
        etcolonia.setVisible(true);
        etcorreo.setVisible(true);
        tvcolonia.setVisible(true);
        tvcorreo.setVisible(true);
        tvtipo.setVisible(true);
        buscar.setVisible(true);
       
        add.setVisible(true);
        close.setVisible(true);
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
        apePat.setEditable(habilita);
       
        apeMat.setEditable(habilita);
        calle.setEditable(habilita);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        iduser = new javax.swing.JLabel();
        nomuser = new javax.swing.JLabel();
        apPat = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addTool = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        add = new javax.swing.JButton();
        close = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        nombre = new javax.swing.JTextField();
        apePat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apeMat = new javax.swing.JTextField();
        apMat = new javax.swing.JLabel();
        calleUser = new javax.swing.JLabel();
        calle = new javax.swing.JTextField();
        numCalle = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        cp = new javax.swing.JLabel();
        cpuser = new javax.swing.JTextField();
        ciudaduser = new javax.swing.JLabel();
        ciudad = new javax.swing.JTextField();
        teluser = new javax.swing.JLabel();
        tel = new javax.swing.JTextField();
        etbusqueda = new javax.swing.JTextField();
        Back = new javax.swing.JButton();
        Clean = new javax.swing.JButton();
        tvcolonia = new javax.swing.JLabel();
        etcolonia = new javax.swing.JTextField();
        tvcorreo = new javax.swing.JLabel();
        etcorreo = new javax.swing.JTextField();
        tvtipo = new javax.swing.JLabel();
        sptipo = new javax.swing.JComboBox<>();

        jLabel10.setText("CIUDAD");

        jLabel11.setText("CIUDAD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Usuario");

        iduser.setText("ID:");
        iduser.setName(""); // NOI18N

        nomuser.setText("Nombre");

        apPat.setText("Apellido Paterno");

        jLabel9.setText("Opciones");

        addTool.setBackground(new java.awt.Color(51, 51, 255));
        addTool.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        addTool.setForeground(new java.awt.Color(255, 255, 255));
        addTool.setText("agregar usuario");
        addTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToolActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                "#id del usuario", "Nombre", "Apellido Paterno", "Apellido Materno", "Calle", "Número", "Ciudad", "C.P.", "Estado", "Tel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
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

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        apMat.setText("Apellido Materno");

        calleUser.setText("calle");

        calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleActionPerformed(evt);
            }
        });

        numCalle.setText("numero");

        cp.setText("C.P.");

        ciudaduser.setText("Ciudad");

        teluser.setText("Teléfono");

        Back.setBackground(new java.awt.Color(204, 0, 0));
        Back.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Back.setForeground(new java.awt.Color(255, 255, 255));
        Back.setText("Regresar");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Clean.setBackground(new java.awt.Color(255, 0, 51));
        Clean.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Clean.setForeground(new java.awt.Color(255, 255, 255));
        Clean.setText("Limpiar campos");
        Clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CleanActionPerformed(evt);
            }
        });

        tvcolonia.setText("Colonia");

        tvcorreo.setText(" correo electronico");

        tvtipo.setText("Tipo de usuario");

        sptipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Biblioteca", "General", "Estudiante", "Profesor" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(29, 29, 29)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(236, 236, 236))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(ciudaduser)
                                                    .addComponent(nomuser)
                                                    .addComponent(iduser))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(47, 47, 47)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(calle, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                            .addComponent(ciudad)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(48, 48, 48)
                                                        .addComponent(nombre))))
                                            .addComponent(calleUser))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(apPat)
                                            .addComponent(teluser)
                                            .addComponent(numCalle))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(apePat, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                                    .addComponent(numero))
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(apMat)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(tvcolonia)
                                                            .addComponent(cp)))))
                                            .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(apeMat)
                                            .addComponent(cpuser)
                                            .addComponent(etcolonia, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tvcorreo)
                                            .addComponent(tvtipo))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(etcorreo)
                                            .addComponent(sptipo, 0, 178, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(etbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(107, 107, 107)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addTool)))
                                .addGap(150, 150, 150))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Back)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Clean)
                                    .addGap(73, 73, 73)
                                    .addComponent(close))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1261, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(etbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addTool))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(nomuser))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iduser)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apPat)
                            .addComponent(apePat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apMat)
                            .addComponent(apeMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvcorreo)
                            .addComponent(etcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(calleUser)
                                    .addComponent(calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numCalle))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cp)
                                    .addComponent(cpuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ciudaduser)
                            .addComponent(teluser)
                            .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvcolonia)
                            .addComponent(etcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvtipo)
                            .addComponent(sptipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(close)
                            .addComponent(Clean))
                        .addGap(43, 43, 43)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(Back)))
                .addGap(109, 109, 109))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
       // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_buscarActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
      addTool.setVisible(true);
        iduser.setVisible(false);
        nomuser.setVisible(false);
        nombre.setVisible(false);
        apPat.setVisible(false);
        Clean.setVisible(false);
        apePat.setVisible(false);
       ciudad.setVisible(false);
        ciudaduser.setVisible(false);
        numero.setVisible(false);
        tel.setVisible(false);
        teluser.setVisible(false);
        cp.setVisible(false);
        cpuser.setVisible(false);
        apMat.setVisible(false);
        apeMat.setVisible(false);
        calleUser.setVisible(false);
        calle.setVisible(false);
        numCalle.setVisible(false);
         sptipo.setVisible(false);
        etcolonia.setVisible(false);
        etcorreo.setVisible(false);
        tvcolonia.setVisible(false);
        tvcorreo.setVisible(false);
        tvtipo.setVisible(false);
        buscar.setVisible(true);
       
        add.setVisible(false);
        close.setVisible(false);

        //actualizaDatos
       // lblMessage.setText("");
        alta=false;
        modifica=false;
        elimina=false;
    }//GEN-LAST:event_closeActionPerformed

    private void addToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToolActionPerformed
        // TODO add your handling code here:
        addTool.setVisible(false);
        habilitaEdicion(true);
        limpiaTxt();
        iduser.setVisible(true);
        nomuser.setVisible(true);
        nombre.setVisible(true);
        apPat.setVisible(true);
        Clean.setVisible(true);
        apePat.setVisible(true);
       ciudad.setVisible(true);
        ciudaduser.setVisible(true);
        numero.setVisible(true);
        tel.setVisible(true);
        teluser.setVisible(true);
        cp.setVisible(true);
        cpuser.setVisible(true);
        apMat.setVisible(true);
        apeMat.setVisible(true);
        calleUser.setVisible(true);
        calle.setVisible(true);
        numCalle.setVisible(true);
         sptipo.setVisible(true);
        etcolonia.setVisible(true);
        etcorreo.setVisible(true);
        tvcolonia.setVisible(true);
        tvcorreo.setVisible(true);
        tvtipo.setVisible(true);
        buscar.setVisible(true);
       
        add.setVisible(true);
        close.setVisible(true);
        alta = true;
    }//GEN-LAST:event_addToolActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int row = this.tblDatos.getSelectedRow();
      if (row>=0){        
        int ax = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas eliminar al usuario "+nombre.getText()+" ?");
        if(ax == JOptionPane.YES_OPTION){
            int row2 = this.tblDatos.getSelectedRow();
                String idusr=tblDatos.getValueAt(row2,0).toString();
            transaccion.transaccion17(idusr);
             llenaDatos();
        }
        else if(ax == JOptionPane.NO_OPTION){
                
        }
      }else{
            JOptionPane.showMessageDialog(null, "Tiene que seleccionar un usuario");
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
int row = this.tblDatos.getSelectedRow();
      if (row>=0){
        String [] id=iduser.getText().toString().split("-");
        transaccion.transaccion18(id[1],nombre.getText().toString(), apePat.getText().toString()
                   , apeMat.getText().toString(),calle.getText().toString(), numero.getText().toString()
                ,etcolonia.getText(), ciudad.getText().toString(),
                cpuser.getText(),tel.getText().toString(), sptipo.getSelectedItem().toString(),etcorreo.getText());
         llenaDatos();
      }else{
      JOptionPane.showMessageDialog(null, "Tiene que seleccionar un usuario");
      }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
       
        if(!nombre.getText().equals("") && !etcorreo.getText().equals("") && !etcolonia.getText().equals("")
                && !apePat.getText().equals("") && !apeMat.getText().equals("")){
        transaccion.transaccion3(transaccion.getNextIDUsuario(),nombre.getText().toString(), apePat.getText().toString()
                   , apeMat.getText().toString(),calle.getText().toString(), numero.getText().toString()
                ,etcolonia.getText(), ciudad.getText().toString(),
                cpuser.getText(),tel.getText().toString(), sptipo.getSelectedItem().toString(),etcorreo.getText());
        

        
        limpiaTxt();
        busqueda();
        for (int i = 0; i < 5; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }     
        this.tblDatos.setRowSelectionInterval(0,0);
       // lblMessage.setText("");
        llenaDatos();
        
        }else{
          JOptionPane.showMessageDialog(null, "Error no puede haber campos vacios");
        }
    }//GEN-LAST:event_addActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDatosMouseClicked

    private void tblDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDatosKeyReleased
        // TODO add your handling code here:
        actualizaDatos();
        muestraDat();
    }//GEN-LAST:event_tblDatosKeyReleased

    private void tblDatosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseReleased
        // TODO add your handling code here:
        actualizaDatos();
        muestraDat();
    }//GEN-LAST:event_tblDatosMouseReleased

    private void calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calleActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        Frame ventana = new Principal();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();     
    }//GEN-LAST:event_BackActionPerformed

    private void CleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CleanActionPerformed
      limpiaTxt();
    }//GEN-LAST:event_CleanActionPerformed

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Clean;
    private javax.swing.JButton add;
    private javax.swing.JButton addTool;
    private javax.swing.JLabel apMat;
    private javax.swing.JLabel apPat;
    private javax.swing.JTextField apeMat;
    private javax.swing.JTextField apePat;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField calle;
    private javax.swing.JLabel calleUser;
    private javax.swing.JTextField ciudad;
    private javax.swing.JLabel ciudaduser;
    private javax.swing.JButton close;
    private javax.swing.JLabel cp;
    private javax.swing.JTextField cpuser;
    private javax.swing.JTextField etbusqueda;
    private javax.swing.JTextField etcolonia;
    private javax.swing.JTextField etcorreo;
    private javax.swing.JLabel iduser;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nomuser;
    private javax.swing.JLabel numCalle;
    private javax.swing.JTextField numero;
    private javax.swing.JComboBox<String> sptipo;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField tel;
    private javax.swing.JLabel teluser;
    private javax.swing.JLabel tvcolonia;
    private javax.swing.JLabel tvcorreo;
    private javax.swing.JLabel tvtipo;
    // End of variables declaration//GEN-END:variables
}
