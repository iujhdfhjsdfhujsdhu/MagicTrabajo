package com.example.trabajomagic;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean isNightMode = false; // Estado inicial del modo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de cartas
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("Ulamog, el hambre que no cesa", "Indestructible. Exilia dos permanentes al lanzar.", R.drawable.ulamog));
        cartas.add(new Carta("Bosque", "Permite agregar un maná verde. Subtipo: Bosque.", R.drawable.bosque));
        cartas.add(new Carta("Acto Blasfemo", "Hace 13 puntos de daño a cada criatura.", R.drawable.acto_blasfemo));
        cartas.add(new Carta("Shivan Dragon", "Flying, Firebreathing", R.drawable.shivan_dragon));
        cartas.add(new Carta("Black Lotus", "Sacrifice Black Lotus: Add three mana of any one color.", R.drawable.black_lotus));
        cartas.add(new Carta("Llanowar Elves", "Tap: Add one green mana.", R.drawable.llanowar_elves));
        cartas.add(new Carta("Lightning Bolt", "Deal 3 damage to any target.", R.drawable.lightning_bolt));
        cartas.add(new Carta("Counterspell", "Counter target spell.", R.drawable.counterspell));
        cartas.add(new Carta("Tarmogoyf", "Tarmogoyf's power is equal to the number of card types in all graveyards.", R.drawable.tarmogoyf));
        cartas.add(new Carta("Serra Angel", "Flying, Vigilance", R.drawable.serra_angel));

        // Adaptador
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(cartas, this, isNightMode);
        recyclerView.setAdapter(adapter);

        // Switch para alternar modos
        Switch switchDayNight = findViewById(R.id.switchDayNight);
        switchDayNight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isNightMode = isChecked;

            // Cambiar fondo del layout
            if (isNightMode) {
                findViewById(R.id.main).setBackgroundResource(R.drawable.background_magic_night);
            } else {
                findViewById(R.id.main).setBackgroundResource(R.drawable.background_magic_day);
            }
            adapter.setNightMode(isNightMode); // Informar al adaptador
        });
        // Botón para eliminar cartas seleccionadas
        Button deleteButton = findViewById(R.id.button);
        deleteButton.setOnClickListener(v -> adapter.removeSelectedCards());
    }
}
