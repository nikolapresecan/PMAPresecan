package com.example.pmapresecan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> ime = new MutableLiveData<>();
    private MutableLiveData<String> prezime = new MutableLiveData<>();
    private MutableLiveData<String> datumrod = new MutableLiveData<>();
    private MutableLiveData<String> predmet = new MutableLiveData<>();
    private MutableLiveData<String> profesor = new MutableLiveData<>();
    private MutableLiveData<Integer> satipred = new MutableLiveData<>();
    private MutableLiveData<Integer> satilv = new MutableLiveData<>();
    private MutableLiveData<String> tip = new MutableLiveData<>();
    private final MutableLiveData<String> slika = new MutableLiveData<>();


    public LiveData<String> getIme() {
        return ime;
    }

    public void setIme(String Ime) {
        ime.setValue(Ime);
    }

    public LiveData<String> getPrezime() {
        return prezime;
    }

    public void setPrezime(String Prezime) {
        prezime.setValue(Prezime);
    }

    public LiveData<String> getDatumrod() {
        return datumrod;
    }

    public void setDatumrod(String Datumrod) {
        datumrod.setValue(Datumrod);
    }

    public LiveData<String> getPredmet() {
        return predmet;
    }

    public void setPredmet(String Predmet) {
        predmet.setValue(Predmet);
    }

    public LiveData<String> getProfesor() {
        return profesor;
    }

    public void setProfesor(String Profesor) {
        profesor.setValue(Profesor);
    }

    public LiveData<Integer> getSatipred() {
        return satipred;
    }

    public void setSatipred(Integer Satipred) {
        satipred.setValue(Satipred);
    }

    public LiveData<Integer> getSatilv() { return satilv; }

    public void setSatilv(Integer Satilv) {
        satilv.setValue(Satilv);
    }

    public LiveData<String> getTip() {
        return tip;
    }

    public void setTip(String Tip) {
        tip.setValue(Tip);
    }

    public void initTip(Context context) {
        if (tip.getValue() == null) {
            tip.setValue(context.getString(R.string.label_izborni));
        }
    }

    public LiveData<String> getSlika() { return slika; }

    public void setSlika(String Slika) { slika.setValue(Slika); }
}
