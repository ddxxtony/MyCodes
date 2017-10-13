package com.example.ellui.interfaz2;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    public static boolean onMedicamentos,onResponsables,onPaciente;
    public static  boolean HayMedicamentos=false;
    public static  boolean   HayResponsables=false;
    public static ArrayList<ImageItem> imageItems = new ArrayList<>();
    public static ArrayList<ImageItem> imageItemsResponsables = new ArrayList<>();

    EditText nombre;
    EditText calle;
    EditText numero;
    EditText colonia;
    Boolean bandera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageItems=new ArrayList<ImageItem>();
        imageItemsResponsables=new ArrayList<ImageItem>();
        new Consulta_Medicamento().execute();
        new Consulta_Responsables().execute();
        new  consultainfopac().execute();
        onlistenerBTADD();
        StartLista();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fapfer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent docreg = new Intent(getApplicationContext(), EditaPac.class);
                startActivity(docreg);
            }
        });

       // nombre = (EditText)findViewById(R.id.pnombre);
     //   calle = (EditText)findViewById(R.id.calle);
      //  numero = (EditText)findViewById(R.id.numero);
       // colonia = (EditText)findViewById(R.id.colonia);




    }

//////////////////////////////////////////////////abrir agregar
    public void onlistenerBTADD(){

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onMedicamentos) {
                    Intent nForm = new Intent(MainActivity.this, Add_Medic.class);
                    startActivity(nForm);
                    //finish();
                }
                if(onResponsables){
                    Intent nForm = new Intent(MainActivity.this, RegistraDoc.class);
                    startActivity(nForm);
                }
                if(onPaciente){
                    //Intent nForm = new Intent(MainActivity.this, Add_Medic.class);
                   // startActivity(nForm);

                }

            }
        });
    }

public void StartLista(){

    ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();
    TextView textg=(TextView)findViewById(R.id.textView2);
//    textg.setText("ddsfsdffffffffffffffffffffff");

    datos.add(new Lista_entrada(R.drawable.pm, "Medicamentos", ""));
    datos.add(new Lista_entrada(R.drawable.pyshy, "Responsables", ""));
    datos.add(new Lista_entrada(R.drawable.paciente, "Paciente", ""));
    datos.add(new Lista_entrada(R.drawable.bye, "Terminar Tratamiento", ""));
    datos.add(new Lista_entrada(R.drawable.salir, "Salir", ""));


    lista = (ListView) findViewById(R.id.ListView_listado);
    lista.setAdapter(new Lista_adaptador(this, R.layout.entrada, datos){
        @Override
        public void onEntrada(Object entrada, View view) {
            if (entrada != null) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                if (texto_superior_entrada != null)
                    texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                if (texto_inferior_entrada != null)
                    texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo());

                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                if (imagen_entrada != null)
                    imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
            }
        }
    });

    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
            Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion);

            CharSequence texto = "Seleccionado: " + elegido.get_textoEncima();
            switch(elegido.get_textoEncima().toString()){
                case "Medicamentos":
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                    onMedicamentos=true;
                    onResponsables=false;
                    onPaciente=false;
                    if(HayMedicamentos){
                        findViewById(R.id.includePacienteinfo).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_MV).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_ResVacio).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_M_Con).setVisibility(View.VISIBLE);
                        findViewById(R.id.includeResponsablesC).setVisibility(View.GONE);

                    }else{
                        findViewById(R.id.includeDer_MV).setVisibility(View.VISIBLE);
                        findViewById(R.id.includeDer_ResVacio).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_M_Con).setVisibility(View.GONE);
                        findViewById(R.id.includeResponsablesC).setVisibility(View.GONE);
                        findViewById(R.id.includePacienteinfo).setVisibility(View.GONE);

                    }

                break;

                case "Responsables":

                    onMedicamentos=false;
                    onResponsables=true;
                    onPaciente=false;
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                    if(HayResponsables){

                        findViewById(R.id.includeDer_MV).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_ResVacio).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_M_Con).setVisibility(View.GONE);
                        findViewById(R.id.includeResponsablesC).setVisibility(View.VISIBLE);
                        findViewById(R.id.includePacienteinfo).setVisibility(View.GONE);
                    }else{
                        findViewById(R.id.includeDer_MV).setVisibility(View.GONE);
                        findViewById(R.id.includeDer_ResVacio).setVisibility(View.VISIBLE);
                        findViewById(R.id.includeDer_M_Con).setVisibility(View.GONE);
                        findViewById(R.id.includeResponsablesC).setVisibility(View.GONE);
                        findViewById(R.id.includePacienteinfo).setVisibility(View.GONE);
                    }
                    break;

                case "Paciente":
                    onMedicamentos=false;
                    onResponsables=false;
                    onPaciente=true;

                    //Intent nForm = new Intent(MainActivity.this, InfoPac.class);
                    //startActivity(nForm);
                    //finish();
                    findViewById(R.id.fab).setVisibility(View.GONE);
                    findViewById(R.id.includeDer_MV).setVisibility(View.GONE);
                    findViewById(R.id.includeDer_ResVacio).setVisibility(View.GONE);
                    findViewById(R.id.includeDer_M_Con).setVisibility(View.GONE);
                    findViewById(R.id.includeResponsablesC).setVisibility(View.GONE);
                    findViewById(R.id.includePacienteinfo).setVisibility(View.VISIBLE);
                break;

                case "Terminar Tratamiento":
                    new popup().popup2("Esta consiente que se borraran todos los mediamentos  que haya Registrado así como los responsables?", "Seguro?");

                    break;
                default: finish();break;

            }
           // Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
          //  toast.show();
        }
    });

}



    class Consulta_Medicamento extends AsyncTask {

        private GridView gridView;
        private GridViewAdapter gridAdapter;
        private Boolean error;
        MysqlConn EBDCon;/// Base de datos externa Mysql
        private ArrayList<ImageItem> images = new ArrayList<>();
        @Override
        protected void  onPreExecute(){

        }





        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(error){
               // Toast.makeText(getBaseContext(), "Error ",
             //           Toast.LENGTH_LONG).show();
            }else{
             //   Toast.makeText(getBaseContext(), "Exito",
             //           Toast.LENGTH_LONG).show();
                MainActivity.HayMedicamentos=true;
                MainActivity.imageItems=images;
                PreparaGrid();
            }
        }
        public  void PreparaGrid(){

            gridView = (GridView) findViewById(R.id.gridViewgrid);
            gridAdapter = new GridViewAdapter(MainActivity.this, R.layout.grid_item_layout, getData());
            gridView.setAdapter(gridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                    //Create intent
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                       intent.putExtra("title", item.getTitle());
                     intent.putExtra("image", item.getName());
                    intent.putExtra("id",item.getId());
                    //Start details activity
                    //Start details activity
                    startActivity(intent);

                }
            });
        }

        private ArrayList<ImageItem> getData() {
            final ArrayList<ImageItem> images = new ArrayList<>();
            for (int i = 0; i < MainActivity.imageItems.size(); i++) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pasti_5+i);
                String pasti="pasti_"+Integer.toString(i+5);
                images.add(new ImageItem(bitmap,MainActivity.imageItems.get(i).getTitle(),MainActivity.imageItems.get(i).getId(),pasti));
            }
            return images;
        }


        @Override
        protected Object doInBackground(Object[] params) {

            error=false;
            EBDCon= new MysqlConn();
            // new Registra_medicamento().execute(etname,etdosis,RG);
            try{

                EBDCon.Connect();
                EBDCon.Consult("select idmedicamento,nombre from medicamento");
                Bitmap bitmap;
                EBDCon.rs.last();
                int n=EBDCon.rs.getRow();
                EBDCon.rs.first();
                if( n>0){
                    do {
                        HayMedicamentos=true;
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_1);
                        images.add(new ImageItem(bitmap, EBDCon.rs.getString(2).toString(),
                                Integer.parseInt(EBDCon.rs.getString(1).toString())));
                    }while(EBDCon.rs.next());
                }else{
                    HayMedicamentos=false;
                    error=true;
                }
                EBDCon.desConnect();
            }
            catch(Exception e){
                error=true;
                HayMedicamentos=false;

            }
            return null;
        }
    }

    class Consulta_Responsables extends AsyncTask {

        private GridView gridView;
        private GridViewAdapter gridAdapter;
        private Boolean error;
        MysqlConn EBDCon;/// Base de datos externa Mysql
        private ArrayList<ImageItem> images = new ArrayList<>();
        @Override
        protected void  onPreExecute(){

        }



        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(error){
              //  Toast.makeText(getBaseContext(), "Error ",
          //              Toast.LENGTH_LONG).show();
            }else{
              //  Toast.makeText(getBaseContext(), "Exito",
              //          Toast.LENGTH_LONG).show();
                MainActivity.HayResponsables=true;
                MainActivity.imageItemsResponsables=images;
                PreparaGrid();
            }
        }
        public  void PreparaGrid(){

            gridView = (GridView) findViewById(R.id.gridViewgridResponsables);
            gridAdapter = new GridViewAdapter(MainActivity.this, R.layout.grid_item_layout, getData());
            gridView.setAdapter(gridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                    //Create intent
                    Intent intent = new Intent(MainActivity.this, inforrespon.class);
                   // intent.putExtra("title", item.getTitle());
                    //intent.putExtra("image", item.getImage());
                    intent.putExtra("id",item.getId());
                    //Start details activity
                    //Start details activity
                    startActivity(intent);

                }
            });
        }

        private ArrayList<ImageItem> getData() {
            final ArrayList<ImageItem> images = new ArrayList<>();
            for (int i = 0; i < MainActivity.imageItemsResponsables.size(); i++) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image_1+i);
                images.add(new ImageItem(bitmap,MainActivity.imageItemsResponsables.get(i).getTitle(),MainActivity.imageItemsResponsables.get(i).getId()));
            }
            return images;
        }


        @Override
        protected Object doInBackground(Object[] params) {

            error=false;
            EBDCon= new MysqlConn();
            // new Registra_medicamento().execute(etname,etdosis,RG);
            try{

                EBDCon.Connect();
                EBDCon.Consult("select idresponsable,nombre from responsable");
                Bitmap bitmap;
                EBDCon.rs.last();
                int n=EBDCon.rs.getRow();
                EBDCon.rs.first();
                if( n>0){
                    do {
                        HayResponsables=true;
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_1);
                        images.add(new ImageItem(bitmap, EBDCon.rs.getString(2).toString(),
                                Integer.parseInt(EBDCon.rs.getString(1).toString())));
                    }while(EBDCon.rs.next());
                }else{
                    error=true;
                    HayResponsables=false;
                }
                EBDCon.desConnect();
            }
            catch(Exception e){
                error=true;
                HayResponsables=false;

            }
            return null;
        }
    }

    class consulta_paciente extends AsyncTask {
        MysqlConn consulta = new MysqlConn();
        String nom, cal, num, col;
        String resultado = "";

        public void actualiza2(String nom, String cal, String num, String col){
            nombre.setText(nom);
            calle.setText(cal);
            numero.setText(num);
            colonia.setText(col);
        }

        @Override


        protected String doInBackground(Object[] params) {
            try{
                consulta.Connect();
                consulta.Consult("SELECT * FROM paciente");
                if (consulta.rs.first()){
                    resultado = "Algo";
                    do{
                        nom = consulta.rs.getString("nombre");
                        cal = consulta.rs.getString("calle");
                        num = consulta.rs.getString("numero");
                        col = consulta.rs.getString("colonia");
                    }while(consulta.rs.next());
                }else{
                    resultado = "Vacío";
                }
                consulta.desConnect();
            }catch (Exception e){
                resultado = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            if (resultado.equals("Vacío")) {
               // new Main2Activity.popup("No hay registros.");
            }else if(resultado.equals("Algo")){
                actualiza2(nom, cal, num, col);
            }else{
               new MainActivity.popup();
            }
        }
    }

    class Borra_Todo extends AsyncTask {
        MysqlConn consulta = new MysqlConn();
        String resultado = "";


        @Override


        protected String doInBackground(Object[] params) {
            try{
                consulta.Connect();
               int a= consulta.Update("Delete from medicamento;");
                int b=consulta.Update("Delete from responsable;");
                int c=consulta.Update("Delete from paciente;");
               int d= consulta.Update("Delete from log;");

                resultado = "Algo";

                consulta.desConnect();
            }catch (Exception e){
                resultado = "Vacio";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            if (resultado.equals("Vacío")) {
                // new Main2Activity.popup("No hay registros.");
            }else if(resultado.equals("Algo")){

                Intent docreg = new Intent(getApplicationContext(), Decision.class);
                startActivity(docreg);
                finish();
            }else{

            }
        }
    }






    void actualiza2(String nom, String cal, String num, String col){
        TextView pnombre;
        TextView pcalle;
        TextView pnumero;
        TextView pcolonia;
        pnombre = (TextView)findViewById(R.id.pnombre);
        pcalle = (TextView)findViewById(R.id.pcalle);
        pnumero = (TextView)findViewById(R.id.pnumero);
        pcolonia = (TextView)findViewById(R.id.pcolonia);

        pnombre.setText(nom);
        pcalle.setText(cal);
        pnumero.setText(num);
        pcolonia.setText(col);
    }
    class consultainfopac extends AsyncTask {
        MysqlConn consulta = new MysqlConn();
        String nom, cal, num, col;
        String resultado = "";

        @Override

        protected String doInBackground(Object[] params) {
            try{
                consulta.Connect();
                consulta.Consult("SELECT * FROM paciente");
                if (consulta.rs.first()){
                    resultado = "Algo";
                    do{
                        nom = consulta.rs.getString("nombre");
                        cal = consulta.rs.getString("calle");
                        num = consulta.rs.getString("numero");
                        col = consulta.rs.getString("colonia");
                    }while(consulta.rs.next());
                }else{
                    resultado = "Vacío";
                }
                consulta.desConnect();
            }catch (Exception e){
                resultado = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            if (resultado.equals("Vacío")) {

            }else if(resultado.equals("Algo")){
                actualiza2(nom, cal, num, col);
            }else{
            }
        }
    }

    class popup{
        public void popup2(String texto, final String texto2){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage(texto);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Sí",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                            builder1.setMessage(texto2);
                            builder1.setCancelable(true);
                            builder1.setPositiveButton(
                                    "Sí",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            new  Borra_Todo().execute();
                                            finish();
                                            dialog.cancel();
                                        }
                                    });
                            builder1.setNegativeButton(
                                    "No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }
}







