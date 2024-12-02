package com.example.pmapresecan;

public class Student {
    private String Ime;
    private String Prezime;
    private String Predmet;
    private String Slika;

    public Student() {
    }

    public Student(String ime, String prezime, String predmet) {
        Ime = ime;
        Prezime = prezime;
        Predmet = predmet;
    }

    public Student(String ime, String prezime, String predmet, String slika) {
        Ime = ime;
        Prezime = prezime;
        Predmet = predmet;
        Slika = slika;
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

    public String getSlika() {
        return Slika;
    }

    public void setSlika(String slika) {
        Slika = slika;
    }
}
