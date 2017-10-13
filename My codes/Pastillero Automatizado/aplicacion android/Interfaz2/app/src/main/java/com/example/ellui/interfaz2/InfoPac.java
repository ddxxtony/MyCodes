package com.example.ellui.interfaz2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by fer_m on 04/06/2017.
 */

public class InfoPac extends AppCompatActivity{
    EditText nombre;
    EditText calle;
    EditText numero;
    EditText colonia;
    Boolean bandera = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopac);
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
                Intent docreg = new Intent(getApplicationContext(), EditaPac.class);
                startActivity(docreg);
            }
        });
    }

    void actualiza2(String nom, String cal, String num, String col){
        nombre.setText(nom);
        calle.setText(cal);
        numero.setText(num);
        colonia.setText(col);
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
                new popup("No hay registros.");
            }else if(resultado.equals("Algo")){
                actualiza2(nom, cal, num, col);
            }else{
                new popup(resultado);
            }
        }
    }

    class popup{
        popup(String texto){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(InfoPac.this);
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
