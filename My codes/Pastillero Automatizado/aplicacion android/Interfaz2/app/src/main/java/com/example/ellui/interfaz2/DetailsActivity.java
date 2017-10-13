package com.example.ellui.interfaz2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.content.Intent;

public class DetailsActivity extends ActionBarActivity {

    private Button BTE;
    public static int idm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String title = getIntent().getStringExtra("title");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
       String bitmapp = getIntent().getStringExtra("image");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pasti_5);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        EditText tvnombre = (EditText ) findViewById(R.id.NombreM);
        EditText tvdosis2 = ( EditText) findViewById(R.id.DosisM);
        EditText tvcolor = ( EditText) findViewById(R.id.ColorM);
        EditText tvfechaTErmino = ( EditText) findViewById(R.id.FechaTermnoM);
        EditText tvSigFecha = ( EditText) findViewById(R.id.FechaSigM);
        EditText tvIntervalo = ( EditText) findViewById(R.id.intervalom);
        tvnombre.setFocusable(false);
        tvdosis2.setFocusable(false);
        tvcolor.setFocusable(false);
        tvfechaTErmino.setFocusable(false);
        tvSigFecha.setFocusable(false);
        tvIntervalo.setFocusable(false);
//        titleTextView.setFocusable(false);
//        titleTextView.setText(title);
         idm = extras.getInt("id");
        ImageView imageView = (ImageView) findViewById(R.id.imageDetails);
        int resId = getResources().getIdentifier(bitmapp, "drawable", getPackageName());
        imageView.setImageResource(resId);

          // imageView.setImageBitmap(bitmap);

        new Consulta_Medicamento().execute(idm);
        add_listenerBorrar();
        add_listenerEditar();
    }

    public void add_listenerBorrar(){
        BTE=(Button) findViewById(R.id.BTBorrar);
        BTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent nForm = new Intent(Add_Medic.this, add_medic2.class);
                //nForm.putExtra("Dosis",etdosis.getText().toString());
                // nForm.putExtra("nombre",etname.getText().toString());
                //String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                //nForm.putExtra("Tubo",RGSelection);
                //startActivity(nForm);
                //finish();
                new popup().popup2("Esta seguro de eliminar el registro?", "Completamente?");
           //     new Borra_Medicamento().execute(id);



            }
        });
    }

    public void add_listenerEditar(){
        BTE=(Button) findViewById(R.id.BTEditar);
        BTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent nForm = new Intent(DetailsActivity.this, Modificar_Medic.class);
                nForm.putExtra("id",idm);
                // nForm.putExtra("nombre",etname.getText().toString());
                //String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                //nForm.putExtra("Tubo",RGSelection);
                startActivity(nForm);
                finish();


                //  new Verificar_id_bde().execute(Integer.parseInt(IDuser.getText().toString()),LBDCon);

            }
        });
    }
    class Consulta_Medicamento extends AsyncTask {


        private String nombre, dosis, color, i1, i2, FechaHasta, FechaSIG, HoraSig;
        private Boolean error;
        String transa;
        MysqlConn EBDCon;/// Base de datos externa Mysql

        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (error) {
               // Toast.makeText(getBaseContext(), "Error " + transa,
                 //       Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(getBaseContext(), "Exito",
                  //      Toast.LENGTH_LONG).show();

                EditText tvnombre = (EditText ) findViewById(R.id.NombreM);
                EditText tvdosis2 = ( EditText) findViewById(R.id.DosisM);
                EditText tvcolor = ( EditText) findViewById(R.id.ColorM);
                EditText tvfechaTErmino = ( EditText) findViewById(R.id.FechaTermnoM);
                EditText tvSigFecha = ( EditText) findViewById(R.id.FechaSigM);
                EditText tvIntervalo = ( EditText) findViewById(R.id.intervalom);
                tvnombre.setText(nombre);
                tvdosis2.setText(dosis);
                tvcolor.setText(color);
                tvfechaTErmino.setText(FechaHasta);
                TextView FYHsig = (TextView) findViewById(R.id.FechaSigM);
                FYHsig.setText("Sera el " + FechaSIG + "a  las " + HoraSig);
                tvIntervalo.setText("Cada " + GetPos1(Integer.parseInt(i1)) + " con " + GetPos2(Integer.parseInt(i1), Integer.parseInt(i2)) + "  ");

            }
        }

        public String GetPos1(int intervalo) {
            float intervaloF = intervalo;
            if (intervalo % 60 == 0) {
                if (intervalo > 1) {
                    return "Horas";
                } else {
                    return "Hora";
                }

            } else if (intervalo % 1440 == 0) {
                if (intervalo > 1) {
                    return "Dias";
                } else {
                    return "Dia";
                }
            } else if (intervalo % 10080 == 0) {
                if (intervalo > 1) {
                    return "Semanas";
                } else {
                    return "Semana";
                }
            } else if (intervalo % 43200 == 0) {
                if (intervalo > 1) {
                    return "Mes";
                } else {
                    return "Meses";
                }
            } else if (intervalo > 1) {
                return "";
            } else {
                return "";
            }
        }

        //  Toma_sp1.setSelection(GetPos1(intervalo1));
        //     Toma_sp2.setSelection(GetPos2(intervalo2));

        public String GetPos2(int intervalo1, int intervalo2) {
            if (GetPos1(intervalo1).equals("Hora") || GetPos1(intervalo1).equals("Hora")) {
                if (intervalo2 == 1) {
                    return " Minuto";
                } else if (intervalo2 == 0) {
                    return "";
                } else {
                    return "Minutos";
                }

            } else if (GetPos1(intervalo1).equals("Dia") || GetPos1(intervalo1).equals("Dias")) {
                if (intervalo2 == 1) {
                    return "Hora";
                } else if (intervalo2 == 0) {
                    return "";
                } else {
                    return "Horas";
                }

            } else if (GetPos1(intervalo1).equals("Semana") || GetPos1(intervalo1).equals("Semanas")) {
                if (intervalo2 == 1) {
                    return " Dia";
                } else if (intervalo2 == 0) {
                    return "";
                } else {
                    return "Dias";
                }

            } else if (GetPos1(intervalo1).equals("Mes") || GetPos1(intervalo1).equals("Meses")) {

                if (intervalo2 % 10080 == 0) {
                    if (intervalo2 == 1) {
                        return " Semana";
                    } else if (intervalo2 == 0) {
                        return "";
                    } else {
                        return "Semanas";
                    }

                } else {
                    if (intervalo2 == 1) {
                        return " Dia";
                    } else if (intervalo2 == 0) {
                        return "";
                    } else {
                        return "Dias";
                    }

                }

            }
            return " ";
        }


        @Override
        protected Object doInBackground(Object[] params) {
            //(int) params[0];
            error = false;
            transa = "select idmedicamento,nombre,dosis,colordg,DATE_FORMAT(duraciontx,'%d-%m-%Y') as duracion,i1,i2," +
                    "  DATE_FORMAT(fechayhorado, '%d-%m-%Y') DATEONLY, DATE_FORMAT(fechayhorado,'%H:%i') TIMEONLY" +
                    " from medicamento where idmedicamento=" + (int) params[0] + ";";
            EBDCon = new MysqlConn();
            // new Registra_medicamento().execute(etname,etdosis,RG);
            try {

                EBDCon.Connect();


                //Log.d("dsfs",transa);
                EBDCon.Consult(transa);
                EBDCon.rs.first();
                if (EBDCon.rs != null) {
                    nombre = EBDCon.rs.getString("nombre").toString();
                    dosis = EBDCon.rs.getString("dosis").toString();
                    color = EBDCon.rs.getString("colordg").toString();
                    FechaHasta = EBDCon.rs.getString("duracion").toString();
                    i1 = EBDCon.rs.getString("i1");
                    i2 = EBDCon.rs.getString("i2");
                    FechaSIG = EBDCon.rs.getString("DATEONLY");
                    HoraSig = EBDCon.rs.getString("TIMEONLY");

                }

                EBDCon.desConnect();
            } catch (Exception e) {
                error = true;

            }
            return null;
        }
    }


    class Borra_Medicamento extends AsyncTask {


        private Boolean error;
        MysqlConn EBDCon;/// Base de datos externa Mysql

        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (error) {
             //   Toast.makeText(getBaseContext(), "Error",
               //         Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(getBaseContext(), "Exito",
                  //      Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                //Start details activity
                //Start details activity
                startActivity(intent);
                finish();


            }
        }


        @Override
        protected Object doInBackground(Object[] params) {
            //(int) params[0];
            error = false;
            EBDCon = new MysqlConn();
            // new Registra_medicamento().execute(etname,etdosis,RG);
            try {

                EBDCon.Connect();


                //Log.d("dsfs",transa);
                EBDCon.Update("delete from medicamento where idmedicamento="+idm);

                EBDCon.desConnect();
            } catch (Exception e) {
                error = true;

            }
            return null;
        }
    }



    class popup{
        popup(){

        }

        public void popup2(String texto, final String texto2){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(DetailsActivity.this);
            builder1.setMessage(texto);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Sí",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(DetailsActivity.this);
                            builder1.setMessage(texto2);
                            builder1.setCancelable(true);
                            builder1.setPositiveButton(
                                    "Sí",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            new Borra_Medicamento().execute(id);
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
