package com.example.pmapresecan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryFragment extends Fragment {

    private TextView txtIme;
    private TextView txtPrezime;
    private TextView txtDatum;
    private TextView txtPredmet;
    private TextView txtProfesor;
    private TextView txtPred;
    private TextView txtLV;
    private TextView txtTip;
    private Button btnVrati;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.initTip(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        txtIme = view.findViewById(R.id.txtIme);
        txtPrezime = view.findViewById(R.id.txtPrezime);
        txtDatum = view.findViewById(R.id.txtDatum);
        txtPredmet = view.findViewById(R.id.txtPredmet);
        txtProfesor = view.findViewById(R.id.txtProfesor);
        txtPred = view.findViewById(R.id.txtPred);
        txtLV = view.findViewById(R.id.txtLV);
        txtTip = view.findViewById(R.id.txtTip);

        txtIme.setText("");
        sharedViewModel.getIme().observe(getViewLifecycleOwner(), novoIme -> txtIme.setText(novoIme));

        txtPrezime.setText("");
        sharedViewModel.getPrezime().observe(getViewLifecycleOwner(), novoPrez -> txtPrezime.setText(novoPrez));

        txtDatum.setText("");
        sharedViewModel.getDatumrod().observe(getViewLifecycleOwner(), novoDat -> txtDatum.setText(novoDat));

        txtPredmet.setText("");
        sharedViewModel.getPredmet().observe(getViewLifecycleOwner(), novoPred -> txtPredmet.setText(novoPred));

        txtProfesor.setText("");
        sharedViewModel.getProfesor().observe(getViewLifecycleOwner(), novoProf -> txtProfesor.setText(novoProf));

        txtPred.setText("");
        sharedViewModel.getSatipred().observe(getViewLifecycleOwner(), novoPred -> txtPred.setText(String.valueOf(novoPred)));

        txtLV.setText("");
        sharedViewModel.getSatilv().observe(getViewLifecycleOwner(), novoLv -> txtLV.setText(String.valueOf(novoLv)));

        txtTip.setText("");
        sharedViewModel.getTip().observe(getViewLifecycleOwner(), novoTip -> txtTip.setText(novoTip));

        btnVrati = view.findViewById(R.id.btnPocetak);
        btnVrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ime = sharedViewModel.getIme().getValue();
                String prezime = sharedViewModel.getPrezime().getValue();
                String datumRod = sharedViewModel.getDatumrod().getValue();
                String predmet = sharedViewModel.getPredmet().getValue();
                String profesor = sharedViewModel.getProfesor().getValue();
                Integer satiPred = sharedViewModel.getSatipred().getValue();
                Integer satiLV = sharedViewModel.getSatilv().getValue();
                String slika = sharedViewModel.getSlika().getValue();

                if (ime != null && !ime.isEmpty() && prezime != null && !prezime.isEmpty() && datumRod != null && !datumRod.isEmpty() && predmet != null && !predmet.isEmpty() &&
                        profesor != null && !profesor.isEmpty() && satiPred != null && satiPred >= 0 && satiLV != null && satiLV >= 0 && slika != null && !slika.isEmpty()) {
                    Student student = new Student(ime, prezime, predmet, slika);
                    StudentSingleton.getInstance().setStudenti(student);

                    Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(homeIntent);
                }else {
                    Toast.makeText(getContext(), getString(R.string.toast_popunite_polja), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}