package com.example.pmapresecan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PersonalInfoFragment extends Fragment {

    private TextInputEditText etIme;
    private TextInputEditText etDatum;
    private TextInputEditText etPrezime;
    private SharedViewModel sharedViewModel;
    private ImageButton btnSlikaj;
    private ImageView imgSlika;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        etIme = view.findViewById(R.id.etIme);
        etPrezime = view.findViewById(R.id.etPrezime);
        etDatum = view.findViewById(R.id.etDatum);
        btnSlikaj = view.findViewById(R.id.btnSlikaj);
        imgSlika = view.findViewById(R.id.imgSlika);


        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText(getString(R.string.label_datumrod))
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

                datePicker.show(getParentFragmentManager(), "DATE_PICKER");

                datePicker.addOnPositiveButtonClickListener(selection -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.", Locale.getDefault());
                    String formattedDate = sdf.format(selection);
                    etDatum.setText(formattedDate);
                });
            }
        });

        etIme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sharedViewModel.setIme(editable.toString());
            }
        });

        etPrezime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sharedViewModel.setPrezime(editable.toString());
            }
        });

        etDatum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sharedViewModel.setDatumrod(editable.toString());
            }
        });

        btnSlikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CreateNewRecordActivity) requireActivity()).openCamera();
            }
        });

        sharedViewModel.getSlika().observe(getViewLifecycleOwner(), slika -> {
            if (slika != null) {
                Glide.with(requireContext())
                        .load(slika)
                        .into(imgSlika);
            }
        });

        return view;
    }
}