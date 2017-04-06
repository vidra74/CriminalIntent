package com.example.korisnik.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Korisnik on 18.2.2017..
 */

public class Crime extends Object {

    private String m_Title;
    private UUID m_id;
    private boolean m_Solved;
    private Date mDateCrime;

    public Date getDateCrime() {
        return mDateCrime;
    }

    public void setDateCrime(Date mDateCrime) {
        this.mDateCrime = mDateCrime;
    }

    public boolean isSolved() {
        return m_Solved;
    }

    public void setSolved(boolean m_Solved) {
        this.m_Solved = m_Solved;
    }

    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String m_Title) {
        this.m_Title = m_Title;
    }

    public UUID get_id() {
        return m_id;
    }

    @Override
    public String toString() {
        return m_Title;
    }

    public Crime(){
        m_id = UUID.randomUUID();
        m_Solved = false;
        mDateCrime = new Date();
    }
}
