package com.example.pmapresecan;

public class Profesor {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String kabinet;
    private String titula;
    private String akademsko_zvanje;
    private String profilna;

    public Profesor() {

    }

    public Profesor(int id, String ime, String prezime, String email, String kabinet, String titula, String akademsko_zvanje, String profilna) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kabinet = kabinet;
        this.titula = titula;
        this.akademsko_zvanje = akademsko_zvanje;
        this.profilna = profilna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKabinet() {
        return kabinet;
    }

    public void setKabinet(String kabinet) {
        this.kabinet = kabinet;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public String getAkademsko_zvanje() {
        return akademsko_zvanje;
    }

    public void setAkademsko_zvanje(String akademsko_zvanje) {
        this.akademsko_zvanje = akademsko_zvanje;
    }

    public String getProfilna() {
        return profilna;
    }

    public void setProfilna(String profilna) {
        this.profilna = profilna;
    }
}