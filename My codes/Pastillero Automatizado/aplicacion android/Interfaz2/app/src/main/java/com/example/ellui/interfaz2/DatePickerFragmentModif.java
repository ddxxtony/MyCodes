package com.example.ellui.interfaz2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by ellui on 31/05/2017.
 */

public class DatePickerFragmentModif extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        TextView tvFecha =(TextView) getActivity().findViewById(R.id.TVFecha);
        String DTToma=tvFecha.getText().toString();
        String DateToma []=DTToma.split("-");
        final Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(DateToma[2].replace(" ",""));
        int month = Integer.parseInt(DateToma[1].replace(" ",""))-1;
        int day = Integer.parseInt(DateToma[0].replace(" ",""));
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView tv1=(TextView) getActivity().findViewById(R.id.TVFecha);
        tv1.setText(day+"-"+month+"-"+year);
        modificar_medic2.fechaP=year+"-"+month+"-"+day;

    }
}