package com.example.pmapresecan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    private AutoCompleteTextView actePredmet;
    private Button btnOtvori;
    private String predmet;
    private TextInputEditText etProfesor;
    private String profesor;
    private TextInputEditText etPred;
    private int predavanja;
    private TextInputEditText etLV;
    private int lv;
    private SwitchMaterial switchBool;
    private String bool;
    private String ime;
    private String prezime;
    private String datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Predmeti);
        actePredmet = findViewById(R.id.acetPredmet);
        actePredmet.setAdapter(adapter);

        etProfesor = findViewById(R.id.etProfesor);
        etPred = findViewById(R.id.etPred);
        etLV = findViewById(R.id.etLV);
        switchBool = findViewById(R.id.switchBool);

        btnOtvori = findViewById(R.id.btnOtvoriSummary);
        btnOtvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle extras = getIntent().getExtras();
                ime = extras.getString("ime");
                prezime = extras.getString("prezime");
                datum = extras.getString("datum");

                predmet = actePredmet.getText().toString();
                profesor = etProfesor.getText().toString();

                String pred = etPred.getText().toString();
                String lab = etLV.getText().toString();
                bool = switchBool.isChecked() ? "true" : "false";

                if(!predmet.isEmpty() && !profesor.isEmpty() && !pred.isEmpty() && !lab.isEmpty()){
                    try {
                        predavanja = Integer.parseInt(pred);
                        lv = Integer.parseInt(lab);

                        if (predavanja >= 0 && lv >= 0) {
                            Intent studentIntent = new Intent(getApplicationContext(), SummaryActivity.class);
                            studentIntent.putExtra("predmet", predmet);
                            studentIntent.putExtra("profesor", profesor);
                            studentIntent.putExtra("predavanja", predavanja);
                            studentIntent.putExtra("lv", lv);
                            studentIntent.putExtra("bool", bool);
                            studentIntent.putExtra("ime", ime);
                            studentIntent.putExtra("prezime", prezime);
                            studentIntent.putExtra("datum", datum);
                            startActivity(studentIntent);
                        }
                        else {
                            Toast.makeText(StudentInfoActivity.this, "Polja za broj predavanja i broj LV moraju biti veća ili jednaka 0", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (NumberFormatException ex){
                        Toast.makeText(StudentInfoActivity.this, "Unesite brojeve u polja za sati predavanja i sati LV", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(StudentInfoActivity.this, "Popunite sva polja za unos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static final String[] Predmeti = new String[] {
            "Osnove programiranja", "Web programiranje na strani poslužitelja", "Objektno orijentirano programiranje", ".NET programiranje", "Programiranje mobilnih aplikacija"
    };

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Vraćanje unatrag nije dopušteno", Toast.LENGTH_SHORT).show();
    }
}