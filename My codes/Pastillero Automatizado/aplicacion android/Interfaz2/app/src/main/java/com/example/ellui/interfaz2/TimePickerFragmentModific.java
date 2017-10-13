package com.example.ellui.interfaz2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by ellui on 31/05/2017.
 */

public class TimePickerFragmentModific extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        TextView T=(TextView) getActivity().findViewById(R.id.TVHora);

        String DTToma =T.getText().toString();
        String TimeToma []=DTToma.split(":");
        final Calendar c = Calendar.getInstance();
        String hora[]=TimeToma[1].split(" ");
        int hour = Integer.parseInt(hora[1]);
        int minute = Integer.parseInt(TimeToma[2].replace(" ",""));

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        TextView tv1=(TextView) getActivity().findViewById(R.id.TVHora);
        tv1.setText("Hora: "+view.getCurrentHour()+" Minuto: "+view.getCurrentMinute());
        modificar_medic2.horaP=view.getCurrentHour();
        modificar_medic2.minutoP=view.getCurrentMinute();

    }
}