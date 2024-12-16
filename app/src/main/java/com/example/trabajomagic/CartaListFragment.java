package com.example.trabajomagic;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CartaListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    public CartaListFragment() {
        // Requiere un constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragment
        View rootView = inflater.inflate(R.layout.fragmentcards, container, false);

        // Inicializar RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lista de cartas
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Ulamog, the Ceaseless Hunger", "Indestructible. Exile two permanents when cast.", R.drawable.ulamog));
        cartas.add(new Carta("Forest", "Allows adding one green mana. Subtype: Forest.", R.drawable.bosque));
        cartas.add(new Carta("Blasphemous Act", "Deals 13 damage to each creature.", R.drawable.acto_blasfemo));
        cartas.add(new Carta("Shivan Dragon", "Flying, Firebreathing", R.drawable.shivan_dragon));
        cartas.add(new Carta("Black Lotus", "Sacrifice Black Lotus: Add three mana of any one color.", R.drawable.black_lotus));
        cartas.add(new Carta("Llanowar Elves", "Tap: Add one green mana.", R.drawable.llanowar_elves));
        cartas.add(new Carta("Lightning Bolt", "Deal 3 damage to any target.", R.drawable.lightning_bolt));
        cartas.add(new Carta("Counterspell", "Counter target spell.", R.drawable.counterspell));
        cartas.add(new Carta("Tarmogoyf", "Tarmogoyf's power is equal to the number of card types in all graveyards.", R.drawable.tarmogoyf));
        cartas.add(new Carta("Serra Angel", "Flying, Vigilance", R.drawable.serra_angel));

        // Adaptador para RecyclerView
        adapter = new RecyclerViewAdapter(cartas, getContext(), false); // Guardar el adaptador en una variable
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    // Método público para obtener el adaptador
    public RecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void updateNightMode(boolean isNightMode) {
    }
}
