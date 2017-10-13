package com.example.ellui.interfaz2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class EditaPac extends AppCompatActivity {
    EditText nombre;
    EditText calle;
    EditText numero;
    EditText colonia;
    Boolean bandera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_pac);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nombre = (EditText)findViewById(R.id.nombre);
        calle = (EditText)findViewById(R.id.calle);
        numero = (EditText)findViewById(R.id.numero);
        colonia = (EditText)findViewById(R.id.colonia);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(nombre.getText().toString()) && !TextUtils.isEmpty(calle.getText().toString())
                        && !TextUtils.isEmpty(numero.getText().toString()) && !TextUtils.isEmpty(colonia.getText().toString()))
                    new actualiza().execute();
                else
                    new popup("Campo(s) vacíos, ingrese los datos faltantes.");
            }
        });

        if (bandera) {
            new consulta().execute();
        }
    }

    void bloqueain(){
        nombre.setInputType(0);
        calle.setInputType(0);
        numero.setInputType(0);
        colonia.setInputType(0);
        findViewById(R.id.fab).setEnabled(false);
    }
    void actualiza2(String nom, String cal, String num, String col){
        nombre.setInputType(131073);
        calle.setInputType(131073);
        numero.setInputType(131073);
        colonia.setInputType(131073);
        findViewById(R.id.fab).setEnabled(true);
        nombre.setText(nom);
        calle.setText(cal);
        numero.setText(num);
        colonia.setText(col);
    }

    class consulta extends AsyncTask{
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
                new popup("No hay registros que editar.");
                bloqueain();
            }else if(resultado.equals("Algo")){
                actualiza2(nom, cal, num, col);
            }else{
                new popup(resultado);
            }
        }
    }

    class actualiza extends AsyncTask {
        MysqlConn actualizar = new MysqlConn();
        String nom = nombre.getText().toString();
        String cal = calle.getText().toString();
        String num = numero.getText().toString();
        String col = colonia.getText().toString();
        String resultado = "";
        @Override

        protected String doInBackground(Object[] params) {

            try {
                actualizar.Connect();
                int result = actualizar.Update("UPDATE paciente SET nombre='"+nom
                        +"', calle='"+cal+"', numero='"+num
                        +"', colonia='"+col+ "' where idpaciente="+ 1 );
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

    class popup{
        popup(String texto){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditaPac.this);
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
    }

}
