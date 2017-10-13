package com.example.ellui.interfaz2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditaResp extends AppCompatActivity {
    List<String> ids = new ArrayList<String>();
    List<String> nombres = new ArrayList<String>();
    List<String> correos = new ArrayList<String>();
    Boolean bandera = true;
    Spinner spin;
    EditText nombre;
    EditText correo;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_resp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getInt("id");
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        spin = (Spinner)findViewById(R.id.spinner);
        nombre = (EditText)findViewById(R.id.nombre);
        correo = (EditText)findViewById(R.id.correo);
        spin.setVisibility(View.INVISIBLE);

        // Bitmap bitmap = getIntent().getParcelableExtra("image");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(nombre.getText().toString()) && !TextUtils.isEmpty(correo.getText().toString()))
                    if(validaemail(correo.getText().toString()))
                        new actualiza().execute();
                    else
                        new popup("Correo inválido, verifique su correo.");
                else
                    new popup("Campo(s) vacíos, ingrese los datos faltantes.");
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fapfer);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new popup().popup2("Esta seguro de eliminar el registro?", "Esta seguro?");
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombre.setText(nombres.get(spin.getSelectedItemPosition()));
                correo.setText(correos.get(spin.getSelectedItemPosition()));
                //new popup(nombre.getInputType()+"");   131073
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bandera) {
            new consulta().execute();
        }
    }

    boolean validaemail(String correo){
        if (TextUtils.isEmpty(correo)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches();
        }
    }

    void actualiza2(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, correos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setSelection(0);
        nombre.setInputType(131073);
        correo.setInputType(131073);
        findViewById(R.id.fab).setEnabled(true);
        findViewById(R.id.fapfer).setEnabled(true);
        nombre.setText(nombres.get(0));
        correo.setText(correos.get(0));
    }
    void bloqueain(){
        nombre.setInputType(0);
        correo.setInputType(0);
        findViewById(R.id.fab).setEnabled(false);
        findViewById(R.id.fapfer).setEnabled(false);
    }

    class consulta extends AsyncTask{
        MysqlConn consulta = new MysqlConn();
        String resultado = "";

        @Override

        protected String doInBackground(Object[] params) {
            try{
                consulta.Connect();
                consulta.Consult("SELECT * FROM responsable where idresponsable= "+id);
                if (consulta.rs.first()){
                    resultado = "Algo";
                    do{
                        ids.add(consulta.rs.getString("idresponsable"));
                        nombres.add(consulta.rs.getString("nombre"));
                        correos.add(consulta.rs.getString("correo"));
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
                new popup("No hay registros que editar.");
                bloqueain();
            }else if(resultado.equals("Algo")){
                actualiza2();
                bandera = false;
            }else{
                new popup(resultado);
            }
        }
    }


    class actualiza extends AsyncTask {
        MysqlConn actualizar = new MysqlConn();
        String nom = nombre.getText().toString();
        String cor = correo.getText().toString();
        String id = ids.get(spin.getSelectedItemPosition());
        String resultado = "";
        @Override

        protected String doInBackground(Object[] params) {

            try {
                actualizar.Connect();
                int result = actualizar.Update("UPDATE responsable SET nombre='"+nom
                        +"', correo='"+cor+"' where idresponsable="+ id );
                if(result > 0){
                    //new popup("Exito al insertar");
                    resultado = "Exito al actualizar";
                }else{
                    resultado = "Error al actualizar";
                }
                return resultado;
            }catch (Exception e){
                //new popup(e.toString());
                resultado = e.toString();
                return resultado;

            }

        }

        @Override
        protected void onPostExecute(Object result) {

            new popup(resultado);
            /****  Insertar codigo de cambiar de ventana si la actualización fue exitosa*****/
            if(resultado.equals("Exito al actualizar")){
                //Insertar codigo para avanzar de ventana con un popup doble
                Intent docreg = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(docreg);
                finish();
            }
        }
    }

    class eliminar extends AsyncTask {
        MysqlConn actualizar = new MysqlConn();
        String id = ids.get(spin.getSelectedItemPosition());
        String resultado = "";
        @Override

        protected String doInBackground(Object[] params) {

            try {
                actualizar.Connect();
                int result = actualizar.Update("DELETE FROM responsable WHERE idresponsable="+ id );
                if(result > 0){
                    //new popup("Exito al eliminar");
                    resultado = "Exito al eliminar";
                }else{
                    resultado = "Error al eliminar";
                }
                return resultado;
            }catch (Exception e){
                //new popup(e.toString());
                resultado = e.toString();
                return resultado;

            }

        }

        @Override
        protected void onPostExecute(Object result) {

            new popup(resultado);
            /****  Insertar codigo de cambiar de ventana si la actualización fue exitosa*****/
            if(resultado.equals("Exito al eliminar")){
                //Insertar codigo para avanzar de ventana con un popup doble
                Intent docreg = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(docreg);
                finish();
            }
        }
    }

    class popup{
        popup(){

        }
        popup(String texto){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditaResp.this);
            builder1.setMessage(texto);
            builder1.setCancelable(true);

            builder1.setNeutralButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });



            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        public void popup2(String texto, final String texto2){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditaResp.this);
            builder1.setMessage(texto);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Sí",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditaResp.this);
                            builder1.setMessage(texto2);
                            builder1.setCancelable(true);
                            builder1.setPositiveButton(
                                    "Sí",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            new eliminar().execute();
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
