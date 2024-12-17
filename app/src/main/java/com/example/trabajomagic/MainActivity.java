package com.example.trabajomagic;

import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private boolean isNightMode = false; // Estado inicial del modo noche

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa y carga el fragmento de lista de cartas
        CartaListFragment cartaListFragment = new CartaListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, cartaListFragment); // Usa el ID correcto
        fragmentTransaction.commit();


        // Configura el switch para alternar entre modos día/noche
        Switch switchDayNight = findViewById(R.id.switchDayNight);
        switchDayNight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isNightMode = isChecked;

            // Cambiar el fondo del layout principal
            if (isNightMode) {
                findViewById(R.id.main).setBackgroundResource(R.drawable.background_magic_night);
            } else {
                findViewById(R.id.main).setBackgroundResource(R.drawable.background_magic_day);
            }

            // Notifica al fragmento sobre el cambio de modo noche
            cartaListFragment.updateNightMode(isNightMode);
        });
    }
}
