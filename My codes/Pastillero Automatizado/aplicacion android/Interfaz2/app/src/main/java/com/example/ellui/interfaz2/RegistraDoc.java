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
import android.widget.EditText;

public class RegistraDoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_doc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombre = (EditText)findViewById(R.id.nombre);
                EditText correo = (EditText)findViewById(R.id.correo);

                if(nombre.getText().length() == 0 || correo.getText().length() == 0){
                    new popup("Campo(s) vacíos, complete los campos restantes.");
                }else{
                    if(validaemail(correo.getText().toString()) == true) {
                        new inserta().execute(nombre.getText(), correo.getText());
                    }else{
                        new popup("El formato de correo es incorrecto, ingréselo nuevamente. ");
                    }
                }
            }
        });
    }

    boolean validaemail(String correo){
        if (TextUtils.isEmpty(correo)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches();
        }
    }

    class inserta extends AsyncTask {
        MysqlConn registro = new MysqlConn();
        String nombre;
        String correo;
        String resultado = "";
        @Override

        protected String doInBackground(Object[] params) {
            nombre = params[0].toString();
            correo = params[1].toString();

            try {
                registro.Connect();
                int result = registro.Update("INSERT INTO responsable (nombre, correo, paciente_idpaciente) values('"+nombre+"','"+correo+"',1)");
                if(result > 0){
                    //new popup("Exito al insertar");
                    resultado = "Exito al insertar";
                }else{
                    resultado = "Error no se pudo insertar";
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
            if(resultado.equals("Exito al insertar")){
                //Insertar codigo para avanzar de ventana con un popup doble
                Intent docreg = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(docreg);
                finish();
            }
        }
    }

    class popup{
        popup(String texto){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegistraDoc.this);
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
