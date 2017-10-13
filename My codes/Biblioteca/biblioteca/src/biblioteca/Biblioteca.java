
package biblioteca;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class Biblioteca extends javax.swing.JFrame {
    private String idTr;
    private String TRName;
    public String cadena = "";
    public String abuscar = "";
    MysqlConn objConn = new MysqlConn();
    
    public Biblioteca() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
       
        for (int i = 0; i < 9; i++) {
            tblDatos.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.black,Color.white));
        }  
        buscarAvanzado.setVisible(true);
        menu.setVisible(false);
        cancelar.setVisible(false);
        titulo.setVisible(false);
        porTitulo.setVisible(false);
        editorial.setVisible(false);
        porEditorial.setVisible(false);
        idioma.setVisible(false);
        porIdioma.setVisible(false);
        pais.setVisible(false);
        porPais.setVisible(false);
        ubicacion.setVisible(false);
        porUbicacion.setVisible(false);
        fecha.setVisible(false);
        porFecha.setVisible(false);
        autor.setVisible(false);
        porAutor.setVisible(false);
        categoria.setVisible(false);
        porCategoria.setVisible(false);
        buscarAv.setVisible(false);
        
        llenarTabla();
    }
    
    private void llenarTabla(){

        
        
          String consulta="select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Aguascalientes' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro l, autor a, Subcategoria s, libro_has_autor la " +
                "where l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor" +
                    " union " +
                    "select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Rincón de Romos' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro2 l, autor a, Subcategoria2 s, libro_has_autor la " +
                "where l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor"+
                    "  union " +
                   "select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'San Jóse de Grácia' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro3 l, autor a, Subcategoria3 s, libro_has_autor la " +
                "where l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor";
					
					
					
                
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
                    for (j = 0; j < 9; j++) {
                        try{
                            /*MysqlConn obj2 = new MysqlConn();
                            String consulta2 = "select a.Nombre, a.apPaterno, a.apMaterno " +
                                                "from libro l, autor a, libro_has_autor la " +
                                                "where l.idLibro = '" + objConn.rs.getString(1) + "' " +
                                                "and l.idLibro = la.Libro_idLibro " +
                                                "and la.Autor_idAutor = a.idAutor;";
                            System.out.println(consulta2);
                            obj2.Consult(consulta2);
                            autorn = obj2.rs.getString(1) + " " + obj2.rs.getString(2) + " " + obj2.rs.getString(3);

                            MysqlConn obj3 = new MysqlConn();
                            String consulta3 = "select categoria.Nombre from categoria, libro " +
                                                "where libro.idLibro = '" + objConn.rs.getString(1) + "' " +
                                                "and libro.Categoria_idCategoria = categoria.idCategoria;";
                            System.out.println(consulta3);
                            obj3.Consult(consulta3);
                            categoria = obj3.rs.getString(1);*/
                            
                            datos[i][0] = objConn.rs.getString(1);
                            datos[i][1] = objConn.rs.getString(2);
                            datos[i][2] = objConn.rs.getString(3);
                            datos[i][3] = objConn.rs.getString(4);
                            datos[i][4] = objConn.rs.getString(5);
                            datos[i][5] = objConn.rs.getString(6);
                            datos[i][6] = objConn.rs.getString(7);
                            datos[i][7] = objConn.rs.getString(8)+" "+objConn.rs.getString(9)+" "+objConn.rs.getString(10);
                            datos[i][8] = objConn.rs.getString(11);
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
                 "#libro", "Título", "Editorial", "Idioma", "País", "Fecha Publicación", "Ubicación", "Autor", "Categoría"
                };
                
                this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
          }
    }
    
    private void extraer(String cads){
      
            
            //String consulta = "select idlibro " + 
           //         "from libro where titulo LIKE '"+cads+"%'; ";
         //   System.out.println(consulta);
           // objConn.Consult(consulta);

        /*  String  consulta = "select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Aguascalientes' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro l, autor a, Subcategoria s, libro_has_autor la " +
                "where l.titulo like '"+cads+"%'"
                    + "and l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor";
            
            					
	*/				           //Consulta distribuida
            String consulta="select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Aguascalientes' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro l, autor a, Subcategoria s, libro_has_autor la " +
                "where l.titulo like '"+cads+"%' and"
                    + " l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor" +
                    " union " +
                   "select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Aguascalientes' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro2 l, autor a, Subcategoria2 s, libro_has_autor la " +
                "where l.titulo like '"+cads+"%' and "
                    + "l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor" +
                    "  union " +
                   "select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Aguascalientes' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro3 l, autor a, Subcategoria3 s, libro_has_autor la " +
                "where l.titulo like '"+cads+"%' and"
                    + " l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor";
					
            System.out.println(consulta);
            objConn.Consult(consulta);
   
        
        int n = 0;
        int i;
        if(objConn.rs!= null){
            //obtener numero de registros
            try{
                objConn.rs.last();
                n= objConn.rs.getRow();
                System.out.println("ROWS: " + n);
                objConn.rs.first();
            }catch(Exception e){
            }
            //llenar la matriz de herramienta
            String [][] datos = new String [n][9];
            for (i = 0; i < n; i++) {
                try{
                    datos[i][0] = objConn.rs.getString(1);
                    datos[i][1] = objConn.rs.getString(2);
                    datos[i][2] = objConn.rs.getString(3);
                    datos[i][3] = objConn.rs.getString(4);
                    datos[i][4] = objConn.rs.getString(5);
                    datos[i][5] = objConn.rs.getString(6);
                    datos[i][6] = objConn.rs.getString(7);
                    datos[i][7] = objConn.rs.getString(8)+" "+objConn.rs.getString(9)+" "+objConn.rs.getString(10);
                    datos[i][8] = objConn.rs.getString(11);
                }catch(Exception e){
                }
                try{
                    objConn.rs.next();
                }catch(Exception e){
                }
            } 
            //cargamos la informacion a la tabla

            String []columnas = new String []{
                "#libro", "Título", "Editorial", "Idioma", "País", "Fecha Publicación", "Ubicación", "Autor", "Categoría"};
            this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
        }
    
        
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        addTool = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        busqueda = new javax.swing.JTextField();
        menu = new javax.swing.JPanel();
        porTitulo = new java.awt.TextField();
        titulo = new javax.swing.JLabel();
        porEditorial = new java.awt.TextField();
        editorial = new javax.swing.JLabel();
        porIdioma = new java.awt.TextField();
        idioma = new javax.swing.JLabel();
        porPais = new java.awt.TextField();
        pais = new javax.swing.JLabel();
        porUbicacion = new java.awt.TextField();
        ubicacion = new javax.swing.JLabel();
        porFecha = new java.awt.TextField();
        fecha = new javax.swing.JLabel();
        buscarPor = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        autor = new javax.swing.JLabel();
        categoria = new javax.swing.JLabel();
        porCategoria = new java.awt.TextField();
        porAutor = new java.awt.TextField();
        buscarAv = new javax.swing.JButton();
        buscarAvanzado = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel10.setText("CIUDAD");

        jLabel11.setText("CIUDAD");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setText("Biblioteca");

        addTool.setBackground(new java.awt.Color(51, 51, 255));
        addTool.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        addTool.setForeground(new java.awt.Color(255, 255, 255));
        addTool.setText("iniciar sesión");
        addTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToolActionPerformed(evt);
            }
        });

        tblDatos.setBackground(Color.white);
        tblDatos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Libro", "Título", "Editorial", "Idioma", "País", "Fecha Publicación", "Ubicación", "Autor", "Categoría"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busquedaKeyTyped(evt);
            }
        });

        menu.setBackground(new java.awt.Color(255, 255, 255));

        titulo.setText("Título");

        editorial.setText("Editorial");

        idioma.setText("Idioma");

        pais.setText("País");

        ubicacion.setText("Ubicación");

        fecha.setText("Fecha de publicación");

        buscarPor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscarPor.setText("Buscar libro por:");

        cancelar.setBackground(new java.awt.Color(255, 0, 51));
        cancelar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setText("cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        autor.setText("Autor");

        categoria.setText("Categoría");

        buscarAv.setBackground(new java.awt.Color(0, 153, 0));
        buscarAv.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        buscarAv.setForeground(new java.awt.Color(255, 255, 255));
        buscarAv.setText("Buscar");
        buscarAv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarAvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buscarAv)
                        .addGap(18, 18, 18)
                        .addComponent(cancelar))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titulo)
                                    .addComponent(porTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editorial))
                                .addGap(26, 26, 26)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idioma))
                                .addGap(25, 25, 25)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porPais, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pais))
                                .addGap(23, 23, 23)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ubicacion)
                                    .addComponent(porUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(menuLayout.createSequentialGroup()
                                .addGap(512, 512, 512)
                                .addComponent(buscarPor))
                            .addGroup(menuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(autor)
                                    .addComponent(porAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoria)
                                    .addComponent(porCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha)
                            .addComponent(porFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscarPor)
                .addGap(18, 32, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editorial)
                        .addComponent(idioma)
                        .addComponent(pais)
                        .addComponent(ubicacion)
                        .addComponent(fecha)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(porFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelar)
                            .addComponent(buscarAv)))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(autor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(categoria, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        buscarAvanzado.setBackground(new java.awt.Color(0, 153, 0));
        buscarAvanzado.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        buscarAvanzado.setForeground(new java.awt.Color(255, 255, 255));
        buscarAvanzado.setText("Búsqueda avanzada");
        buscarAvanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarAvanzadoActionPerformed(evt);
            }
        });

        jLabel7.setText("Búsqueda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addTool, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarAvanzado)))
                        .addGap(66, 66, 66))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(addTool, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(buscarAvanzado))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void addToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToolActionPerformed
        
        JTextField user = new JTextField(20);
        JTextField pass = new JTextField(15);
        JPanel sesion = new JPanel();
        sesion.add(new JLabel("Usuario: "));
        sesion.add(user);
        sesion.add(Box.createHorizontalStrut(15));
        sesion.add(new JLabel("Contraseña: "));
        sesion.add(pass);
        
        JOptionPane.showConfirmDialog(null, sesion ,"iniciar sesión", JOptionPane.OK_CANCEL_OPTION);
        
        if(JOptionPane.CANCEL_OPTION==1){
         String consulta = "select contrasena, idTrabajador,Nombre from mydb.trabajador  where idTrabajador='"+user.getText()+"' and contrasena='"+pass.getText()+"'"  ;
        
        objConn.Consult(consulta);
        if(objConn.rs!=null){
            try {
               idTr= objConn.rs.getString(2);
               TRName=objConn.rs.getString(3);
                
                this.dispose();
                Principal main = new Principal();
                main.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(sesion,
                "Usuario o contraseña incorrectos",
                "Datos incorrectos",
                JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            JOptionPane.showMessageDialog(sesion,
                "Usuario o contraseña incorrectos",
                "Datos incorrectos",
                JOptionPane.ERROR_MESSAGE);
        }
        
        }
    }//GEN-LAST:event_addToolActionPerformed

    private void buscarAvanzadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarAvanzadoActionPerformed
        busqueda.setVisible(false);
        buscarAvanzado.setVisible(false);
        jLabel7.setVisible(false);
        buscarAv.setVisible(true);
        menu.setVisible(true);
        autor.setVisible(true);
        porAutor.setVisible(true);
        categoria.setVisible(true);
        porCategoria.setVisible(true);
        titulo.setVisible(true);
        porTitulo.setVisible(true);
        editorial.setVisible(true);
        porEditorial.setVisible(true);
        idioma.setVisible(true);
        porIdioma.setVisible(true);
        pais.setVisible(true);
        porPais.setVisible(true);
        ubicacion.setVisible(true);
        porUbicacion.setVisible(true);
        fecha.setVisible(true);
        porFecha.setVisible(true);
        cancelar.setVisible(true);
        
    }//GEN-LAST:event_buscarAvanzadoActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDatosMouseClicked

    private void tblDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDatosKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblDatosKeyReleased

    private void tblDatosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblDatosMouseReleased

    private void busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if(caracter != KeyEvent.VK_BACKSPACE){
            cadena += caracter;
            System.out.println("char->"+cadena+"");
            extraer(cadena);
        }
        else{
            try{
                cadena = cadena.substring(0, cadena.length()-1);
                llenarTabla();
            }catch(StringIndexOutOfBoundsException e){
                cadena = "";
                llenarTabla();
            }
            System.out.println("char->"+cadena+"");
        }
    }//GEN-LAST:event_busquedaKeyTyped

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        jLabel7.setVisible(true);
        buscarAvanzado.setVisible(true);
        busqueda.setVisible(true);
        menu.setVisible(false);
        cancelar.setVisible(false);
        titulo.setVisible(false);
        porTitulo.setVisible(false);
        editorial.setVisible(false);
        porEditorial.setVisible(false);
        idioma.setVisible(false);
        porIdioma.setVisible(false);
        pais.setVisible(false);
        porPais.setVisible(false);
        ubicacion.setVisible(false);
        porUbicacion.setVisible(false);
        fecha.setVisible(false);
        porFecha.setVisible(false);
        cancelar.setVisible(true);
        buscarAv.setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void buscarAvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarAvActionPerformed
        // TODO add your handling code here:
        String[] vecs ;
        objConn=new MysqlConn();
        String titulo = porTitulo.getText();
        String editorial = porEditorial.getText();
        String idioma = porIdioma.getText();
        String pais = porPais.getText();
        String ubicacion = porUbicacion.getText();
        String fecha = porFecha.getText();
        String autor = porAutor.getText();
        String categoria = porCategoria.getText();
        if(autor.length()>2){
        vecs= autor.split(" ");}else{
        
            
            vecs=new String[3];
            vecs[0]="";
            vecs[1]="";
            vecs[2]="";
        }
        
      
            
        /*    String consulta = "select l.idlibro, l.titulo, l.editorial, l.idioma, l.pais, l.fechapubli, l.ubicacion, " +
                        "a.Nombre, a.apPaterno, a.apMaterno, c.Nombre " +
                        "from libro l, autor a, categoria c, libro_has_autor la " +
                        "where l.titulo = '" + titulo + "' " +
                        "or l.editorial = '" + editorial + "' " +
                        "or l.idioma = '" + idioma + "' " +
                        "or l.pais = '" + pais + "' " +
                        "or l.fechapubli = '" + fecha + "' " +
                        "or l.ubicacion = '" + ubicacion + "' " +
                        "or a.nombre = '" + vecs[0] + "' " +
                        "or a.appaterno = '" + vecs[1] + "' " +
                        "or a.apmaterno = '" + vecs[2] + "' " +
                        "or c.Nombre = '" + categoria + "';";
            */

         
            			
		String consulta = " select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Aguascalientes' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro l, autor a, Subcategoria s, libro_has_autor la " +
                "where l.titulo Like '"+titulo+"%'"+
                   "and l.editorial like '" + editorial + "%' " +
                        "and l.idioma like '" + idioma + "%' " +
                        "and l.pais like '" + pais + "%' " +
                        "and l.fechapubli like '" + fecha + "%' " +
                        //"or ubicacion = '" + ubicacion + "' " +
                       // "and a.nombre like '" + vecs[0] + "%' " +
                        "and a.appaterno like '" + vecs[1] + "%' " +
                        "and a.apmaterno like '" + vecs[2] + "%' " +
                        "and s.Nombre like '" + categoria + "%'"
                   + "and  l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor union"+
                      
                     " select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'Rincón de Romos' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro2 l, autor a, Subcategoria2 s, libro_has_autor la " +
                "where l.titulo like '"+titulo+"%'"+
                   "and l.editorial like '" + editorial + "%' " +
                        "and l.idioma like '" + idioma + "%' " +
                        "and l.pais like '" + pais + "%' " +
                        "and l.fechapubli like '" + fecha + "%' " +
                        //"or ubicacion = '" + ubicacion + "' " +
                        //"and a.nombre like '" + vecs[0] + "%' " +
                        "and a.appaterno like '" + vecs[1] + "%' " +
                        "and a.apmaterno like '" + vecs[2] + "%' " +
                        "and s.Nombre like '" + categoria + "%'"
                   + "and  l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor union"+
                        
                " select concat_ws('-',l.Subcategoria_Categoria_idCategoria,l.Subcategoria_idSub,l.Ctitulo,l.numEjemplar,l.Volumen,l.fechaPubli) as idlibro, l.titulo, l.editorial, l.idioma, " + 
                "l.pais , fechaPubli, 'San Jóse de Grácia' as ubicacion, " + 
                "a.Nombre, a.apPaterno, a.apMaterno, s.Nombre  " +
                "from libro3 l, autor a, Subcategoria3 s, libro_has_autor la " +
                "where l.titulo like '"+titulo+"%'"+
                   "and l.editorial like '" + editorial + "%' " +
                        "and l.idioma like '" + idioma + "%' " +
                        "and l.pais like '" + pais + "%' " +
                        "and l.fechapubli like '" + fecha + "%' " +
                        //"or ubicacion = '" + ubicacion + "' " +
                        //"and a.nombre like '" + vecs[0] + "%' " +
                        "and a.appaterno like '" + vecs[1] + "%' " +
                        "and a.apmaterno like '" + vecs[2] + "%' " +
                        "and s.Nombre like '" + categoria + "%' "
                   + "and  l.Subcategoria_Categoria_idCategoria = la.Libro_Subcategoria_Categoria_idCategoria " +
                "and l.Subcategoria_idSub = la.Libro_Subcategoria_idSub " +
                "and l.Ctitulo= la.Libro_Ctitulo " +
                "and l.numEjemplar= la.Libro_numEjemplar " +
                "and l.Volumen= la.Libro_Volumen " +  
                "and l.fechaPubli= la.Libro_fechaPubli " +
                "and l.Subcategoria_idSub = s.idSub " +
                "and la.Autor_CAutor = a.CAutor"        ;  
              System.out.println(consulta);
            objConn.Consult(consulta);
   
        
        int n = 0;
        int i;
        if(objConn.rs!= null){
            //obtener numero de registros
            try{
                objConn.rs.last();
                n= objConn.rs.getRow();
                System.out.println("ROWS: " + n);
                objConn.rs.first();
            }catch(Exception e){
            }
            //llenar la matriz de herramienta
            String [][] datos = new String [n][9];
            for (i = 0; i < n; i++) {
                try{
                    datos[i][0] = objConn.rs.getString(1);
                    datos[i][1] = objConn.rs.getString(2);
                    datos[i][2] = objConn.rs.getString(3);
                    datos[i][3] = objConn.rs.getString(4);
                    datos[i][4] = objConn.rs.getString(5);
                    datos[i][5] = objConn.rs.getString(6);
                    datos[i][6] = objConn.rs.getString(7);
                    datos[i][7] = objConn.rs.getString(8)+" "+objConn.rs.getString(9)+" "+objConn.rs.getString(10);
                    datos[i][8] = objConn.rs.getString(11);
                }catch(Exception e){
                }
                try{
                    objConn.rs.next();
                }catch(Exception e){
                }
            } 
            //cargamos la informacion a la tabla

            String []columnas = new String []{
                "#libro", "Título", "Editorial", "Idioma", "País", "Fecha Publicación", "Ubicación", "Autor", "Categoría"};
            this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(datos,columnas));
        }
        objConn.closeRsStmt();
    }//GEN-LAST:event_buscarAvActionPerformed

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
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTool;
    private javax.swing.JLabel autor;
    private javax.swing.JButton buscarAv;
    private javax.swing.JButton buscarAvanzado;
    private javax.swing.JLabel buscarPor;
    private javax.swing.JTextField busqueda;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel categoria;
    private javax.swing.JLabel editorial;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel idioma;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel pais;
    private java.awt.TextField porAutor;
    private java.awt.TextField porCategoria;
    private java.awt.TextField porEditorial;
    private java.awt.TextField porFecha;
    private java.awt.TextField porIdioma;
    private java.awt.TextField porPais;
    private java.awt.TextField porTitulo;
    private java.awt.TextField porUbicacion;
    private javax.swing.JTable tblDatos;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel ubicacion;
    // End of variables declaration//GEN-END:variables
}
