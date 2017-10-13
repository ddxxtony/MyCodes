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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EliminaResp extends AppCompatActivity {
    List<String> ids = new ArrayList<String>();
    List<String> correos = new ArrayList<String>();
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimina_resp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        spin = (Spinner)findViewById(R.id.spinner2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new eliminar().execute();
            }
        });
        new consulta().execute();
    }

    void actualiza2(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, correos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setSelection(0);
    }

    boolean validaemail(String correo){
        if (TextUtils.isEmpty(correo)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches();
        }
    }

    class consulta extends AsyncTask {
        MysqlConn consulta = new MysqlConn();
        String resultado = "";

        @Override

        protected String doInBackground(Object[] params) {
            try{
                consulta.Connect();
                consulta.Consult("SELECT * FROM responsable");
                if (consulta.rs.first()){
                    resultado = "Algo";
                    do{
                        ids.add(consulta.rs.getString("idresponsable"));
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
                new popup("No hay registros que eliminar.");
                findViewById(R.id.fab).setEnabled(false);
            }else if(resultado.equals("Algo")){
                actualiza2();
                findViewById(R.id.fab).setEnabled(true);
            }else{
                new popup(resultado);
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
        popup(String texto){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EliminaResp.this);
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
