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
import android.view.View;

public class Decision extends AppCompatActivity {
    Intent docreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);
        new consulta().execute();
    }

    class consulta extends AsyncTask {
        MysqlConn consulta = new MysqlConn();
        String resultado = "";

        @Override

        protected String doInBackground(Object[] params) {
            try{
                consulta.Connect();
                consulta.Consult("SELECT * FROM paciente");
                if (consulta.rs.first()){
                    resultado = "Algo";
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
                docreg = new Intent(getApplicationContext(), RegistraPac.class);
                startActivity(docreg);
                finish();
            }else if(resultado.equals("Algo")){
                docreg = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(docreg);
                //new popup("Ya se ha registrado un paciente");
                finish();
                //new popup("Ya se ha registrado un paciente");
            }else{
                new popup(resultado);
            }
        }
    }

    class popup {
        popup(String texto) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Decision.this);
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
