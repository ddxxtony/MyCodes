package com.example.ellui.interfaz2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class modificar_medic2 extends AppCompatActivity {

    private Spinner Toma_sp1,Toma_sp2;
    public static int minutoP,horaP;
    public static String fechaP,fechaTermino;
    private EditText etinterval1,etinterval2;
    private Button BTAdd;
    private String etname,etdosis;
    private String RG;
    public  int idMedicamento;
    public static int hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_medic2);
        new modificar_medic2.LlenaCampos().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent= getIntent();
        Bundle extras= intent.getExtras();
        if(extras!=null){
            etname=extras.getString("nombre");
            etdosis=extras.getString("Dosis");
            RG=extras.getString("Tubo");
          idMedicamento=extras.getInt("id");
        }
        addListenerOnTomasp1();
        addLstenerOnBTAgregar();



    }


   public void addFechayhorap(){

       TextView tvFecha =(TextView) findViewById(R.id.TVFecha);
       String DTToma=tvFecha.getText().toString();
       String DateToma []=DTToma.split("-");
       int year = Integer.parseInt(DateToma[2].replace(" ",""));
       int month = Integer.parseInt(DateToma[1].replace(" ",""))-1;
       int day = Integer.parseInt(DateToma[0].replace(" ",""));
       modificar_medic2.fechaP=year+"-"+month+"-"+day;

       TextView T=(TextView) findViewById(R.id.TVHora);
       String DToma =T.getText().toString();

       String TimeToma []=DToma.split(":");
       String hora[]=TimeToma[1].split(" ");
       int hour = Integer.parseInt(hora[1]);
       int minute = Integer.parseInt(TimeToma[2].replace(" ",""));
       modificar_medic2.horaP=hour;
       modificar_medic2.minutoP=minute;


   }





    public void addLstenerOnBTAgregar(){

        BTAdd=(Button) findViewById(R.id.BTAdd);
        BTAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modificar_medic2.fechaP==null){
                    addFechayhorap();
                }
                // Intent nForm = new Intent(Add_Medic.this, add_medic2.class);
                //nForm.putExtra("Dosis",etdosis.getText().toString());
                // nForm.putExtra("nombre",etname.getText().toString());
                //String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                //nForm.putExtra("Tubo",RGSelection);
                //startActivity(nForm);
                //finish();
                new modificar_medic2.Registra_medicamento().execute(etname,etdosis,RG,idMedicamento);



            }
        });
    }



    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragmentModific();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragmentModif();
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(modificar_medic2.this,android.R.layout.simple_spinner_item, list);
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
        private String TransaccionesMedicament;
        private int duracion;
        private int intervalo;
        private int intervalo1,intervalo2;
        private String sp1,sp2;
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
            sp1=String.valueOf(Toma_sp1.getSelectedItem());
            sp2=String.valueOf(Toma_sp2.getSelectedItem());
            DatePicker myDatePicker = (DatePicker) findViewById(R.id.datePicker);
            add_medic2.fechaTermino=myDatePicker.getYear()+"-"+myDatePicker.getMonth()+"-"+myDatePicker.getDayOfMonth();

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
                Toast.makeText(getBaseContext(), "Verifique su conexion "+ TransaccionesMedicament,
                        Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(), "EL Medicamento se modifico con exito",
                        Toast.LENGTH_LONG).show();
            }
             Intent nForm = new Intent(modificar_medic2.this, MainActivity.class);
            //nForm.putExtra("ID",String.valueOf(ID));
            //nForm.putExtra("nombre",nombre);
            startActivity(nForm);
            finish();

            //   Toast.makeText(MainActivity.this, "No se encontro su ID en la Base de datos",
            //         Toast.LENGTH_LONG).show();

        }

        @Override
        protected Object doInBackground(Object[] params) {

            error=false;
            EBDCon= new MysqlConn();
            // new Registra_medicamento().execute(etname,etdosis,RG,id);
            try{

                 TransaccionesMedicament="update medicamento set " +
                        "nombre='"+(String)params[0]+"'" +
                        " ,dosis="+(String)params[1]
                        +",colordg='"+(String)params[2]
                        +"',intervalo="+intervalo
                        +",duraciontx='"+add_medic2.fechaTermino +" 00:00:00'"
                        + ",fechayhorado='"+ modificar_medic2.fechaP+" "+modificar_medic2.horaP+":"+modificar_medic2.minutoP+":00'"
                        +",i1="+intervalo1
                        +",i2="+intervalo2
                        +",paciente_idpaciente=1 " +
                         ",s1='"+sp1+"' ,"+
                         "s2='"+sp2+" '"+
                         " where idmedicamento="+idMedicamento+";";
                Log.d(" hola mundo", TransaccionesMedicament);
                EBDCon.Connect();
                EBDCon.Update(TransaccionesMedicament);
                EBDCon.desConnect();
            }
            catch(Exception e){
                error=true;

            }
            return null;
        }
    }

    class LlenaCampos extends AsyncTask {

        private EditText etinterval1,etinterval2;
        private Spinner  Toma_sp1,Toma_sp2;
        private TimePicker horainicio;
        private DatePicker fechaInicio,fechaFin;
        private Boolean error;
        private String sp1,sp2;
        private int duracion,yy1,dd1,mm1,yy2,dd2,mm2,hh,min,intervalo1,intervalo2;
        DatePicker myDatePicker ;
        String Duracion,fechaiyhorai;

        MysqlConn EBDCon;/// Base de datos externa Mysql

        @Override
        protected void  onPreExecute(){



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
                Toast.makeText(getBaseContext(), "Error ",
                        Toast.LENGTH_LONG).show();
            }else{

                etinterval1=(EditText) findViewById(R.id.ETIntervalo1);
                etinterval2=(EditText) findViewById(R.id.ETIntervalo2);
                Toma_sp1 = (Spinner) findViewById(R.id.Toma_sp1);
                Toma_sp2 = (Spinner) findViewById(R.id.Toma_sp2);


                String DurationDate []=Duracion.split(" ");
                String dateparts []=DurationDate[0].split("-");
                yy1=Integer.parseInt(dateparts[0]);
                mm1=Integer.parseInt(dateparts[1]);
                dd1=Integer.parseInt(dateparts[2]);
                DatePicker myDatePicker = (DatePicker) findViewById(R.id.datePicker);
                myDatePicker.init(yy1, mm1-1, dd1, null);
                etinterval1.setText(intervalo1+"");
                etinterval2.setText(intervalo2+"");

                Toma_sp1.setSelection(GetPos1(sp1));
                Toma_sp2.setSelection(GetPos2(sp2));

                String DTToma []=fechaiyhorai.split(" ");
                String TimeToma []=DTToma[1].split(":");
                String DateToma []=DTToma[0].split("-");
                hh=Integer.parseInt(TimeToma[0]);
                min=Integer.parseInt(TimeToma[1]);

                yy2=Integer.parseInt(DateToma[0]);
                mm2=Integer.parseInt(DateToma[1]);
                dd2=Integer.parseInt(DateToma[2]);

                TextView tvFecha =(TextView) findViewById(R.id.TVFecha);
                tvFecha.setText(""+dd2+"-"+ mm2+"-"+yy2);

                TextView tvHora=(TextView) findViewById(R.id.TVHora);
                tvHora.setText("Hora: "+hh+" Minuto: "+min);

                //  transaccionHorario="insert into horario values (null,'"+Add_Medic.fechaP+" "+Add_Medic.horaP+":+"+Add_Medic.minutoP+":00')";
                Toast.makeText(getBaseContext(), "Exito",
                        Toast.LENGTH_LONG).show();
            }

        }

        public int GetPos1(String p){
                switch (p){
                    case "Hora(s)":
                        return 0;

                    case  "Dia(s)":
                        return 1;

                    case "Semana(s)":
                        return 2;

                    case "Mes(es)":
                        return 3;
                    default:
                        return 0;



            }
        }

        public int GetPos2(String p) {
            switch (p){
                case "Minuto(s)":
                    return 0;

                case "Hora(s)":
                    return 0;

                case  "Dia(s)":
                    if(sp1.equals("Semana(s)")){
                        return 0;
                    }else{
                        return 1;

                    }

                case "Semana(s)":
                    return 0;

                default:
                    return 0;


            }

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
                    intervalo1= Integer.parseInt(EBDCon.rs.getString(8).toString());
                    intervalo2=Integer.parseInt( EBDCon.rs.getString(9).toString());
                    Duracion=EBDCon.rs.getString(6).toString();
                    fechaiyhorai=EBDCon.rs.getString(7).toString();
                    sp1=EBDCon.rs.getString(11).toString();
                    sp2=EBDCon.rs.getString(12).toString();
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
