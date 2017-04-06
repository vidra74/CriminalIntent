package com.example.korisnik.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Korisnik on 25.2.2017..
 */

public class CrimeLab extends Object {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context InContext){
        mAppContext = InContext;
        mCrimes = new ArrayList<Crime>();

        for(int i = 0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0);
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context InContext){
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(InContext);
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c: mCrimes) {
            if (c.get_id().equals(id))
                return c;
        }
        return null;
    }
}
