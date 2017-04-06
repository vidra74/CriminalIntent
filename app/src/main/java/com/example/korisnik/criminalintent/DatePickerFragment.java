package com.example.korisnik.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Frano on 02.03.2017.
 */

public class DatePickerFragment extends DialogFragment {
    private Date mCrimeDate;
    public static final String EXTRA_DATE = "package com.example.korisnik.criminalintent.crime_date";

    public static DatePickerFragment newInstance(Date dateCrime){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, dateCrime);

        DatePickerFragment dialog = new DatePickerFragment();
        dialog.setArguments(args);
        return dialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCrimeDate = (Date)getArguments().getSerializable("EXTRA_DATE");

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);

        datePicker.init(2016, 11, 22, new DatePicker.OnDateChangedListener(){

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCrimeDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE, mCrimeDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                    .setView(v)
                    .setTitle(R.string.date_picker_title)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendResult(Activity.RESULT_OK);
                        }
                    })
                    .create();
    }

    private void sendResult(int resultCode){

        if (getTargetFragment() == null)
            return;

        Intent data = new Intent();
        data.putExtra(EXTRA_DATE, mCrimeDate);

        getTargetFragment().onActivityResult(CrimeFragment.REQUEST_DATE, resultCode, data);
    }
}
