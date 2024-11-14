package com.example.pmapresecan;

import java.util.ArrayList;
import java.util.List;

public class StudentSingleton {
    private static StudentSingleton oInstance = null;
    private List<Student> studenti = new ArrayList<>();

    protected StudentSingleton() {

    }

    public static StudentSingleton getInstance() {
        if(oInstance == null) {
            oInstance = new StudentSingleton();
        }
        return oInstance;
    }

    public List<Student> getStudenti(){
        return studenti;
    }

    public void setStudenti(Student student){
        studenti.add(student);
    }
}
