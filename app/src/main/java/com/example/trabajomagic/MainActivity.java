package com.example.trabajomagic;

import static android.R.color.holo_red_light;
import com.example.trabajomagic.R;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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
        deleteButton.setOnClickListener(v -> {
            int selectedCount = adapter.getSelectedPositions().size();

            // Eliminar cartas seleccionadas
            adapter.removeSelectedCards();

            // Toast de borrar
            if (selectedCount > 0) {
                // Usar string "message" para mostrar el número de cartas eliminadas
                String message = getString(R.string.toast_deleted_cards, selectedCount);
                // Crear un TextView para el Toast
                TextView textoDelToast = new TextView(MainActivity.this);
                textoDelToast.setText(message);
                textoDelToast.setTextSize(18); // Tamaño del texto
                textoDelToast.setPadding(16, 16, 16, 16); // Padding
                textoDelToast.setBackgroundColor(getResources().getColor(holo_red_light)); // Color de fondo
                textoDelToast.setTextColor(getResources().getColor(android.R.color.white)); // Color de la letra

                Toast toast = new Toast(MainActivity.this);
                toast.setView(textoDelToast);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            } else {
                // Mensaje de No cartas seleccionadas
                Toast.makeText(this, getString(R.string.toast_no_cards_selected), Toast.LENGTH_SHORT).show();
            }
        });
        TabLayout tabLayout = findViewById(R.id.TabLayout);


// Listener de selección de pestañas
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                // Acción al seleccionar una pestaña
                Log.d("TabLayout", "Tab seleccionada: " + tab.getText());

                // Mostrar Snackbar al seleccionar una pestaña
                String message = "Tab seleccionada: " + tab.getText();
                Snackbar snackbar = Snackbar.make(tabLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Acción al deseleccionar una pestaña
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Acción al volver a seleccionar una pestaña
            }
        });



    }




}
