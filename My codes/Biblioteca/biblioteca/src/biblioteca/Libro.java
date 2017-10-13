package biblioteca;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Libro extends javax.swing.JFrame {
    
    Transacciones transacciones;
    String cadena;
    boolean mostrar_opciones=false;
    public MysqlConn objConn = new MysqlConn();
    public Libro() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        for (int i = 0; i < 12; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }        
        transacciones=new Transacciones();
        cantidad.setVisible(false);
        NumEjemplar.setVisible(false);
        titulolib.setVisible(false);
        titulo.setVisible(false);
        editolib.setVisible(false);
        editorial.setVisible(false);
        numPag.setVisible(false);
        numPags.setVisible(false);
        idiolib.setVisible(false);
        idioma.setVisible(false);
        paislib.setVisible(false);
        pais.setVisible(false);
        fechaPubli.setVisible(false);
        Lsubcategoria.setVisible(false);
        cbSubcategoria.setVisible(false);
        sinoplib.setVisible(false);
        sinopsis.setVisible(false);
        add.setVisible(true);
        close.setVisible(false);
        numVolumen.setVisible(false);
        volumen.setVisible(false);
        agreAutor.setVisible(false);
        cbAutor.setVisible(false);
        CBcategoria.setVisible(false);
        Lcategoria.setVisible(false);
        Fechapicker.setVisible(false);
        CBcategoria.setVisible(false);
        add.setVisible(false);
        BTModifica.setVisible(false);
        BtElimina.setVisible(false);
        txtopciones.setVisible(false);
        String ids[]=new String[10];
            ids[0]="000";ids[1]="100";ids[2]="200";ids[3]="300";ids[4]="400";ids[5]="500";ids[6]="600";ids[7]="700";ids[8]="800";ids[9]="900";
            objConn.Consult("select nombre from subcategoria3 where Categoria_idCategoria='"+ids[CBcategoria.getSelectedIndex()]+"'");
            System.out.println("cb selected index: "+ ids[CBcategoria.getSelectedIndex()]);
           // cbSubcategoria=null;
            if (objConn.rs!=null){
                try {//cb subcategoria
                    do{
                        cbSubcategoria.addItem(objConn.rs.getString(1));
                    }while(objConn.rs.next());
                } catch (SQLException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    
    
    private boolean alta = false;

    private void busqueda(){
        String consulta = "select * from libro where titulo like '"+busqueda.getText().toString()+"%' "
                + "union select * from libro2 where titulo like '"+busqueda.getText().toString()+"%' "
                + "union select * from libro3 where titulo like '"+busqueda.getText().toString()+"%' ";
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
            String [][] datos = new String [n][12];
                for (i = 0; i < n; i++) {
                    for (j = 0; j < 12; j++) {
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
                 "Categoría","Subcategoría","Clave","Cantidad","Volumen","Año Publi","Idioma","Título","Editorial","Sinópsis","País","NúmPágina"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    }
    private void limpiaTxt(){
//      idLibro.setText("");
        titulo.setText("");
        editorial.setText("");
        numPags.setText("");
    }
    
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(date);
    
    
    
    
    private void actualizaDatos(){
        if (mostrar_opciones){
        addTool.setVisible(false);
        habilitaEdicion(true);
        limpiaTxt();
        titulolib.setVisible(true);
        cbAutor.setVisible(true);
        agreAutor.setVisible(true);
        titulo.setVisible(true);
        editolib.setVisible(true);
        editorial.setVisible(true);
        volumen.setVisible(false);
        numVolumen.setVisible(false);
        numPag.setVisible(true);
        numPags.setVisible(true);
        idiolib.setVisible(true);
        idioma.setVisible(true);
        paislib.setVisible(true);
        pais.setVisible(true);
        Fechapicker.setVisible(false);
        fechaPubli.setVisible(false);
        Lsubcategoria.setVisible(false);
        cbSubcategoria.setVisible(false);
        buscar.setVisible(false);
        sinoplib.setVisible(true);
        sinopsis.setVisible(true);
        add.setVisible(true);
        close.setVisible(true);
        CBcategoria.setVisible(false);
        Lcategoria.setVisible(false);
        cantidad.setVisible(false);
        NumEjemplar.setVisible(false);
        
        }
        int row = this.tblDatos.getSelectedRow();
      
        asignaTexto(titulo, row, 7);
        asignaTexto(editorial, row, 8);
        asignaTexto(sinoplib, row, 9);
        asignaTexto(idioma, row, 6);
        asignaTexto(pais, row, 10);
        asignaTexto(numPags, row, 11);
        //valores para los id
       
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
        titulo.setEditable(habilita);
        editorial.setEditable(habilita);
        sinoplib.setEditable(habilita);
        idioma.setEditable(habilita);
        pais.setEditable(habilita);
        numPags.setEditable(habilita);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        titulolib = new javax.swing.JLabel();
        editolib = new javax.swing.JLabel();
        numPag = new javax.swing.JLabel();
        txtopciones = new javax.swing.JLabel();
        addTool = new javax.swing.JButton();
        BtElimina = new javax.swing.JButton();
        BTModifica = new javax.swing.JButton();
        add = new javax.swing.JButton();
        close = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        titulo = new javax.swing.JTextField();
        editorial = new javax.swing.JTextField();
        numPags = new javax.swing.JTextField();
        lblMessage1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idioma = new javax.swing.JTextField();
        idiolib = new javax.swing.JLabel();
        paislib = new javax.swing.JLabel();
        pais = new javax.swing.JTextField();
        fechaPubli = new javax.swing.JLabel();
        Lsubcategoria = new javax.swing.JLabel();
        cbSubcategoria = new javax.swing.JComboBox();
        sinopsis = new javax.swing.JLabel();
        busqueda = new javax.swing.JTextField();
        Lcategoria = new javax.swing.JLabel();
        CBcategoria = new javax.swing.JComboBox<String>();
        sinoplib = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        numVolumen = new javax.swing.JLabel();
        volumen = new javax.swing.JTextField();
        Fechapicker = new javax.swing.JTextField();
        agreAutor = new javax.swing.JLabel();
        cbAutor = new javax.swing.JComboBox();
        NumEjemplar = new javax.swing.JLabel();
        cantidad = new javax.swing.JSpinner();

        jLabel10.setText("CIUDAD");

        jLabel11.setText("CIUDAD");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Libros");

        titulolib.setText("Título");

        editolib.setText("Editorial");

        numPag.setText("núm. páginas");

        txtopciones.setText("Opciones");

        addTool.setBackground(new java.awt.Color(51, 51, 255));
        addTool.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        addTool.setForeground(new java.awt.Color(255, 255, 255));
        addTool.setText("Opciones para Libros");
        addTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToolActionPerformed(evt);
            }
        });

        BtElimina.setBackground(new java.awt.Color(204, 0, 0));
        BtElimina.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtElimina.setForeground(new java.awt.Color(255, 255, 255));
        BtElimina.setText("Eliminar");
        BtElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtEliminaActionPerformed(evt);
            }
        });

        BTModifica.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BTModifica.setText("Modificar");
        BTModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTModificaActionPerformed(evt);
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
                "Categoría", "Subcategoría", "Clave Libro", "Cantidad", "Volumen", "Año Publicación", "Idioma", "Título", "Editorial", "Sinópsis", "País", "NúmPágina"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true
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

        numPags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numPagsActionPerformed(evt);
            }
        });

        idiolib.setText("Idioma");

        paislib.setText("País");

        pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paisActionPerformed(evt);
            }
        });

        fechaPubli.setText("Año de Publicación");

        Lsubcategoria.setText("Subcategoría");

        cbSubcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubcategoriaActionPerformed(evt);
            }
        });

        sinopsis.setText("Sinopsis");

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busquedaKeyTyped(evt);
            }
        });

        Lcategoria.setText("Categoria");

        CBcategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Generales", "Filosofía", "Religión", "Ciencias sociales", "Lenguas", "Ciencias naturales y matemáticas", "Tecnología", "Arte decoración y recreación", "Literatura", "Historia geografía y Biografía" }));
        CBcategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBcategoriaItemStateChanged(evt);
            }
        });
        CBcategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBcategoriaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CBcategoriaMousePressed(evt);
            }
        });
        CBcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBcategoriaActionPerformed(evt);
            }
        });
        CBcategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBcategoriaKeyPressed(evt);
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

        numVolumen.setText("Volumen");

        agreAutor.setText("Agregar autor");

        cbAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAutorActionPerformed(evt);
            }
        });

        NumEjemplar.setText("Cantidad");

        cantidad.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sinoplib, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtopciones, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BTModifica)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtElimina)
                                        .addGap(18, 18, 18)
                                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(429, 429, 429))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(close)
                                        .addGap(97, 97, 97))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(NumEjemplar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(idiolib)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(idioma, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(titulolib)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(editolib)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(editorial, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(numPag))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(paislib)
                                                .addGap(25, 25, 25)
                                                .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(fechaPubli)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(numPags)
                                            .addComponent(Fechapicker, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(numVolumen)
                                            .addComponent(Lcategoria))))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(88, 88, 88))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(volumen)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(CBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Lsubcategoria)
                                            .addComponent(agreAutor))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbAutor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbSubcategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sinopsis)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(73, 73, 73))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addTool, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(busqueda))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titulolib)
                    .addComponent(editolib)
                    .addComponent(editorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numPag)
                    .addComponent(numPags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numVolumen)
                    .addComponent(volumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agreAutor)
                    .addComponent(cbAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idiolib)
                    .addComponent(paislib)
                    .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaPubli)
                    .addComponent(Lsubcategoria)
                    .addComponent(cbSubcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lcategoria)
                    .addComponent(CBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fechapicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMessage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinopsis, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(close))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(NumEjemplar)
                                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sinoplib, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtopciones, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTModifica)
                    .addComponent(BtElimina)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back))
                .addGap(127, 127, 127))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
          busqueda();
    }//GEN-LAST:event_buscarActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        sinopsis.setVisible(false);
        sinoplib.setVisible(false);
        cantidad.setVisible(false);
        NumEjemplar.setVisible(false);
        titulolib.setVisible(false);
        titulo.setVisible(false);
        editolib.setVisible(false);
        editorial.setVisible(false);
        numPag.setVisible(false);
        numPags.setVisible(false);
        idiolib.setVisible(false);
        idioma.setVisible(false);
        paislib.setVisible(false);
        pais.setVisible(false);
        fechaPubli.setVisible(false);
        Lsubcategoria.setVisible(false);
        cbSubcategoria.setVisible(false);
        numVolumen.setVisible(false);
        volumen.setVisible(false);
        cbSubcategoria.setVisible(false);
        Lsubcategoria.setVisible(false);
        buscar.setVisible(true);
        add.setVisible(false);
        close.setVisible(false);
        addTool.setVisible(true);
        mostrar_opciones=false;
        agreAutor.setVisible(false);
        cbAutor.setVisible(false);
        CBcategoria.setVisible(false);
        Lcategoria.setVisible(false);
        Fechapicker.setVisible(false);
        CBcategoria.setVisible(false);
        add.setVisible(false);
        BTModifica.setVisible(false);
        BtElimina.setVisible(false);
        txtopciones.setVisible(false);
        alta=false;
    }//GEN-LAST:event_closeActionPerformed

    
    private void addToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToolActionPerformed
        addTool.setVisible(false);
        habilitaEdicion(true);
        limpiaTxt();
        titulolib.setVisible(true);
        titulo.setVisible(true);
        cantidad.setVisible(true);
        NumEjemplar.setVisible(true);
        editolib.setVisible(true);
        editorial.setVisible(true);
        numPag.setVisible(true);
        numPags.setVisible(true);
        idiolib.setVisible(true);
        idioma.setVisible(true);
        paislib.setVisible(true);
        pais.setVisible(true);
        fechaPubli.setVisible(true);
        Lsubcategoria.setVisible(true);
        cbSubcategoria.setVisible(true);
        buscar.setVisible(true);
        sinoplib.setVisible(true);
        sinopsis.setVisible(true);
        add.setVisible(true);
        close.setVisible(true);
        CBcategoria.setVisible(true);
        Lcategoria.setVisible(true);
        numVolumen.setVisible(true);
        volumen.setVisible(true);
        cbSubcategoria.setVisible(true);
        Lsubcategoria.setVisible(true);
        agreAutor.setVisible(true);
        cbAutor.setVisible(true);
        alta = true;
        mostrar_opciones=true;
        CBcategoria.setVisible(true);
        Lcategoria.setVisible(true);
        Fechapicker.setVisible(true);
        CBcategoria.setVisible(true);
        add.setVisible(true);
        BTModifica.setVisible(true);
        BtElimina.setVisible(true);
        txtopciones.setVisible(true);
     
        
        MysqlConn Conn = new MysqlConn();
        Conn.Consult("select nombre from autor");
        if (Conn.rs!=null){
            try {//llenado una vez
                do{
                    cbAutor.addItem(Conn.rs.getString(1).toString());
                }while(Conn.rs.next());
            } catch (SQLException ex) {
                Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addToolActionPerformed

    private void BtEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEliminaActionPerformed
        int row = this.tblDatos.getSelectedRow();
        String s_c_id=tblDatos.getValueAt(row,0).toString();
        String s_idSub=tblDatos.getValueAt(row,1).toString();
        String cTitulo=tblDatos.getValueAt(row,2).toString();
        String ejemplar=tblDatos.getValueAt(row,3).toString();
        String vol=tblDatos.getValueAt(row,4).toString();
        String anio=tblDatos.getValueAt(row,5).toString();
       
        int ejem=Integer.parseInt(ejemplar);
        int volum=Integer.parseInt(vol);
        int ani=Integer.parseInt(anio);
       
        transacciones.T10(s_c_id,s_idSub,cTitulo,ejem,volum,ani);
        busqueda();
        for (int i = 0; i < 12; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }     
        this.tblDatos.setRowSelectionInterval(0,0);
       // lblMessage.setText("");
        actualizaDatos();
    
    }//GEN-LAST:event_BtEliminaActionPerformed

    private void BTModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTModificaActionPerformed
        String idAutor="";
        if (mostrar_opciones){
              /*addTool.setVisible(false);
                habilitaEdicion(true);
                volumen.setVisible(false);
                numVolumen.setVisible(false);
                titulolib.setVisible(true);
                titulo.setVisible(true);
                editolib.setVisible(true);
                
                NumEjemplar.setVisible(false);
                editorial.setVisible(true);
                numPag.setVisible(true);
                numPags.setVisible(true);
                idiolib.setVisible(true);
                idioma.setVisible(true);
                CBcategoria.setVisible(false);
                Lcategoria.setVisible(false);
                cbSubcategoria.setVisible(false);
                paislib.setVisible(true);
                pais.setVisible(true);
                fechaPubli.setVisible(false);
                Fechapicker.setVisible(false);
                NumEjemplar.setVisible(false);
                cantidad.setVisible(false);
                agreAutor.setVisible(true);
                cbAutor.setVisible(true);
                sinoplib.setVisible(true);
                sinopsis.setVisible(true);
                add.setVisible(true);
                close.setVisible(true);
                alta = false;*/
        }
        int row = this.tblDatos.getSelectedRow();
        String s_c_id=tblDatos.getValueAt(row,0).toString();
        String s_idSub=tblDatos.getValueAt(row,1).toString();
        String cTitulo=tblDatos.getValueAt(row,2).toString();
        String ejemplar=tblDatos.getValueAt(row,3).toString();
        String vol=tblDatos.getValueAt(row,4).toString();
        String anio=tblDatos.getValueAt(row,5).toString();
        
        /*asignaTexto(idioma, row, 6);
        asignaTexto(titulo, row, 7);
        asignaTexto(editorial, row, 8);
        asignaTexto(sinoplib, row, 9);
        asignaTexto(pais, row, 10);
        asignaTexto(numPags, row, 11);*/
        
        objConn.Consult("select CAutor from autor where nombre='"+cbAutor.getSelectedItem()+"'");
            if (objConn.rs!=null){
                try {
                    do{
                       idAutor= objConn.rs.getString(1);
                    }while(objConn.rs.next());
                } catch (SQLException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            int volum=Integer.parseInt(vol);
            int fecha=Integer.parseInt(anio);
            int pags=Integer.parseInt(numPags.getText());
            int ejem=Integer.parseInt(ejemplar);
        transacciones.transaccion20(s_c_id,s_idSub,cTitulo,ejem,volum,fecha,titulo.getText(),editorial.getText(),sinoplib.getText(), idioma.getText(),pais.getText(),pags, idAutor);       
       // objConn.closeRsStmt();
        busqueda();
        for (int i = 0; i < 12; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }     
        this.tblDatos.setRowSelectionInterval(0,0);
        
        actualizaDatos();
        
        
    }//GEN-LAST:event_BTModificaActionPerformed

    public String obtenerId(char dos, char tres){
        String id1="",id2="", idfin;
        if(dos=='a'||dos=='A'||dos=='B'||dos=='b'||dos=='c'||dos=='C'){
            id1=id1+2;
        }else if(dos=='d'||dos=='D'||dos=='e'||dos=='E'||dos=='f'||dos=='F'){
            id1=id1+3;
        }else if(dos=='g'||dos=='G'||dos=='h'||dos=='H'||dos=='i'||dos=='I'){
            id1=id1+4;
        }else if(dos=='j'||dos=='J'||dos=='k'||dos=='K'||dos=='l'||dos=='L'){
            id1=id1+5;
        }else if(dos=='m'||dos=='M'||dos=='n'||dos=='N'||dos=='o'||dos=='O'){
            id1=id1+6;
        }else if(dos=='t'||dos=='T'||dos=='u'||dos=='U'||dos=='v'||dos=='V'){
            id1=id1+8;
        }else if(dos=='p'||dos=='P'||dos=='q'||dos=='Q'||dos=='r'||dos=='R'||dos=='s'||dos=='S'){
            id1=id1+7;
        }else if(dos=='w'||dos=='W'||dos=='x'||dos=='X'||dos=='y'||dos=='Y'||dos=='z'||dos=='Z'){
            id1=id1+9;
        }
        if(tres=='a'||tres=='A'||tres=='B'||tres=='b'||tres=='c'||tres=='C'){
            id2=id2+2;
        }else if(tres=='d'||tres=='D'||tres=='e'||tres=='E'||tres=='f'||tres=='F'){
            id2=id2+3;
        }else if(tres=='g'||tres=='G'||tres=='h'||tres=='H'||tres=='i'||tres=='I'){
            id2=id2+4;
        }else if(tres=='j'||tres=='J'||tres=='k'||tres=='K'||tres=='l'||tres=='L'){
            id2=id2+5;
        }else if(tres=='m'||tres=='M'||tres=='n'||tres=='N'||tres=='o'||tres=='O'){
            id2=id2+6;
        }else if(tres=='t'||tres=='T'||tres=='u'||tres=='U'||tres=='v'||tres=='V'){
            id2=id2+8;
        }else if(tres=='p'||tres=='P'||tres=='q'||tres=='Q'||tres=='r'||tres=='R'||tres=='s'||tres=='S'){
            id2=id2+7;
        }else if(tres=='w'||tres=='W'||tres=='x'||tres=='X'||tres=='y'||tres=='Y'||tres=='z'||tres=='Z'){
            id2=id2+9;
        }
        
        idfin=id1+id2;
        return idfin;
    } 
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        MysqlConn Conn = new MysqlConn();
        if(alta){
            String Clave="";
            String idCategoria="", idSub="",idAutor="";
            
//categoría id_Categoria            
            Conn.Consult("select idcategoria from categoria3 where nombre='"+CBcategoria.getSelectedItem().toString()+"'");
            if (Conn.rs!=null){
                try {
                    do{
                       idCategoria= Conn.rs.getString(1).toString();
                    }while(Conn.rs.next());
                } catch (SQLException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//id_Subcategoria            
            Conn.Consult("select idSub from Subcategoria3 where nombre='"+cbSubcategoria.getSelectedItem().toString()+"'");
            if (Conn.rs!=null){
                try {
                    do{
                       idSub= Conn.rs.getString(1);
                    }while(Conn.rs.next());
                } catch (SQLException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
 //idAutor           
            Conn.Consult("select CAutor from autor where nombre='"+cbAutor.getSelectedItem()+"'");
            if (Conn.rs!=null){
                try {
                    do{
                       idAutor= Conn.rs.getString(1);
                    }while(Conn.rs.next());
                } catch (SQLException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            char letraDos,letraTres;
            letraDos=titulo.getText().charAt(1);
            letraTres=titulo.getText().charAt(2);
            String idLibro=obtenerId(letraDos, letraTres);
            Clave=titulo.getText().charAt(0)+idLibro;
            int volum=Integer.parseInt(volumen.getText());
            int fecha=Integer.parseInt(Fechapicker.getText());
            int pags=Integer.parseInt(numPags.getText());
            //int ejem=Integer.parseInt(ejemplar.getText());
            int ejem= (int) cantidad.getValue();
           transacciones.transaccion1(idCategoria, idSub, idAutor, Clave,ejem,volum,fecha, idioma.getText(),titulo.getText(),editorial.getText(),sinoplib.getText(),pais.getText(),pags);
            alta = false;
            habilitaEdicion(false);
        }
        limpiaTxt();
        busqueda();
        for (int i = 0; i < 12; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }     
        this.tblDatos.setRowSelectionInterval(0,0);
        
        actualizaDatos();
       //Conn.closeRsStmt();
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

    private void paisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paisActionPerformed

    private void cbSubcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubcategoriaActionPerformed

    }//GEN-LAST:event_cbSubcategoriaActionPerformed

    private void numPagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numPagsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numPagsActionPerformed

    
    
    private void CBcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBcategoriaActionPerformed
        String ids[]=new String[10];
            ids[0]="000";ids[1]="100";ids[2]="200";ids[3]="300";ids[4]="400";ids[5]="500";ids[6]="600";ids[7]="700";ids[8]="800";ids[9]="900";
            objConn.Consult("select nombre from subcategoria3 where Categoria_idCategoria='"+ids[CBcategoria.getSelectedIndex()]+"'");
            System.out.println("cb selected index: "+ ids[CBcategoria.getSelectedIndex()]);
           cbSubcategoria.removeAllItems();
           if (objConn.rs!=null){
                try {//cb subcategoria
                    do{
                        cbSubcategoria.addItem(objConn.rs.getString(1));
                    }while(objConn.rs.next());
                }catch(SQLException ex){
                 
                }
            }
    }//GEN-LAST:event_CBcategoriaActionPerformed

    private void busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyTyped
                                   
     
    
    }//GEN-LAST:event_busquedaKeyTyped

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Frame ventana = new Principal();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void cbAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAutorActionPerformed
      /*  DefaultComboBoxModel model = (DefaultComboBoxModel) cbAutor.getModel();
        String consulta = "select nombre from autor ";
        System.out.println(consulta);
        objConn.Consult(consulta);
        
        if(objConn.rs!= null){
            //obtener numero de registros
            try{
                objConn.rs.last();
                objConn.rs.getRow();
                objConn.rs.first();
            }catch(Exception e){
            }
            try {
                do{
                    model.addElement(objConn.rs.getString(1));
                }while(objConn.rs.next());
            } catch (SQLException ex) {
                Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
            }
               
          }lo */
    }//GEN-LAST:event_cbAutorActionPerformed

    private void CBcategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBcategoriaItemStateChanged
        
       
    }//GEN-LAST:event_CBcategoriaItemStateChanged

    private void CBcategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBcategoriaMouseClicked
         
    }//GEN-LAST:event_CBcategoriaMouseClicked

    private void CBcategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBcategoriaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBcategoriaKeyPressed

    private void CBcategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBcategoriaMousePressed
         
    }//GEN-LAST:event_CBcategoriaMousePressed

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
            java.util.logging.Logger.getLogger(Libro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Libro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Libro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Libro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Libro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTModifica;
    private javax.swing.JButton BtElimina;
    private javax.swing.JComboBox<String> CBcategoria;
    private javax.swing.JTextField Fechapicker;
    private javax.swing.JLabel Lcategoria;
    private javax.swing.JLabel Lsubcategoria;
    private javax.swing.JLabel NumEjemplar;
    private javax.swing.JButton add;
    private javax.swing.JButton addTool;
    private javax.swing.JLabel agreAutor;
    private javax.swing.JButton back;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField busqueda;
    private javax.swing.JSpinner cantidad;
    private javax.swing.JComboBox cbAutor;
    private javax.swing.JComboBox cbSubcategoria;
    private javax.swing.JButton close;
    private javax.swing.JLabel editolib;
    private javax.swing.JTextField editorial;
    private javax.swing.JLabel fechaPubli;
    private javax.swing.JLabel idiolib;
    private javax.swing.JTextField idioma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel numPag;
    private javax.swing.JTextField numPags;
    private javax.swing.JLabel numVolumen;
    private javax.swing.JTextField pais;
    private javax.swing.JLabel paislib;
    private javax.swing.JTextField sinoplib;
    private javax.swing.JLabel sinopsis;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField titulo;
    private javax.swing.JLabel titulolib;
    private javax.swing.JLabel txtopciones;
    private javax.swing.JTextField volumen;
    // End of variables declaration//GEN-END:variables
}
