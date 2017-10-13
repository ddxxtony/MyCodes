package com.example.ellui.interfaz2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Modificar_Medic extends AppCompatActivity {
    private MysqlConn EBDCon;


    private TimePicker horainicio;
    private TextView TVCon1,TVCon2;
    private Button BANext;
    private EditText etname,etdosis;
    private RadioGroup RG;

    public  int idMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__medic);
        new Rellenar_campos().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addLstenerOnBTSiguiente();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent= getIntent();
        Bundle extras= intent.getExtras();
        if(extras!=null){
           idMedicamento=extras.getInt("id");
        }
       // idMedicamento=1;


    }



    public void  addLstenerOnBTSiguiente(){
        BANext=(Button) findViewById(R.id.BTAdd);
        etname=(EditText) findViewById(R.id.ETName);
        etdosis=(EditText) findViewById(R.id.ETDosis);
        RG=(RadioGroup) findViewById(R.id.RG);
        BANext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nForm = new Intent(Modificar_Medic.this, modificar_medic2.class);
                nForm.putExtra("Dosis",etdosis.getText().toString());
                nForm.putExtra("nombre",etname.getText().toString());
                nForm.putExtra("id",idMedicamento);
                String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                nForm.putExtra("Tubo",RGSelection);
                startActivity(nForm);
                finish();
                // new Registra_medicamento().execute();

                //  new Verificar_id_bde().execute(Integer.parseInt(IDuser.getText().toString()),LBDCon);

            }
        });
    }

    class Rellenar_campos extends AsyncTask {

        private String Nombre,Dosis;
        private Boolean error;
        private String tuboC;
        private Button BANext;
        private EditText etname,etdosis;
        private RadioGroup RG;


        MysqlConn EBDCon;/// Base de datos externa Mysql

        @Override
        protected void  onPreExecute(){


        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(error){
                Toast.makeText(getBaseContext(), "Error ",
                        Toast.LENGTH_LONG).show();



            }else{
                etname=(EditText) findViewById(R.id.ETName);
                etdosis=(EditText) findViewById(R.id.ETDosis);
                RG=(RadioGroup) findViewById(R.id.RG);
                etname.setText(Nombre);
                etdosis.setText(Dosis);
                RadioGroup RG = (RadioGroup) findViewById(R.id.RG);
                RG.check(Get_index(tuboC));
                Toast.makeText(getBaseContext(), "Exito",
                        Toast.LENGTH_LONG).show();
            }
        }

        //Get index of button
        public int Get_index(String color){

            RadioGroup RG = (RadioGroup) findViewById(R.id.RG);
            RadioButton b1=(RadioButton)findViewById(R.id.Red);
            RadioButton b2=(RadioButton)findViewById(R.id.Green);
            RadioButton b3=(RadioButton)findViewById(R.id.Blue);
            RadioButton b4=(RadioButton)findViewById(R.id.Yellow);
            if (b1.getText().toString().equals(color)){
                return b1.getId();

            } else if (b2.getText().toString().equals(color)){
                return b2.getId();

            }else if (b3.getText().toString().equals(color)){
                return b3.getId();

            }else if (b4.getText().toString().equals(color)){
                return b4.getId();

            }

            //String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();

            return -1;

        }

        @Override
        protected Object doInBackground(Object[] params) {

            error=false;
            EBDCon= new MysqlConn();
            // new Registra_medicamento().execute(etname,etdosis,RG);
            try{
                EBDCon.Connect();
                EBDCon.Consult("select * from medicamento where idmedicamento="+idMedicamento+";");
                EBDCon.rs.last();
                if(  EBDCon.rs!=null){
                    Nombre= EBDCon.rs.getString(2).toString();
                    Dosis= EBDCon.rs.getString(3).toString();
                    tuboC= EBDCon.rs.getString(4).toString();
                }
                EBDCon.desConnect();
            }
            catch(Exception e){
                error=true;

            }
            return null;
        }
    }






}
