package com.example.pmapresecan;

public class Student {
    private String Ime;
    private String Prezime;
    private String Predmet;

    public Student() {
    }

    public Student(String ime, String prezime, String predmet) {
        Ime = ime;
        Prezime = prezime;
        Predmet = predmet;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getPredmet() {
        return Predmet;
    }

    public void setPredmet(String predmet) {
        Predmet = predmet;
    }
}
