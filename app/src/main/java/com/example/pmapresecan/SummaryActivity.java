package com.example.pmapresecan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    private TextView txtIme;
    private TextView txtPrezime;
    private TextView txtDatum;
    private TextView txtPredmet;
    private TextView txtProfesor;
    private TextView txtPred;
    private TextView txtLV;
    private TextView txtTip;
    private Button btnVrati;
    private String ime;
    private String prezime;
    private String datum;
    private String predmet;
    private String profesor;
    private String predavanja;
    private String lv;
    private String tip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        final Bundle extras = getIntent().getExtras();
        ime = extras.getString("ime");
        prezime = extras.getString("prezime");
        datum = extras.getString("datum");
        predmet = extras.getString("predmet");
        profesor = extras.getString("profesor");
        predavanja = String.valueOf(extras.getInt("predavanja"));
        lv = String.valueOf(extras.getInt("lv"));
        tip = extras.getString("bool");

        txtIme = findViewById(R.id.txtIme);
        txtPrezime = findViewById(R.id.txtPrezime);
        txtDatum = findViewById(R.id.txtDatum);
        txtPredmet = findViewById(R.id.txtPredmet);
        txtProfesor = findViewById(R.id.txtProfesor);
        txtPred = findViewById(R.id.txtPred);
        txtLV = findViewById(R.id.txtLV);
        txtTip = findViewById(R.id.txtTip);

        txtIme.setText(ime);
        txtPrezime.setText(prezime);
        txtDatum.setText(datum);
        txtPredmet.setText(predmet);
        txtProfesor.setText(profesor);
        txtPred.setText(predavanja);
        txtLV.setText(lv);

        if(tip.equals("true")){
            txtTip.setText(getString(R.string.label_izborni));
        }
        else {
            txtTip.setText(getString(R.string.label_obavezni));
        }

        btnVrati = findViewById(R.id.btnPocetak);
        btnVrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(ime, prezime, predmet);
                StudentSingleton.getInstance().setStudenti(student);

                Intent homeIntent = new Intent(SummaryActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Vraćanje unatrag nije dopušteno", Toast.LENGTH_SHORT).show();
    }
}