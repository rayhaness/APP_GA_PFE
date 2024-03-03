package com.example.app_ga_pfe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;
public class EmploiTempsFragment extends Fragment {
    TextView t1, t2, t3, t4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emploi_temps, container, false);
        t1 = view.findViewById(R.id.t1);
        t2 = view.findViewById(R.id.t2);
        t3 = view.findViewById(R.id.t3);
        t4 = view.findViewById(R.id.t4);

        Intent intent = getActivity().getIntent();
        long selectedFiliereId = intent.getLongExtra("idFilieres", -1);
        int selectedRadioButtonId = intent.getIntExtra("radiobutton_id", -1);

        // Récupérer les données de la base de données en fonction des sélections de l'utilisateur
        getDataFromDatabase(selectedFiliereId, selectedRadioButtonId);

        return view;
    }

    private void displayEmploiData(List<EmploiTempsClass> emploiDataList) {
        // Vérifier si la liste est vide
        if (emploiDataList != null && !emploiDataList.isEmpty()) {
            // Obtenir le premier objet EmploiTempsClass de la liste
            EmploiTempsClass emploiData = emploiDataList.get(0);
            // Mettre à jour les TextView avec les données récupérées
            t1.setText(emploiData.getMatiere1());
            t2.setText(emploiData.getMatiere2());
            t3.setText(emploiData.getMatiere3());
            t4.setText(emploiData.getMatiere4());
        }
    }

    private void getDataFromDatabase(long filiereId, int Radio) {
        Emploi_TempsBD emploiTempsBD = new Emploi_TempsBD(getActivity());
        List<EmploiTempsClass> emploiDataList = emploiTempsBD.getEmploiData(filiereId, Radio);
        displayEmploiData(emploiDataList);
    }
}