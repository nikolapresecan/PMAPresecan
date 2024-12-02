package com.example.pmapresecan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        List<Student> studenti = StudentSingleton.getInstance().getStudenti();
        recyclerView = findViewById(R.id.rvLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(studenti, this);
        recyclerView.setAdapter(studentAdapter);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pocIntent = new Intent(HomeActivity.this, CreateNewRecordActivity.class);
                startActivity(pocIntent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                setLocale("hr");
                break;
            case 2:
                setLocale("en");
                break;
            case 3:
                setLocale("hu");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setLocale(String code) {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        recreate();
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, getString(R.string.toast_nije_dopusteno), Toast.LENGTH_SHORT).show();
    }
}