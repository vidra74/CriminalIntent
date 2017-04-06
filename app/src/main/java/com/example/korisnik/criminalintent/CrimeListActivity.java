package com.example.korisnik.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Korisnik on 25.2.2017..
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
