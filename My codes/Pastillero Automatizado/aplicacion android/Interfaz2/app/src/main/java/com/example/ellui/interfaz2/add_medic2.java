package com.example.ellui.interfaz2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class add_medic2 extends AppCompatActivity {

    private Spinner Toma_sp1,Toma_sp2;
    public static int minutoP,horaP;
    public static String fechaP,fechaTermino;
    private EditText etinterval1,etinterval2;
    private Button BTAdd;
    private String etname,etdosis;
    private String RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medic2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent= getIntent();
        Bundle extras= intent.getExtras();
        if(extras!=null){
            etname=extras.getString("nombre");
            etdosis=extras.getString("Dosis");
            RG=extras.getString("Tubo");
        }
        setSupportActionBar(toolbar);
        addListenerOnTomasp1();
        addLstenerOnBTAgregar();

    }

public void addLstenerOnBTAgregar(){

    BTAdd=(Button) findViewById(R.id.BTAdd);
    BTAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // Intent nForm = new Intent(Add_Medic.this, add_medic2.class);
            //nForm.putExtra("Dosis",etdosis.getText().toString());
           // nForm.putExtra("nombre",etname.getText().toString());
            //String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();
            //nForm.putExtra("Tubo",RGSelection);
            //startActivity(nForm);
            //finish();
            new Registra_medicamento().execute(etname,etdosis,RG);

            //  new Verificar_id_bde().execute(Integer.parseInt(IDuser.getText().toString()),LBDCon);

        }
    });
}



    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void  addListenerOnTomasp1(){
        Toma_sp1 = (Spinner) findViewById(R.id.Toma_sp1);
        Toma_sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toma_sp2 = (Spinner) findViewById(R.id.Toma_sp2);
                etinterval2=(EditText) findViewById(R.id.ETIntervalo2);
                List<String> list = new ArrayList<String>();
                switch(parent.getItemAtPosition(position).toString()){
                    case "Hora(s)":
                        list.add("Minuto(s)");
                        break;
                    case  "Dia(s)":
                        list.add("Hora(s)");
                        break;
                    case "Semana(s)":
                        list.add("Dia(s)");
                        break;
                    case "Mes(es)":
                        list.add("Semana(s)");
                        list.add("Dia(s)");
                        break;
                    default:break;

                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(add_medic2.this,android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Toma_sp2.setAdapter(dataAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    class Registra_medicamento extends AsyncTask {

        private EditText etinterval1,etinterval2;
        private Spinner  Toma_sp1,Toma_sp2;
        private TimePicker horainicio;
        private DatePicker fechaInicio,fechaFin;
        private Boolean error;
        private String TransaccionesMedicamento;
        private int duracion;
        private int intervalo;
        private int intervalo1,intervalo2;
        private String spin1,spin2;
        DatePicker myDatePicker ;

        MysqlConn EBDCon;/// Base de datos externa Mysql

        @Override
        protected void  onPreExecute(){

            etinterval1=(EditText) findViewById(R.id.ETIntervalo1);
            etinterval2=(EditText) findViewById(R.id.ETIntervalo2);
            Toma_sp1 = (Spinner) findViewById(R.id.Toma_sp1);
            Toma_sp2 = (Spinner) findViewById(R.id.Toma_sp2);
             intervalo=(Integer.parseInt(etinterval1.getText().toString())*(evaluaTime(String.valueOf(Toma_sp1.getSelectedItem()))))+
                    (Integer.parseInt(etinterval2.getText().toString())*(evaluaTime(String.valueOf(Toma_sp2.getSelectedItem()))));
            intervalo1=Integer.parseInt(etinterval1.getText().toString());
            intervalo2=(Integer.parseInt(etinterval2.getText().toString()));

            DatePicker myDatePicker = (DatePicker) findViewById(R.id.datePicker);
            add_medic2.fechaTermino=myDatePicker.getYear()+"-"+myDatePicker.getMonth()+"-"+myDatePicker.getDayOfMonth();
            spin1=String.valueOf(Toma_sp1.getSelectedItem());
            spin2=String.valueOf(Toma_sp2.getSelectedItem());
          //  transaccionHorario="insert into horario values (null,'"+Add_Medic.fechaP+" "+Add_Medic.horaP+":+"+Add_Medic.minutoP+":00')";

        }

        //Convierte una unidad de tiempo a minutos
        public int evaluaTime(String Exp){
            switch (Exp){
                case "Minuto(s)":
                    return 1;

                case "Hora(s)":
                    return 60;

                case  "Dia(s)":
                    return 1440;

                case "Semana(s)":
                    return 10080;

                case "Mes(es)":
                    return 43200;

                case "Año(s)":
                    return 525600;

                case "De por vida":
                    return 80000000;
                default:
                    return 0;


            }

        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(error){
                Toast.makeText(getBaseContext(), "Error"+TransaccionesMedicamento,
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), "Medicamento Agregado",
                        Toast.LENGTH_SHORT).show();
                Intent nForm = new Intent(add_medic2.this, MainActivity.class);
                startActivity(nForm);
                finish();
            }
            //  Intent nForm = new Intent(MainActivity.this, Main2Activity.class);
            //nForm.putExtra("ID",String.valueOf(ID));
            //nForm.putExtra("nombre",nombre);
            //startActivity(nForm);
            //finish();

            //   Toast.makeText(MainActivity.this, "No se encontro su ID en la Base de datos",
            //         Toast.LENGTH_LONG).show();

        }

        @Override
        protected Object doInBackground(Object[] params) {

            error=false;
            EBDCon= new MysqlConn();
           // new Registra_medicamento().execute(etname,etdosis,RG);
            try{

                TransaccionesMedicamento="insert into medicamento values (null,'"+(String)params[0]+"' ,"
                        +(String)params[1]+" ,'"+(String)params[2]+"',"+intervalo+",'"+
                        add_medic2.fechaTermino +" 00:00:00','"+
                        add_medic2.fechaP+" "+add_medic2.horaP+":"+add_medic2.minutoP+":00',"+intervalo1+","+intervalo2+","+1+",'"+spin1 +"','"+spin2+"' );";
                EBDCon.Connect();
                EBDCon.Update(TransaccionesMedicamento);
                EBDCon.desConnect();
            }
            catch(Exception e){
                error=true;

            }
            return null;
        }
    }



}
