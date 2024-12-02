package com.example.pmapresecan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInfoFragment extends Fragment {

    private AutoCompleteTextView actePredmet;
    private Spinner etProfesor;
    private TextInputEditText etPred;
    private TextInputEditText etLV;
    private SwitchMaterial switchBool;
    private SharedViewModel sharedViewModel;
    private final List<Profesor> profesori = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

        actePredmet = view.findViewById(R.id.acetPredmet);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_dropdown_item_1line, Predmeti);
        actePredmet.setAdapter(adapter);

        etPred = view.findViewById(R.id.etPred);
        etLV = view.findViewById(R.id.etLV);
        switchBool = view.findViewById(R.id.switchBool);

        etProfesor = view.findViewById(R.id.etProfesor);

        arrayAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<String>()
        );

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etProfesor.setAdapter(arrayAdapter);
        fetchProfesori();

        actePredmet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sharedViewModel.setPredmet(editable.toString());
            }
        });

        etProfesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0 && i < profesori.size()) {
                    Profesor selectedProfesor = profesori.get(i);
                    sharedViewModel.setProfesor(selectedProfesor.getIme() + " " + selectedProfesor.getPrezime());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etPred.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (!input.isEmpty()) {
                    try {
                        int value = Integer.parseInt(input);
                        if (value >= 0) {
                            sharedViewModel.setSatipred(value);
                        } else {
                            Toast.makeText(getContext(), getString(R.string.toast_broj), Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), getString(R.string.toast_broj), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    sharedViewModel.setSatipred(0);
                }
            }
        });

        etLV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (!input.isEmpty()) {
                    try {
                        int value = Integer.parseInt(input);
                        if (value >= 0) {
                            sharedViewModel.setSatilv(value);
                        } else {
                            Toast.makeText(getContext(), getString(R.string.toast_broj), Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), getString(R.string.toast_broj), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    sharedViewModel.setSatilv(0);
                }
            }
        });

        switchBool.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedViewModel.setTip(getString(R.string.label_izborni));
            } else {
                sharedViewModel.setTip(getString(R.string.label_obavezni));
            }
        });

        return view;
    }

    private static final String[] Predmeti = new String[] {
            "Osnove programiranja", "Web programiranje na strani poslužitelja", "Objektno orijentirano programiranje", ".NET programiranje", "Programiranje mobilnih aplikacija"
    };

    private void fetchProfesori() {
        RetrofitClient.getRetrofitInstance().getProfesori().enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.isSuccessful() && response.body() != null){
                    profesori.addAll(response.body());

                    for(Profesor profesor : profesori){
                        arrayAdapter.add(profesor.getIme() + " " + profesor.getPrezime());
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT);
                Log.e("TAG", "Error: " + t.getMessage());
            }
        });
    }
}