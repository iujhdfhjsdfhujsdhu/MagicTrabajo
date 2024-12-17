package com.example.trabajomagic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OtraCartaListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    public OtraCartaListFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View rootView = inflater.inflate(R.layout.fragmentcards, container, false);

        // Inicializar RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lista de cartas diferentes
        List<Carta> cartas = new ArrayList<>();
        // Agrega las cartas necesarias para este fragmento
        cartas.add(new Carta("Tarmogoyf", "Tarmogoyf's power is equal to the number of card types in all graveyards.", R.drawable.tarmogoyf));
        // Otras cartas...

        // Configurar el adaptador
        adapter = new RecyclerViewAdapter(cartas, getContext(), false);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    // Método para actualizar el modo noche
    public void updateNightMode(boolean isNightMode) {
        if (adapter != null) {
            adapter.setNightMode(isNightMode);
        }
    }
}
