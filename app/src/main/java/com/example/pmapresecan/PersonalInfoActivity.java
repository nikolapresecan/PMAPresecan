package com.example.pmapresecan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PersonalInfoActivity extends AppCompatActivity {

    private TextInputEditText etIme;
    private Button btnOtvori;
    private String ime;
    private TextInputEditText etDatum;
    private String datum;
    private TextInputEditText etPrezime;
    private String prezime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        etIme = findViewById(R.id.etIme);
        etPrezime = findViewById(R.id.etPrezime);
        btnOtvori = findViewById(R.id.btnOtvoriStudent);
        etDatum = findViewById(R.id.etDatum);

        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText(getString(R.string.label_datumrod))
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

                datePicker.show(getSupportFragmentManager(), "DATE_PICKER");

                datePicker.addOnPositiveButtonClickListener(selection -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.", Locale.getDefault());
                    String formattedDate = sdf.format(selection);
                    etDatum.setText(formattedDate);
                });
            }
        });

        btnOtvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ime = etIme.getText().toString();
                prezime = etPrezime.getText().toString();
                datum = etDatum.getText().toString();
                if(!ime.isEmpty() && !prezime.isEmpty() && !datum.isEmpty()){
                    Intent personalIntent = new Intent(getApplicationContext(), StudentInfoActivity.class);
                    personalIntent.putExtra("ime", ime);
                    personalIntent.putExtra("prezime", prezime);
                    personalIntent.putExtra("datum", datum);
                    startActivity(personalIntent);
                }
                else {
                    Toast.makeText(PersonalInfoActivity.this, "Popunite sva polja za unos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Vraćanje unatrag nije dopušteno", Toast.LENGTH_SHORT).show();
    }
}