
package biblioteca;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Préstamo extends javax.swing.JFrame {
    String cadena="";
    ArrayList<String> ides = new ArrayList<String>();
    int bandera = 0;
    
    public Préstamo() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        for (int i = 0; i < 7; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }        
        cancel.setVisible(false);
        recibido.setVisible(false);
        addPrestamo.setVisible(true);
        diaslabel.setVisible(false);
        dias.setVisible(false);
        cambio.setVisible(false);
        cancelarprestamo.setVisible(false);
        nombrelabel.setVisible(false);
        buscanombre.setVisible(false);
        tablauser.setVisible(false);
        combo1.setVisible(false);
        combo2.setVisible(false);
        combo3.setVisible(false);
        libro1.setVisible(false);
        libro2.setVisible(false);
        libro3.setVisible(false);
        add.setVisible(false);
        cancelar.setVisible(false);
        busqueda();
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
        String consulta = "select p.libro_subcategoria_categoria_idcategoria, p.libro_subcategoria_idsub, " +
                        "p.libro_ctitulo, p.libro_numejemplar, p.libro_volumen, p.libro_fechapubli, " +
                        "l.titulo, u.nombre, u.apPat, u.apMat, u.tipo, " +
                        "p.fechaprestamo, p.fechadevolucion, p.situacion, p.fechaentrego, p.costomulta " +
                        "from prestamo p, usuario u, libro l " +
                        "where p.Usuario_idUsuario = u.idUsuario " +
                        "and p.libro_subcategoria_categoria_idcategoria = l.subcategoria_categoria_idcategoria " +
                        "and p.libro_subcategoria_idsub = l.subcategoria_idsub " +
                        "and p.libro_ctitulo = l.ctitulo " +
                        "and p.libro_numejemplar = l.numejemplar " +
                        "and p.libro_volumen = l.volumen " +
                        "and p.libro_fechapubli = l.fechapubli union " +
                            
                        "select p2.libro_subcategoria_categoria_idcategoria, p2.libro_subcategoria_idsub, p2.libro_ctitulo, " +
                        "p2.libro_numejemplar, p2.libro_volumen, p2.libro_fechapubli, l2.titulo, u2.nombre, u2.apPat, u2.apMat, "+
                        "u2.tipo, p2.fechaprestamo, p2.fechadevolucion, p2.situacion, p2.fechaentrego, p2.costomulta "+
                        "from prestamo2 p2, libro2 l2, (select * from usuario union select * from usuario2 union select * from usuario3) as u2 "+
                        "where p2.Usuario_idUsuario = u2.idUsuario "+
                        "and p2.libro_subcategoria_categoria_idcategoria = l2.subcategoria_categoria_idcategoria "+
                        "and p2.libro_subcategoria_idsub = l2.subcategoria_idsub "+
                        "and p2.libro_ctitulo = l2.ctitulo "+
                        "and p2.libro_numejemplar = l2.numejemplar "+
                        "and p2.libro_volumen = l2.volumen "+
                        "and p2.libro_fechapubli = l2.fechapubli union " +
                
                        "select p3.libro_subcategoria_categoria_idcategoria, p3.libro_subcategoria_idsub, p3.libro_ctitulo, " +
                        "p3.libro_numejemplar, p3.libro_volumen, p3.libro_fechapubli, l3.titulo, u3.nombre, u3.apPat, u3.apMat, "+
                        "u3.tipo, p3.fechaprestamo, p3.fechadevolucion, p3.situacion, p3.fechaentrego, p3.costomulta "+
                        "from prestamo3 p3, libro3 l3, (select * from usuario union select * from usuario2 union select * from usuario3) as u3 "+
                        "where p3.Usuario_idUsuario = u3.idUsuario "+
                        "and p3.libro_subcategoria_categoria_idcategoria = l3.subcategoria_categoria_idcategoria "+
                        "and p3.libro_subcategoria_idsub = l3.subcategoria_idsub "+
                        "and p3.libro_ctitulo = l3.ctitulo "+
                        "and p3.libro_numejemplar = l3.numejemplar "+
                        "and p3.libro_volumen = l3.volumen "+
                        "and p3.libro_fechapubli = l3.fechapubli;";
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
            String [][] datos = new String [n][9];
                for (i = 0; i < n; i++) {
                    try{
                        System.out.println("ENTRO");
                        datos[i][0] = objConn.rs.getString(1)+" "+objConn.rs.getString(2)+" "+objConn.rs.getString(3)+" "+objConn.rs.getString(4)+" "+objConn.rs.getString(5)+" "+objConn.rs.getString(6);
                        datos[i][1] = objConn.rs.getString(7);
                        datos[i][2] = objConn.rs.getString(8)+" "+objConn.rs.getString(9)+" "+objConn.rs.getString(10);
                        datos[i][3] = objConn.rs.getString(11);
                        datos[i][4] = objConn.rs.getString(12);
                        datos[i][5] = objConn.rs.getString(13);
                        datos[i][6] = objConn.rs.getString(14);
                        datos[i][7] = objConn.rs.getString(15);
                        datos[i][8] = objConn.rs.getString(16);
                        System.out.println(datos[i][0]);
                    }catch(Exception e){
                    }
                    try{
                        objConn.rs.next();
                    }catch(Exception e){
                    }
                } 
                //cargamos la informacion a la tabla
                String []columnas = new String []{
                 "ID Préstamo", "Libro", "Usuario", "Tipo Usuario", "Realizado el:","Devolución","Situación","Regresó el:","Multa"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
        
        if (bandera==0){
            try {
                String consultas = "select subcategoria_categoria_idcategoria, subcategoria_idsub, " +
                                    "ctitulo, numejemplar, volumen, fechapubli, titulo from libro union " +
                                    "select subcategoria_categoria_idcategoria, subcategoria_idsub, " +
                                    "ctitulo, numejemplar, volumen, fechapubli, titulo from libro2 union " +
                                    "select subcategoria_categoria_idcategoria, subcategoria_idsub, " +
                                    "ctitulo, numejemplar, volumen, fechapubli, titulo from libro3; ";
                System.out.println(consultas);
                objConn.Consult(consultas);
                String id0="";
                //objConn.rs.last();
                if(objConn.rs.getRow() != 0){
                    do{
                        id0 = objConn.rs.getString(1)+" "+objConn.rs.getString(2)+" "+objConn.rs.getString(3)+" "+
                                     objConn.rs.getString(4)+" "+objConn.rs.getString(5)+" "+objConn.rs.getString(6);
                        ides.add(id0);
                        libro1.addItem(objConn.rs.getString(7));
                        libro2.addItem(objConn.rs.getString(7));
                        libro3.addItem(objConn.rs.getString(7));
                    }while(objConn.rs.next());
                    bandera = 1;
                }
            } catch (SQLException ex) {
                   Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //objConn.closeRsStmt();
    }
    
    private void asignaTexto(javax.swing.JTextField tF, int row, int col){
        String texto = "";
        try{
            texto= tblDatos.getValueAt(row, col).toString();
        }catch(Exception e){
        }
        tF.setText(texto);
    }
    
    
    public  String getNextIDPrestamo( ){ //T1 (Alta Libro)
        String consulta = "select MAX(a)+1 from (select SPLIT_STR(idprestamo,'-', 2) AS a from prestamo) AS x;";
       
        objConn.Consult(consulta);
        String resultado="";
        try {
            if(objConn.rs.getString(1) != null){
                System.out.println("ENTRO NO NULL");
                String id= objConn.rs.getString(1).toString();
                int idmax=Integer.parseInt(id);
                resultado="a-"+idmax;
            }
            else {
                System.out.println("ENTRO NULL");
                resultado = "a-1";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        //objConn.closeRsStmt();
        return resultado;
    }
    
    private void showUsers(ResultSet res){
        int n = 0;
        int i, j;
        if(res!= null){
            //obtener numero de registros
            try{
                res.last();
                n= res.getRow();
                res.first();
            }catch(Exception e){
            }
            //llenar la matriz de herramienta
            String [][] datos = new String [n][2];
                for (i = 0; i < n; i++) {
                    try{
                        datos[i][0] = res.getString(1);
                        datos[i][1] = res.getString(2)+" "+res.getString(3)+" "+res.getString(4);
                    }catch(Exception e){
                    }
                    try{
                        res.next();
                    }catch(Exception e){
                    }
                } 
                //cargamos la informacion a la tabla
                String []columnas = new String []{
                 "ID User","Nombre"
                };

                this.tablauser.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
        }
        //objConn.closeRsStmt();
    }
    
    private void showData(ResultSet res){
        int n = 0;
        int i, j;
        if(res!= null){
            //obtener numero de registros
            try{
                res.last();
                n= res.getRow();
                res.first();
            }catch(Exception e){
            }
            //llenar la matriz de herramienta
            String [][] datos = new String [n][9];
                for (i = 0; i < n; i++) {
                    try{
                        datos[i][0] = objConn.rs.getString(1)+" "+objConn.rs.getString(2)+" "+objConn.rs.getString(3)+" "+objConn.rs.getString(4)+" "+objConn.rs.getString(5)+" "+objConn.rs.getString(6);
                        datos[i][1] = objConn.rs.getString(7);
                        datos[i][2] = objConn.rs.getString(8)+" "+objConn.rs.getString(9)+" "+objConn.rs.getString(10);
                        datos[i][3] = objConn.rs.getString(11);
                        datos[i][4] = objConn.rs.getString(12);
                        datos[i][5] = objConn.rs.getString(13);
                        datos[i][6] = objConn.rs.getString(14);
                        datos[i][7] = objConn.rs.getString(15);
                        datos[i][8] = objConn.rs.getString(16);
                    }catch(Exception e){
                    }
                    try{
                        res.next();
                    }catch(Exception e){
                    }
                } 
                //cargamos la informacion a la tabla
                String []columnas = new String []{
                 "ID Préstamo", "Libro", "Usuario", "Tipo Usuario", "Realizado el:","Devolución","Situación","Regresó el:","Multa"
                };

                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
        }
        //objConn.closeRsStmt();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        numPag = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        lblMessage1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        libro1 = new javax.swing.JComboBox<>();
        combo1 = new javax.swing.JCheckBox();
        libro2 = new javax.swing.JComboBox<>();
        combo2 = new javax.swing.JCheckBox();
        libro3 = new javax.swing.JComboBox<>();
        combo3 = new javax.swing.JCheckBox();
        buscanombre = new javax.swing.JTextField();
        nombrelabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablauser = new javax.swing.JTable();
        addPrestamo = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        dias = new javax.swing.JSpinner();
        cambio = new javax.swing.JButton();
        cancelarprestamo = new javax.swing.JButton();
        diaslabel = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        buscaprincipal = new javax.swing.JLabel();
        recibido = new javax.swing.JButton();

        jLabel10.setText("CIUDAD");

        jLabel11.setText("CIUDAD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(890, 600));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Préstamo");

        cancel.setBackground(new java.awt.Color(204, 0, 0));
        cancel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 255, 255));
        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setText("Renovar Préstamo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        add.setBackground(new java.awt.Color(0, 153, 0));
        add.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Hecho");
        add.setActionCommand("");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        tblDatos.setBackground(Color.white);
        tblDatos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Prestamo", "Libro", "Nombre", "Tipo Usuario", "Fecha Préstamo", "Devolución", "Situación", "Regresó el", "Multa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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

        combo1.setText("Libro 1");

        combo2.setText("Libro 2");

        combo3.setText("Libro 3");
        combo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo3ActionPerformed(evt);
            }
        });

        buscanombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscanombreMouseClicked(evt);
            }
        });
        buscanombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscanombreKeyTyped(evt);
            }
        });

        nombrelabel.setText("Nombre:");

        tablauser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Usuario", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablauser);

        addPrestamo.setBackground(new java.awt.Color(51, 51, 255));
        addPrestamo.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        addPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        addPrestamo.setText("Realizar Préstamo");
        addPrestamo.setActionCommand("");
        addPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrestamoActionPerformed(evt);
            }
        });

        cancelar.setBackground(new java.awt.Color(204, 0, 0));
        cancelar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        dias.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 1));

        cambio.setBackground(new java.awt.Color(0, 153, 0));
        cambio.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        cambio.setForeground(new java.awt.Color(255, 255, 255));
        cambio.setText("Hecho");
        cambio.setActionCommand("");
        cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioActionPerformed(evt);
            }
        });

        cancelarprestamo.setBackground(new java.awt.Color(204, 0, 0));
        cancelarprestamo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cancelarprestamo.setForeground(new java.awt.Color(255, 255, 255));
        cancelarprestamo.setText("Cancelar");
        cancelarprestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarprestamoActionPerformed(evt);
            }
        });

        diaslabel.setText("Días:");

        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });

        buscaprincipal.setText("Buscar:");

        recibido.setBackground(new java.awt.Color(204, 0, 0));
        recibido.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        recibido.setForeground(new java.awt.Color(255, 255, 255));
        recibido.setText("Recibido");
        recibido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nombrelabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(buscanombre, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(combo3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(libro3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(combo2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(libro2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(combo1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(libro1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cancelar))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buscaprincipal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addPrestamo))
                        .addGap(771, 771, 771)
                        .addComponent(lblMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(recibido)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(diaslabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dias, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelarprestamo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cancel))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(547, 547, 547)
                                        .addComponent(numPag))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(lblMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaprincipal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numPag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(libro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(libro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(libro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo3))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cancelar))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buscanombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nombrelabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(cancel)
                    .addComponent(dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarprestamo)
                    .addComponent(diaslabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recibido)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        int ax = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas cancelar?");
        if(ax == JOptionPane.YES_OPTION){
            try{
                String ubicacion = "";
                int row = this.tblDatos.getSelectedRow();
                String idprest=tblDatos.getValueAt(row,0).toString();

                String ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro) as tablaconc " +
                                "where tablaconc.concid = '" + idprest + "'; ";
                objConn.Consult(ubicLibro);

                if(objConn.rs.getRow() == 0){
                    ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro2) as tablaconc " +
                                "where tablaconc.concid = '" + idprest + "'; ";
                    objConn.Consult(ubicLibro);

                    if(objConn.rs.getRow() == 0){
                        ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro3) as tablaconc " +
                                "where tablaconc.concid = '" + idprest + "'; ";
                        objConn.Consult(ubicLibro);

                        if(objConn.rs.getRow() == 0){
                            ubicacion = "Nada";
                        }
                        else if(objConn.rs.getRow() == 1){
                            ubicacion = "Sanjo";
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        ubicacion = "Rincon";
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    ubicacion = "Aguas";
                }
                Transacciones nueva = new Transacciones();
                nueva.transaccion15(idprest, 1, ubicacion); // Mandamos 1 para decir que fue cancelado
                busqueda();
            }catch(Exception e){}
        }
        else if(ax == JOptionPane.NO_OPTION){
                
        }
        cancel.setVisible(false);
        recibido.setVisible(false);
        //objConn.closeRsStmt();
    }//GEN-LAST:event_cancelActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        diaslabel.setVisible(false);
        dias.setVisible(true);
        cambio.setVisible(true);
        cancelarprestamo.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        Transacciones n = new Transacciones();
        int row = 0;
        String iduser = "";
        String consultaTipo = "";
        String ubicacion = "";
        //Insert();
        try{
            if(combo1.isSelected()==true){
                String ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro1.getSelectedIndex()) + "'; ";
                objConn.Consult(ubicLibro);
                
                if(objConn.rs.getRow() == 0){
                    ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro2) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro1.getSelectedIndex()) + "'; ";
                    objConn.Consult(ubicLibro);
                    
                    if(objConn.rs.getRow() == 0){
                        ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro3) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro1.getSelectedIndex()) + "'; ";
                        objConn.Consult(ubicLibro);
                        
                        if(objConn.rs.getRow() == 0){
                            ubicacion = "Nada";
                        }
                        else if(objConn.rs.getRow() == 1){
                            ubicacion = "Sanjo";
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        ubicacion = "Rincon";
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    ubicacion = "Aguas";
                }
                
                row = this.tablauser.getSelectedRow();
                iduser=tablauser.getValueAt(row,0).toString();
                consultaTipo = "select tipo from usuario where idusuario='"+iduser+"';";
                objConn.Consult(consultaTipo);
                if(objConn.rs.getRow() == 0){
                    consultaTipo = "select tipo from usuario2 where idusuario='"+iduser+"';";
                    objConn.Consult(consultaTipo);
                    if(objConn.rs.getRow() == 0){
                        consultaTipo = "select tipo from usuario3 where idusuario='"+iduser+"';";
                        objConn.Consult(consultaTipo);
                        if(objConn.rs.getRow() == 0){
                            JOptionPane.showMessageDialog(null, "No ha sido localizado.");
                        }
                        else if(objConn.rs.getRow() == 1){
                            n.transaccion2(ides.get(libro1.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        n.transaccion2(ides.get(libro1.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    n.transaccion2(ides.get(libro1.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                }
            }
            
            if(combo2.isSelected()==true){
                String ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro2.getSelectedIndex()) + "'; ";
                objConn.Consult(ubicLibro);
                
                if(objConn.rs.getRow() == 0){
                    ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro2) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro2.getSelectedIndex()) + "'; ";
                    objConn.Consult(ubicLibro);
                    
                    if(objConn.rs.getRow() == 0){
                        ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro3) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro2.getSelectedIndex()) + "'; ";
                        objConn.Consult(ubicLibro);
                        
                        if(objConn.rs.getRow() == 0){
                            ubicacion = "Nada";
                        }
                        else if(objConn.rs.getRow() == 1){
                            ubicacion = "Sanjo";
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        ubicacion = "Rincon";
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    ubicacion = "Aguas";
                }
                
                row = this.tablauser.getSelectedRow();
                iduser=tablauser.getValueAt(row,0).toString();
                consultaTipo = "select tipo from usuario where idusuario='"+iduser+"';";
                objConn.Consult(consultaTipo);
                if(objConn.rs.getRow() == 0){
                    consultaTipo = "select tipo from usuario2 where idusuario='"+iduser+"';";
                    objConn.Consult(consultaTipo);
                    if(objConn.rs.getRow() == 0){
                        consultaTipo = "select tipo from usuario3 where idusuario='"+iduser+"';";
                        objConn.Consult(consultaTipo);
                        if(objConn.rs.getRow() == 0){
                            JOptionPane.showMessageDialog(null, "No ha sido localizado.");
                        }
                        else if(objConn.rs.getRow() == 1){
                            n.transaccion2(ides.get(libro2.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        n.transaccion2(ides.get(libro2.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    n.transaccion2(ides.get(libro2.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                }
            }
            
            if(combo3.isSelected()==true){
                String ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro3.getSelectedIndex()) + "'; ";
                objConn.Consult(ubicLibro);
                
                if(objConn.rs.getRow() == 0){
                    ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro2) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro3.getSelectedIndex()) + "'; ";
                    objConn.Consult(ubicLibro);
                    
                    if(objConn.rs.getRow() == 0){
                        ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro3) as tablaconc " +
                                "where tablaconc.concid = '" + ides.get(libro3.getSelectedIndex()) + "'; ";
                        objConn.Consult(ubicLibro);
                        
                        if(objConn.rs.getRow() == 0){
                            ubicacion = "Nada";
                        }
                        else if(objConn.rs.getRow() == 1){
                            ubicacion = "Sanjo";
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        ubicacion = "Rincon";
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    ubicacion = "Aguas";
                }
                
                row = this.tablauser.getSelectedRow();
                iduser=tablauser.getValueAt(row,0).toString();
                consultaTipo = "select tipo from usuario where idusuario='"+iduser+"';";
                objConn.Consult(consultaTipo);
                if(objConn.rs.getRow() == 0){
                    consultaTipo = "select tipo from usuario2 where idusuario='"+iduser+"';";
                    objConn.Consult(consultaTipo);
                    if(objConn.rs.getRow() == 0){
                        consultaTipo = "select tipo from usuario3 where idusuario='"+iduser+"';";
                        objConn.Consult(consultaTipo);
                        if(objConn.rs.getRow() == 0){
                            JOptionPane.showMessageDialog(null, "No ha sido localizado.");
                        }
                        else if(objConn.rs.getRow() == 1){
                            n.transaccion2(ides.get(libro3.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        n.transaccion2(ides.get(libro3.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    n.transaccion2(ides.get(libro3.getSelectedIndex()), iduser, "a-1", objConn.rs.getString(1), ubicacion);
                }
            }
        } catch(Exception e){}
        alta = false;
        //objConn.closeRsStmt();
        busqueda();
    }//GEN-LAST:event_addActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        // TODO add your handling code here:
        cancel.setVisible(true);
        recibido.setVisible(true);
    }//GEN-LAST:event_tblDatosMouseClicked

    private void tblDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDatosKeyReleased
        // TODO add your handling code here:
        cancel.setVisible(true);
        recibido.setVisible(true);
    }//GEN-LAST:event_tblDatosKeyReleased

    private void tblDatosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseReleased
        // TODO add your handling code here:
        //actualizaDatos();
    }//GEN-LAST:event_tblDatosMouseReleased

    private void combo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo3ActionPerformed

    private void buscanombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscanombreKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        String consulta = "";
        if(caracter != KeyEvent.VK_BACKSPACE){
            cadena += caracter;
            System.out.println("char->"+cadena+"");
            consulta = "select * from usuario where nombre like '" + cadena + "%' union "+
                       "select * from usuario2 where nombre like '" + cadena + "%' union "+
                       "select * from usuario3 where nombre like '" + cadena + "%'; ";
            System.out.println(consulta);
            objConn.Consult(consulta);
            showUsers(objConn.rs);
        }
        else{
            try{
                cadena = cadena.substring(0, cadena.length()-1);
                consulta = "select * from usuario union select * from usuario2 union select * from usuario3;";
                objConn.Consult(consulta);
                System.out.println(consulta);
                showUsers(objConn.rs);
            }catch(StringIndexOutOfBoundsException e){
                cadena = "";
                consulta = "select * from usuario union select * from usuario2 union select * from usuario3;";
                objConn.Consult(consulta);
                System.out.println(consulta);
                showUsers(objConn.rs);
            }
            System.out.println("char->"+cadena+"");
        }
        //objConn.closeRsStmt();
    }//GEN-LAST:event_buscanombreKeyTyped

    private void buscanombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscanombreMouseClicked
        // TODO add your handling code here:
        String consulta = "select * from usuario union select * from usuario2 union select * from usuario3;";
        objConn.Consult(consulta);
        System.out.println(consulta);
        //objConn.closeRsStmt();
        showUsers(objConn.rs);
    }//GEN-LAST:event_buscanombreMouseClicked

    private void addPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrestamoActionPerformed
        // TODO add your handling code here:
        addPrestamo.setVisible(false);
        
        nombrelabel.setVisible(true);
        buscanombre.setVisible(true);
        tablauser.setVisible(true);
        combo1.setVisible(true);
        combo2.setVisible(true);
        combo3.setVisible(true);
        libro1.setVisible(true);
        libro2.setVisible(true);
        libro3.setVisible(true);
        add.setVisible(true);
        cancelar.setVisible(true);
    }//GEN-LAST:event_addPrestamoActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        addPrestamo.setVisible(true);
        
        nombrelabel.setVisible(false);
        buscanombre.setVisible(false);
        tablauser.setVisible(false);
        combo1.setVisible(false);
        combo2.setVisible(false);
        combo3.setVisible(false);
        libro1.setVisible(false);
        libro2.setVisible(false);
        libro3.setVisible(false);
        add.setVisible(false);
        cancelar.setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioActionPerformed
        // TODO add your handling code here:
        Transacciones n = new Transacciones();
        String diasS = (int) dias.getValue() + "";
        int row = this.tblDatos.getSelectedRow();
        String idprestamo=tblDatos.getValueAt(row,0).toString();
        String tipouser=tblDatos.getValueAt(row,3).toString();
        
        int dias = Integer.parseInt(diasS);
        
        try{
            String ubicacion="";
            String ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                            "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro) as tablaconc " +
                            "where tablaconc.concid = '" + ides.get(libro2.getSelectedIndex()) + "'; ";
            objConn.Consult(ubicLibro);

            if(objConn.rs.getRow() == 0){
                ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                            "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro2) as tablaconc " +
                            "where tablaconc.concid = '" + ides.get(libro2.getSelectedIndex()) + "'; ";
                objConn.Consult(ubicLibro);

                if(objConn.rs.getRow() == 0){
                    ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                            "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro3) as tablaconc " +
                            "where tablaconc.concid = '" + ides.get(libro2.getSelectedIndex()) + "'; ";
                    objConn.Consult(ubicLibro);

                    if(objConn.rs.getRow() == 0){
                        ubicacion = "Nada";
                    }
                    else if(objConn.rs.getRow() == 1){
                        ubicacion = "Sanjo";
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    ubicacion = "Rincon";
                }
            }
            else if(objConn.rs.getRow() == 1){
                ubicacion = "Aguas";
            }
            
            switch(tipouser){
                case "Estudiante":
                    if(dias > 3){
                        JOptionPane.showMessageDialog(null, "Un estudiante solo puede aumentar en un periodo de 3 días.");
                    }
                    else if(dias > 0 && dias <=3){
                        n.transaccion16(diasS, idprestamo, ubicacion);
                        buscar.setText("");
                        cadena = "";
                        busqueda();
                    }
                    break;

                case "Profesor":
                    if(dias > 4){
                        JOptionPane.showMessageDialog(null, "Un profesor solo puede aumentar en un periodo de 4 días.");
                    }
                    else if(dias > 0 && dias <=4){
                        n.transaccion16(diasS, idprestamo, ubicacion);
                        buscar.setText("");
                        cadena = "";
                        busqueda();
                    }
                    break;

                case "General":
                    if(dias > 3){
                        JOptionPane.showMessageDialog(null, "Al público general solo puede aumentar en un periodo de 3 días.");
                    }
                    else if(dias > 0 && dias <=3){
                        n.transaccion16(diasS, idprestamo, ubicacion);
                        buscar.setText("");
                        cadena = "";
                        busqueda();
                    }
                    break;

                case "Biblioteca":
                    if(dias > 30){
                        JOptionPane.showMessageDialog(null, "A una biblioteca solo puede aumentar en un periodo de 30 días.");
                    }
                    else if(dias > 0 && dias <=30){
                        n.transaccion16(diasS, idprestamo, ubicacion);
                        buscar.setText("");
                        cadena = "";
                        busqueda();
                    }
                    break;
            }
            //objConn.closeRsStmt();
        } catch(Exception e){}
    }//GEN-LAST:event_cambioActionPerformed

    private void cancelarprestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarprestamoActionPerformed
        // TODO add your handling code here:
        diaslabel.setVisible(false);
        dias.setVisible(false);
        cambio.setVisible(false);
        cancelarprestamo.setVisible(false);
    }//GEN-LAST:event_cancelarprestamoActionPerformed

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        String consulta = "";
        if(caracter != KeyEvent.VK_BACKSPACE){
            cadena += caracter;
            System.out.println("char->"+cadena+"");
            consulta = "select p.libro_subcategoria_categoria_idcategoria, p.libro_subcategoria_idsub, " +
                        "p.libro_ctitulo, p.libro_numejemplar, p.libro_volumen, p.libro_fechapubli, " +
                        "l.titulo, u.nombre, u.apPat, u.apMat, u.tipo, " +
                        "p.fechaprestamo, p.fechadevolucion, p.situacion, p.fechaentrego, p.costomulta " +
                        "from prestamo p, usuario u, libro l " +
                        "where p.Usuario_idUsuario = u.idUsuario " +
                        "and p.libro_subcategoria_categoria_idcategoria = l.subcategoria_categoria_idcategoria " +
                        "and p.libro_subcategoria_idsub = l.subcategoria_idsub " +
                        "and p.libro_ctitulo = l.ctitulo " +
                        "and p.libro_numejemplar = l.numejemplar " +
                        "and p.libro_volumen = l.volumen " +
                        "and p.libro_fechapubli = l.fechapubli " +
                        "and u.nombre like '"+cadena+"%' union " +
                            
                        "select p2.libro_subcategoria_categoria_idcategoria, p2.libro_subcategoria_idsub, " +
                        "p2.libro_ctitulo, p2.libro_numejemplar, p2.libro_volumen, p2.libro_fechapubli, " +
                        "l2.titulo, u2.nombre, u2.apPat, u2.apMat, u2.tipo, " +
                        "p2.fechaprestamo, p2.fechadevolucion, p2.situacion, p2.fechaentrego, p2.costomulta " +
                        "from prestamo p2, usuario u2, libro l2 " +
                        "where p2.Usuario_idUsuario = u2.idUsuario " +
                        "and p2.libro_subcategoria_categoria_idcategoria = l2.subcategoria_categoria_idcategoria " +
                        "and p2.libro_subcategoria_idsub = l2.subcategoria_idsub " +
                        "and p2.libro_ctitulo = l2.ctitulo " +
                        "and p2.libro_numejemplar = l2.numejemplar " +
                        "and p2.libro_volumen = l2.volumen " +
                        "and p2.libro_fechapubli = l2.fechapubli " +
                        "and u2.nombre like '"+cadena+"%' union " +

                        "select p3.libro_subcategoria_categoria_idcategoria, p3.libro_subcategoria_idsub, " +
                        "p3.libro_ctitulo, p3.libro_numejemplar, p3.libro_volumen, p3.libro_fechapubli, " +
                        "l3.titulo, u3.nombre, u3.apPat, u3.apMat, u3.tipo, " +
                        "p3.fechaprestamo, p3.fechadevolucion, p3.situacion, p3.fechaentrego, p3.costomulta " +
                        "from prestamo p3, usuario u3, libro l3 " +
                        "where p3.Usuario_idUsuario = u3.idUsuario " +
                        "and p3.libro_subcategoria_categoria_idcategoria = l3.subcategoria_categoria_idcategoria " +
                        "and p3.libro_subcategoria_idsub = l3.subcategoria_idsub " +
                        "and p3.libro_ctitulo = l3.ctitulo " +
                        "and p3.libro_numejemplar = l3.numejemplar " +
                        "and p3.libro_volumen = l3.volumen " +
                        "and p3.libro_fechapubli = l3.fechapubli " +
                        "and u3.nombre like '"+cadena+"%';";
            objConn.Consult(consulta);
            showData(objConn.rs);
        }
        else{
            try{
                cadena = cadena.substring(0, cadena.length()-1);
                consulta = "select p.libro_subcategoria_categoria_idcategoria, p.libro_subcategoria_idsub, " +
                        "p.libro_ctitulo, p.libro_numejemplar, p.libro_volumen, p.libro_fechapubli, " +
                        "l.titulo, u.nombre, u.apPat, u.apMat, u.tipo, " +
                        "p.fechaprestamo, p.fechadevolucion, p.situacion, p.fechaentrego, p.costomulta " +
                        "from prestamo p, usuario u, libro l " +
                        "where p.Usuario_idUsuario = u.idUsuario " +
                        "and p.libro_subcategoria_categoria_idcategoria = l.subcategoria_categoria_idcategoria " +
                        "and p.libro_subcategoria_idsub = l.subcategoria_idsub " +
                        "and p.libro_ctitulo = l.ctitulo " +
                        "and p.libro_numejemplar = l.numejemplar " +
                        "and p.libro_volumen = l.volumen " +
                        "and p.libro_fechapubli = l.fechapubli union " +
                            
                        "select p2.libro_subcategoria_categoria_idcategoria, p2.libro_subcategoria_idsub, " +
                        "p2.libro_ctitulo, p2.libro_numejemplar, p2.libro_volumen, p2.libro_fechapubli, " +
                        "l2.titulo, u2.nombre, u2.apPat, u2.apMat, u2.tipo, " +
                        "p2.fechaprestamo, p2.fechadevolucion, p2.situacion, p2.fechaentrego, p2.costomulta " +
                        "from prestamo p2, usuario u2, libro l2 " +
                        "where p2.Usuario_idUsuario = u2.idUsuario " +
                        "and p2.libro_subcategoria_categoria_idcategoria = l2.subcategoria_categoria_idcategoria " +
                        "and p2.libro_subcategoria_idsub = l2.subcategoria_idsub " +
                        "and p2.libro_ctitulo = l2.ctitulo " +
                        "and p2.libro_numejemplar = l2.numejemplar " +
                        "and p2.libro_volumen = l2.volumen " +
                        "and p2.libro_fechapubli = l2.fechapubli union " +

                        "select p3.libro_subcategoria_categoria_idcategoria, p3.libro_subcategoria_idsub, " +
                        "p3.libro_ctitulo, p3.libro_numejemplar, p3.libro_volumen, p3.libro_fechapubli, " +
                        "l3.titulo, u3.nombre, u3.apPat, u3.apMat, u3.tipo, " +
                        "p3.fechaprestamo, p3.fechadevolucion, p3.situacion, p3.fechaentrego, p3.costomulta " +
                        "from prestamo p3, usuario u3, libro l3 " +
                        "where p3.Usuario_idUsuario = u3.idUsuario " +
                        "and p3.libro_subcategoria_categoria_idcategoria = l3.subcategoria_categoria_idcategoria " +
                        "and p3.libro_subcategoria_idsub = l3.subcategoria_idsub " +
                        "and p3.libro_ctitulo = l3.ctitulo " +
                        "and p3.libro_numejemplar = l3.numejemplar " +
                        "and p3.libro_volumen = l3.volumen " +
                        "and p3.libro_fechapubli = l3.fechapubli;";
                objConn.Consult(consulta);
                showData(objConn.rs);
            }catch(StringIndexOutOfBoundsException e){
                cadena = "";
                consulta = "select p.libro_subcategoria_categoria_idcategoria, p.libro_subcategoria_idsub, " +
                        "p.libro_ctitulo, p.libro_numejemplar, p.libro_volumen, p.libro_fechapubli, " +
                        "l.titulo, u.nombre, u.apPat, u.apMat, u.tipo, " +
                        "p.fechaprestamo, p.fechadevolucion, p.situacion, p.fechaentrego, p.costomulta " +
                        "from prestamo p, usuario u, libro l " +
                        "where p.Usuario_idUsuario = u.idUsuario " +
                        "and p.libro_subcategoria_categoria_idcategoria = l.subcategoria_categoria_idcategoria " +
                        "and p.libro_subcategoria_idsub = l.subcategoria_idsub " +
                        "and p.libro_ctitulo = l.ctitulo " +
                        "and p.libro_numejemplar = l.numejemplar " +
                        "and p.libro_volumen = l.volumen " +
                        "and p.libro_fechapubli = l.fechapubli union " +
                            
                        "select p2.libro_subcategoria_categoria_idcategoria, p2.libro_subcategoria_idsub, " +
                        "p2.libro_ctitulo, p2.libro_numejemplar, p2.libro_volumen, p2.libro_fechapubli, " +
                        "l2.titulo, u2.nombre, u2.apPat, u2.apMat, u2.tipo, " +
                        "p2.fechaprestamo, p2.fechadevolucion, p2.situacion, p2.fechaentrego, p2.costomulta " +
                        "from prestamo p2, usuario u2, libro l2 " +
                        "where p2.Usuario_idUsuario = u2.idUsuario " +
                        "and p2.libro_subcategoria_categoria_idcategoria = l2.subcategoria_categoria_idcategoria " +
                        "and p2.libro_subcategoria_idsub = l2.subcategoria_idsub " +
                        "and p2.libro_ctitulo = l2.ctitulo " +
                        "and p2.libro_numejemplar = l2.numejemplar " +
                        "and p2.libro_volumen = l2.volumen " +
                        "and p2.libro_fechapubli = l2.fechapubli union " +

                        "select p3.libro_subcategoria_categoria_idcategoria, p3.libro_subcategoria_idsub, " +
                        "p3.libro_ctitulo, p3.libro_numejemplar, p3.libro_volumen, p3.libro_fechapubli, " +
                        "l3.titulo, u3.nombre, u3.apPat, u3.apMat, u3.tipo, " +
                        "p3.fechaprestamo, p3.fechadevolucion, p3.situacion, p3.fechaentrego, p3.costomulta " +
                        "from prestamo p3, usuario u3, libro l3 " +
                        "where p3.Usuario_idUsuario = u3.idUsuario " +
                        "and p3.libro_subcategoria_categoria_idcategoria = l3.subcategoria_categoria_idcategoria " +
                        "and p3.libro_subcategoria_idsub = l3.subcategoria_idsub " +
                        "and p3.libro_ctitulo = l3.ctitulo " +
                        "and p3.libro_numejemplar = l3.numejemplar " +
                        "and p3.libro_volumen = l3.volumen " +
                        "and p3.libro_fechapubli = l3.fechapubli;";
                
                objConn.Consult(consulta);
                showData(objConn.rs);
            }
            System.out.println("char->"+cadena+"");
        }
        //objConn.closeRsStmt();
    }//GEN-LAST:event_buscarKeyTyped

    private void recibidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibidoActionPerformed
        // TODO add your handling code here:
        int ax = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas regresar el libro?");
        if(ax == JOptionPane.YES_OPTION){
            try{
                String ubicacion = "";
                int row = this.tblDatos.getSelectedRow();
                String idprest=tblDatos.getValueAt(row,0).toString();

                String ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro) as tablaconc " +
                                "where tablaconc.concid = '" + idprest + "'; ";
                objConn.Consult(ubicLibro);

                if(objConn.rs.getRow() == 0){
                    ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro2) as tablaconc " +
                                "where tablaconc.concid = '" + idprest + "'; ";
                    objConn.Consult(ubicLibro);

                    if(objConn.rs.getRow() == 0){
                        ubicLibro = "select * from (select concat_ws(' ',subcategoria_categoria_idcategoria,"+
                                "subcategoria_idsub,ctitulo,numejemplar,volumen,fechapubli) as concid from libro3) as tablaconc " +
                                "where tablaconc.concid = '" + idprest + "'; ";
                        objConn.Consult(ubicLibro);

                        if(objConn.rs.getRow() == 0){
                            ubicacion = "Nada";
                        }
                        else if(objConn.rs.getRow() == 1){
                            ubicacion = "Sanjo";
                        }
                    }
                    else if(objConn.rs.getRow() == 1){
                        ubicacion = "Rincon";
                    }
                }
                else if(objConn.rs.getRow() == 1){
                    ubicacion = "Aguas";
                }
                Transacciones nueva = new Transacciones();
                nueva.transaccion15(idprest, 2, ubicacion); // Mandamos 2 para decir que fue devuelto
                busqueda();
            }catch(Exception e){}
        }
        else if(ax == JOptionPane.NO_OPTION){
                
        }
        //objConn.closeRsStmt();
        cancel.setVisible(false);
        recibido.setVisible(false);
    }//GEN-LAST:event_recibidoActionPerformed

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
            java.util.logging.Logger.getLogger(Préstamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Préstamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Préstamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Préstamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Préstamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton addPrestamo;
    private javax.swing.JTextField buscanombre;
    private javax.swing.JLabel buscaprincipal;
    private javax.swing.JTextField buscar;
    private javax.swing.JButton cambio;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton cancelarprestamo;
    private javax.swing.JCheckBox combo1;
    private javax.swing.JCheckBox combo2;
    private javax.swing.JCheckBox combo3;
    private javax.swing.JSpinner dias;
    private javax.swing.JLabel diaslabel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JComboBox<String> libro1;
    private javax.swing.JComboBox<String> libro2;
    private javax.swing.JComboBox<String> libro3;
    private javax.swing.JLabel nombrelabel;
    private javax.swing.JLabel numPag;
    private javax.swing.JButton recibido;
    private javax.swing.JTable tablauser;
    private javax.swing.JTable tblDatos;
    // End of variables declaration//GEN-END:variables
}
