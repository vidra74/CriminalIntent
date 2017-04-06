package com.example.korisnik.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Korisnik on 25.2.2017..
 */

public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimeList;
    public static final String CLF_TAG = "com.example.korisnik.criminalintent.clf_tag";
    public static final int ACTION_CRIME = 1;

    private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter(ArrayList<Crime> ArrList){
            super(getActivity(), R.layout.list_item_crime, ArrList);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }
            Crime c = getItem(position);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.tvCrimeTitle);
            titleTextView.setText(c.getTitle());
            TextView dateTextView = (TextView)convertView.findViewById(R.id.tvCrimeDate);
            dateTextView.setText(c.getDateCrime().toString());
            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.cbSolved);
            solvedCheckBox.setChecked(c.isSolved());
            return convertView;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimeList = CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimeList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(CLF_TAG, c.getTitle());

        // Intent intentCrimeAct = new Intent(getActivity(), CrimeActivity.class);
        Intent intentCrimeAct = new Intent(getActivity(), CrimePagerActivity.class);
        intentCrimeAct.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.get_id());
        startActivityForResult(intentCrimeAct, ACTION_CRIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_CRIME){
            String tempStr = "Povrat iz fragmenta: ";
            if (data != null) {
                tempStr = tempStr + "intent " + data.getStringExtra(CrimeFragment.EXTRA_CRIME_RES);
            } else {
                tempStr = tempStr + "nema intenta";
            }

            Log.d(CLF_TAG,  tempStr);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
