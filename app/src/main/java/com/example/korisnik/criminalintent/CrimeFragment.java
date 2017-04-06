package com.example.korisnik.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import java.text.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Korisnik on 18.2.2017..
 */

public class CrimeFragment extends Fragment {
    private Crime m_Crime;
    private EditText mTitleField;
    private CheckBox mSolved;
    private Button mDateSolved;

    public static final String EXTRA_CRIME_ID = "com.example.korisnik.criminalintent.UUID";
    public static final String EXTRA_CRIME_RES = "CrimeFragment_tag.Result";
    public static final String EXTRA_DATE = "CrimeFragment.Date";
    public static final int REQUEST_DATE = 0;

    public static CrimeFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, id);

        CrimeFragment cfg = new CrimeFragment();
        cfg.setArguments(args);

        return cfg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID id = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);

        m_Crime = CrimeLab.get(getActivity()).getCrime(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup parent,
                             Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(m_Crime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // zasad ostavi prazno
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                m_Crime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // zasad ostavi prazno
            }
        });

        mSolved = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolved.setChecked(m_Crime.isSolved());
        mSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                m_Crime.setSolved(isChecked);
            }
        });

        mDateSolved = (Button)v.findViewById(R.id.crime_date);
        updateDateText();
        mDateSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dpf = DatePickerFragment.newInstance(m_Crime.getDateCrime());
                dpf.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dpf.show(fm, EXTRA_DATE);
            }
        });

        return v;
    }

    public void returnResult(){
        Intent intentResult = new Intent();
        intentResult.putExtra(EXTRA_CRIME_RES, "Izašao na returnResult()");
        getActivity().setResult(Activity.RESULT_OK, intentResult);
    }

    @Override
    public void onStop() {
        super.onStop();
        returnResult();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_DATE){
            if (data == null)
                return;
            m_Crime.setDateCrime((Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE));
            updateDateText();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateDateText(){
        mDateSolved.setText(DateFormat.getDateInstance(DateFormat.FULL).format(m_Crime.getDateCrime()));
    }
}
