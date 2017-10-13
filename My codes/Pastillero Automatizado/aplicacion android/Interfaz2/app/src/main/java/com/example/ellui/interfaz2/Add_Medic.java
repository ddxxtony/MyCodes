package com.example.ellui.interfaz2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Add_Medic extends AppCompatActivity {

    private MysqlConn EBDCon;


    private TimePicker horainicio;
    private TextView TVCon1,TVCon2;
    private Button BANext;
    private EditText etname,etdosis;
    private RadioGroup RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__medic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addLstenerOnBTSiguiente();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void  addLstenerOnBTSiguiente(){
        BANext=(Button) findViewById(R.id.BTAdd);
        etname=(EditText) findViewById(R.id.ETName);
        etdosis=(EditText) findViewById(R.id.ETDosis);
        RG=(RadioGroup) findViewById(R.id.RG);
        BANext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent nForm = new Intent(Add_Medic.this, add_medic2.class);
                nForm.putExtra("Dosis",etdosis.getText().toString());
                nForm.putExtra("nombre",etname.getText().toString());
                String RGSelection = ((RadioButton) RG.findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                nForm.putExtra("Tubo",RGSelection);
                startActivity(nForm);
                finish();
                // new Registra_medicamento().execute();

              //  new Verificar_id_bde().execute(Integer.parseInt(IDuser.getText().toString()),LBDCon);

            }
        });
    }





}
