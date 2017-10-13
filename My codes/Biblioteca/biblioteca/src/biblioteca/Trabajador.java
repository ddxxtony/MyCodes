
package biblioteca;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Trabajador extends javax.swing.JFrame {
    boolean bandera = true;
    
    public Trabajador() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        for (int i = 0; i < 7; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }        
        idtrab.setVisible(false);
        nombretrab.setVisible(false);
        nombre.setVisible(false);
        apPat.setVisible(false);
        apePat.setVisible(false);
        ciudad.setVisible(false);
        nomciudad.setVisible(false);
        telefono.setVisible(false);
        teltrab.setVisible(false);
        sueldo.setVisible(false);
        sueldotrab.setVisible(false);
        permiso.setVisible(false);
        cp.setVisible(false);
        cptrab.setVisible(false);
        numero.setVisible(false);
        apMat.setVisible(false);
        apeMat.setVisible(false);
        calletrab.setVisible(false);
        calle.setVisible(false);
        numTrab.setVisible(false);
        ubicatrab.setVisible(false);
        ubicacion.setVisible(false);
        
        id.setVisible(false);
        etCorreo.setVisible(false);
        etcolonia.setVisible(false);
        tvcolonia.setVisible(false);
        tvemail.setVisible(false);
        tvpuesto.setVisible(false);
        psw.setVisible(false);
        sppuesto.setVisible(false);
        
        
        add.setVisible(false);
        close.setVisible(false);
        
        if(bandera == false){
            addTool.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jLabel9.setVisible(false);
        }
        
        llenarTabla();
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
        String consulta = "select * from trabajador";
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
                    for (j = 0; j < 8; j++) {
                        try{
                            datos[i][0] = objConn.rs.getString(1);
                            datos[i][1] = objConn.rs.getString(2)+" "+objConn.rs.getString(3)+" "+objConn.rs.getString(4);
                            datos[i][2] = objConn.rs.getString(5)+" #"+objConn.rs.getString(6)+", "+objConn.rs.getString(8)+". "+objConn.rs.getString(7)+", "+objConn.rs.getString(9)+".";
                            datos[i][3] = objConn.rs.getString(10);
                            datos[i][5] = objConn.rs.getString(12);
                            datos[i][4] = objConn.rs.getString(11);
                              datos[i][6] =getubicacion(datos[i][0]);
                            if(objConn.rs.getString(13).equals("1")){
                                datos[i][7] = "Con permiso.";
                            }
                            else if(objConn.rs.getString(13).equals("0")){
                                datos[i][7] = "Sin permiso.";
                            }
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
                 "#ID Trabajador","Nombre","Direccion","Teléfono","email","Sueldo","Ubicación","Permisos"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    }
    
    private String getubicacion(String id){
        String [] resul=id.split("-");
        switch (resul[0]){
                case "a": return "Aguascalientes";
                    
                 case "r": return "Rincón de Romos"; 
                     
                    case "s": 
                        return "San José de Grácia";

        }
        
        
    return "fallo";
    }
    
    
     private void busqueda(){
        String consulta = "select * from trabajador where nombre like '"+ ETBuscar.getText().toString()+"%'";
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
                    for (j = 0; j < 8; j++) {
                        try{
                            datos[i][0] = objConn.rs.getString(1);
                            datos[i][1] = objConn.rs.getString(2)+" "+objConn.rs.getString(3)+" "+objConn.rs.getString(4);
                            datos[i][2] = objConn.rs.getString(5)+" #"+objConn.rs.getString(6)+", "+objConn.rs.getString(8)+". "+objConn.rs.getString(7)+", "+objConn.rs.getString(9)+".";
                            datos[i][3] = objConn.rs.getString(10);
                            datos[i][5] = objConn.rs.getString(12);
                            datos[i][4] = objConn.rs.getString(11);
                              datos[i][6] =getubicacion(datos[i][0]);
                            if(objConn.rs.getString(13).equals("1")){
                                datos[i][7] = "Con permiso.";
                            }
                            else if(objConn.rs.getString(13).equals("0")){
                                datos[i][7] = "Sin permiso.";
                            }
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
                    "#ID Trabajador","Nombre","Direccion","Teléfono","email","Sueldo","Ubicación","Permisos"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    }
    private void limpiaTxt(){
                 etcolonia.setText("");
                psw.setText("");
                nombre.setText("");
                apePat.setText("");
                apeMat.setText("");
                calle.setText("");
                numero.setText("");
                ciudad.setText("");
                cp.setText("");
                id.setText("");
                sueldo.setText("");
                telefono.setText("");
                etCorreo.setText("");

                                permiso.setSelected(false);
        
    }
    
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);
    

    
    private void Update(){
       String consulta = "Update libro set "
                +" titulo = '"+nombre.getText()+"',"
                +" editorial = '"+apePat.getText()+"',"
               
                +" idioma = '"+apeMat.getText()+"',"
                + "pais= '"+calle.getText()+"',"
                + "fechaPubli= '"+sdf.format(date)+"',"
               
                + "ubicacion='"+ubicacion.getSelectedItem()+"')";
              
                System.out.println(consulta);
                int n=objConn.Update(consulta);
                System.out.println(n+" registros modificados");
    }
    private void Delete(){
        //String consulta = "delete from libro "+ "where id="+trabaja.getText()+";";
      //  System.out.println(consulta);
        //int n = objConn.Update(consulta);
        //System.out.println(n+" registros modificados");
    }
    private void actualizaDatos(){
        int row = this.tblDatos.getSelectedRow();
        
        String consulta = "select * from trabajador where idtrabajador='"+ tblDatos.getValueAt(row, 0).toString() +"';";
        objConn.Consult(consulta);
        if(objConn.rs != null){
            try{
                idtrab.setVisible(true);
                nombretrab.setVisible(true);
                nombre.setVisible(true);
                apPat.setVisible(true);
                apePat.setVisible(true);
                apMat.setVisible(true);
                apeMat.setVisible(true);
                calletrab.setVisible(true);
                calle.setVisible(true);
                numTrab.setVisible(true);
                numero.setVisible(true);
                cp.setVisible(true);
                cptrab.setVisible(true);
                nomciudad.setVisible(true);
                ciudad.setVisible(true);
                teltrab.setVisible(true);
                telefono.setVisible(true);
                sueldo.setVisible(true);
                sueldotrab.setVisible(true);
                permiso.setVisible(true);
                ubicatrab.setVisible(true);
                ubicacion.setVisible(true);
                add.setVisible(true);
                close.setVisible(true);
                addTool.setVisible(false);
                
                 id.setVisible(true);
        etCorreo.setVisible(true);
        etcolonia.setVisible(true);
        tvcolonia.setVisible(true);
        tvemail.setVisible(true);
        tvpuesto.setVisible(true);
        psw.setVisible(true);
        sppuesto.setVisible(true);
        
                etcolonia.setText(objConn.rs.getString(7));
                psw.setText(objConn.rs.getString(14));
                nombre.setText(objConn.rs.getString(2));
                apePat.setText(objConn.rs.getString(3));
                apeMat.setText(objConn.rs.getString(4));
                calle.setText(objConn.rs.getString(5));
                numero.setText(objConn.rs.getString(6));
                ciudad.setText(objConn.rs.getString(8));
                cp.setText(objConn.rs.getString(9));
                id.setText("ID:"+objConn.rs.getString(1));
                sueldo.setText(objConn.rs.getString(12));
                telefono.setText(objConn.rs.getString(10));
                etCorreo.setText(objConn.rs.getString(11));
                sppuesto.setSelectedItem(objConn.rs.getString(15));
                ubicacion.setSelectedItem(getubicacion(objConn.rs.getString(15)));
                if(objConn.rs.getString(13).equals("1")){
                                permiso.setSelected(true);
                            }
                            else {
                                permiso.setSelected(false);
                            }
            }catch(Exception e){
                
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idtrab = new javax.swing.JLabel();
        nombretrab = new javax.swing.JLabel();
        apPat = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addTool = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        add = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        nombre = new javax.swing.JTextField();
        apePat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apeMat = new javax.swing.JTextField();
        apMat = new javax.swing.JLabel();
        calletrab = new javax.swing.JLabel();
        calle = new javax.swing.JTextField();
        numTrab = new javax.swing.JLabel();
        ubicatrab = new javax.swing.JLabel();
        ubicacion = new javax.swing.JComboBox();
        numero = new javax.swing.JTextField();
        cptrab = new javax.swing.JLabel();
        cp = new javax.swing.JTextField();
        nomciudad = new javax.swing.JLabel();
        ciudad = new javax.swing.JTextField();
        teltrab = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        sueldotrab = new javax.swing.JLabel();
        sueldo = new javax.swing.JTextField();
        permiso = new javax.swing.JCheckBox();
        Buscar = new javax.swing.JButton();
        back = new javax.swing.JButton();
        ETBuscar = new javax.swing.JTextField();
        tvemail = new javax.swing.JLabel();
        etCorreo = new javax.swing.JTextField();
        tvpuesto = new javax.swing.JLabel();
        sppuesto = new javax.swing.JComboBox<>();
        psw = new javax.swing.JPasswordField();
        tvcolonia = new javax.swing.JLabel();
        etcolonia = new javax.swing.JTextField();
        id = new javax.swing.JLabel();

        jLabel10.setText("CIUDAD");

        jLabel11.setText("CIUDAD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Trabajador");

        idtrab.setText("contraseña");
        idtrab.setName(""); // NOI18N

        nombretrab.setText("Nombre");

        apPat.setText("Apellido Paterno");

        jLabel9.setText("Opciones");

        addTool.setBackground(new java.awt.Color(51, 51, 255));
        addTool.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        addTool.setForeground(new java.awt.Color(255, 255, 255));
        addTool.setText("agregar trabajador");
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
        close.setText("cancelar");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        tblDatos.setBackground(Color.white);
        tblDatos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#id del trabajador", "Nombre", "Calle", "C.P.", "Tel", "sueldo", "Ubicación", "Permiso de préstamo", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Byte.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
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

        apMat.setText("Apellido Materno");

        calletrab.setText("calle");

        calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleActionPerformed(evt);
            }
        });

        numTrab.setText("numero");

        ubicatrab.setText("Ubicación");

        ubicacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aguascalientes", "Rincón de Romos", "San José de Gracia" }));
        ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubicacionActionPerformed(evt);
            }
        });

        cptrab.setText("C.P");

        cp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpActionPerformed(evt);
            }
        });

        nomciudad.setText("Ciudad");

        teltrab.setText("Teléfono");

        sueldotrab.setText("sueldo");

        permiso.setText("Permiso para prestar libros");

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(204, 0, 0));
        back.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("Regresar");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        tvemail.setText("Email");

        tvpuesto.setText("Puesto");

        sppuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jefe", "Bibliotecario" }));

        psw.setText("jPass");

        tvcolonia.setText("Colonia");

        id.setText("ID: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(22, 22, 22))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(idtrab)
                                                            .addGap(30, 30, 30))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(nomciudad)
                                                            .addGap(51, 51, 51)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(calletrab)
                                                        .addGap(63, 63, 63)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(ciudad)
                                                    .addComponent(calle)
                                                    .addComponent(psw, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nombretrab)
                                                    .addComponent(numTrab)
                                                    .addComponent(teltrab)))
                                            .addComponent(id))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(53, 53, 53)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(sueldotrab)
                                                    .addComponent(cptrab, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(65, 65, 65)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cp, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(apPat)
                                                .addGap(18, 18, 18)
                                                .addComponent(apePat, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tvemail)
                                                .addGap(74, 74, 74)
                                                .addComponent(etCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tvcolonia)
                                                .addGap(63, 63, 63)
                                                .addComponent(etcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(apMat)
                                                .addGap(18, 18, 18)
                                                .addComponent(apeMat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ETBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(91, 91, 91)
                                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ubicatrab)
                                    .addComponent(tvpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel3)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(close)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sppuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addTool)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(permiso)
                                            .addGap(19, 19, 19))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ETBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addComponent(id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(addTool, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(apPat))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idtrab)
                            .addComponent(nombretrab)
                            .addComponent(nombre)
                            .addComponent(psw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(apePat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(apMat)
                        .addComponent(apeMat)
                        .addComponent(ubicatrab)
                        .addComponent(ubicacion)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(calletrab)
                        .addComponent(calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numTrab)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cptrab)
                        .addComponent(cp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tvpuesto)
                        .addComponent(sppuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tvcolonia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etcolonia))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomciudad)
                            .addComponent(teltrab)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sueldotrab)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvemail)
                            .addComponent(etCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(permiso)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton2))
                    .addComponent(back))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        idtrab.setVisible(false);
        nombretrab.setVisible(false);
        nombre.setVisible(false);
        apPat.setVisible(false);
        apePat.setVisible(false);
        apMat.setVisible(false);
        apeMat.setVisible(false);
        calletrab.setVisible(false);
        calle.setVisible(false);
        numTrab.setVisible(false);
        numero.setVisible(false);
       // estadotrab.setVisible(false);
        //estado.setVisible(false);
        cp.setVisible(false);
        cptrab.setVisible(false);
        nomciudad.setVisible(false);
        ciudad.setVisible(false);
        teltrab.setVisible(false);
        telefono.setVisible(false);
        sueldo.setVisible(false);
        sueldotrab.setVisible(false);
        permiso.setVisible(false);
        ubicatrab.setVisible(false);
        ubicacion.setVisible(false);
        add.setVisible(false);
        close.setVisible(false);
        addTool.setVisible(true);
        
        id.setVisible(false);
        etCorreo.setVisible(false);
        etcolonia.setVisible(false);
        tvcolonia.setVisible(false);
        tvemail.setVisible(false);
        tvpuesto.setVisible(false);
        psw.setVisible(false);
        sppuesto.setVisible(false);
        
        alta=false;
        modifica=false;
        elimina=false;
    }//GEN-LAST:event_closeActionPerformed

    private void addToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToolActionPerformed
        // TODO add your handling code here:
               id.setVisible(true);
        etCorreo.setVisible(true);
        etcolonia.setVisible(true);
        tvcolonia.setVisible(true);
        tvemail.setVisible(true);
        tvpuesto.setVisible(true);
        psw.setVisible(true);
        sppuesto.setVisible(true);
        addTool.setVisible(false);
        limpiaTxt();
        idtrab.setVisible(true);
        nombretrab.setVisible(true);
        nombre.setVisible(true);
        apPat.setVisible(true);
        apePat.setVisible(true);
       ciudad.setVisible(true);
        nomciudad.setVisible(true);
        telefono.setVisible(true);
        teltrab.setVisible(true);
        sueldo.setVisible(true);
        sueldotrab.setVisible(true);
        permiso.setVisible(true);
     //   estadotrab.setVisible(true);
       // estado.setVisible(true);
        cp.setVisible(true);
        cptrab.setVisible(true);
        numero.setVisible(true);
        apeMat.setVisible(true);
        apeMat.setVisible(true);
        calletrab.setVisible(true);
        calle.setVisible(true);
        numTrab.setVisible(true);
        ubicatrab.setVisible(true);
        ubicacion.setVisible(true);
       
        add.setVisible(true);
        close.setVisible(true);
        alta = true;
    }//GEN-LAST:event_addToolActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int ax = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas eliminar?");
        if(ax == JOptionPane.YES_OPTION){
            Transacciones nueva = new Transacciones();
            int row = this.tblDatos.getSelectedRow();
            String idtrab=tblDatos.getValueAt(row,0).toString();
            nueva.T13(idtrab);
            llenarTabla();
        }
        else if(ax == JOptionPane.NO_OPTION){
                
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       // TODO add your handling code here:
        Transacciones nuevo = new Transacciones();
        
        int x = 0;
        if(permiso.isSelected() == true) x=1;
        else if(permiso.isSelected() == false) x=0;
        
        String tid[]=id.getText().toString().split(":");
        nuevo.T14(tid[1], 
                nombre.getText(), 
                apePat.getText(), 
                apeMat.getText(), 
                calle.getText(), 
                numero.getText(),
                etcolonia.getText(),
                ciudad.getText(), 
                cp.getText(),
                telefono.getText(),
                etCorreo.getText(),
                sueldo.getText(), 
                getPermiso(),
                new String(psw.getPassword()),
                sppuesto.getSelectedItem().toString());
        
        llenarTabla();
    }//GEN-LAST:event_jButton3ActionPerformed

    public String getPermiso(){
        if(permiso.isSelected()){
            return "1";
        }else{
            return "0";
        }
    }
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        Transacciones nuevo = new Transacciones();
        
        int x = 0;
        if(permiso.isSelected() == true) x=1;
        else if(permiso.isSelected() == false) x=0;
        
        nuevo.transaccion4(new Transacciones().getNextIDTrabajador(ubicacion.getSelectedItem().toString()), 
                nombre.getText(), 
                apePat.getText(), 
                apeMat.getText(), 
                calle.getText(), 
                numero.getText(),
                etcolonia.getText(),
                ciudad.getText(), 
                cp.getText(),
                telefono.getText(),
                etCorreo.getText(),
                sueldo.getText(), 
                getPermiso(),
                new String(psw.getPassword()),
                sppuesto.getSelectedItem().toString());
        
        llenarTabla();
        idtrab.setVisible(false);
      //  trabaja.setVisible(false);
        nombretrab.setVisible(false);
        nombre.setVisible(false);
        apPat.setVisible(false);
        apePat.setVisible(false);
        apMat.setVisible(false);
        apeMat.setVisible(false);
        calletrab.setVisible(false);
        calle.setVisible(false);
        numTrab.setVisible(false);
        numero.setVisible(false);
        //estadotrab.setVisible(false);
        //estado.setVisible(false);
        cp.setVisible(false);
        cptrab.setVisible(false);
        nomciudad.setVisible(false);
        ciudad.setVisible(false);
        teltrab.setVisible(false);
        telefono.setVisible(false);
        sueldo.setVisible(false);
        sueldotrab.setVisible(false);
        permiso.setVisible(false);
        ubicatrab.setVisible(false);
        ubicacion.setVisible(false);
        add.setVisible(false);
        close.setVisible(false);
        addTool.setVisible(true);
        
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

    private void calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calleActionPerformed

    private void ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubicacionActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

Frame ventana = new Principal();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        busqueda();
    }//GEN-LAST:event_BuscarActionPerformed

    private void cpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpActionPerformed

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
            java.util.logging.Logger.getLogger(Trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trabajador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JTextField ETBuscar;
    private javax.swing.JButton add;
    private javax.swing.JButton addTool;
    private javax.swing.JLabel apMat;
    private javax.swing.JLabel apPat;
    private javax.swing.JTextField apeMat;
    private javax.swing.JTextField apePat;
    private javax.swing.JButton back;
    private javax.swing.JTextField calle;
    private javax.swing.JLabel calletrab;
    private javax.swing.JTextField ciudad;
    private javax.swing.JButton close;
    private javax.swing.JTextField cp;
    private javax.swing.JLabel cptrab;
    private javax.swing.JTextField etCorreo;
    private javax.swing.JTextField etcolonia;
    private javax.swing.JLabel id;
    private javax.swing.JLabel idtrab;
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
    private javax.swing.JLabel nombretrab;
    private javax.swing.JLabel nomciudad;
    private javax.swing.JLabel numTrab;
    private javax.swing.JTextField numero;
    private javax.swing.JCheckBox permiso;
    private javax.swing.JPasswordField psw;
    private javax.swing.JComboBox<String> sppuesto;
    private javax.swing.JTextField sueldo;
    private javax.swing.JLabel sueldotrab;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel teltrab;
    private javax.swing.JLabel tvcolonia;
    private javax.swing.JLabel tvemail;
    private javax.swing.JLabel tvpuesto;
    private javax.swing.JComboBox ubicacion;
    private javax.swing.JLabel ubicatrab;
    // End of variables declaration//GEN-END:variables
}
