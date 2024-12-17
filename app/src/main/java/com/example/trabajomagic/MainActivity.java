package com.example.trabajomagic;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private boolean isNightMode = false; // Estado inicial del modo noche

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        });

        // Obtiene referencias a TabLayout y FrameLayout
        TabLayout tabLayout = findViewById(R.id.TabLayout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Inicializa el fragmento por defecto (puede ser el fragmento principal de cartas)
        CartaListFragment cartaListFragment = new CartaListFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, cartaListFragment);
        fragmentTransaction.commit();

        // Configura el cambio de fragmentos al seleccionar un tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;

                // Cambiar el fragmento según el tab seleccionado
                switch (tab.getPosition()) {
                    case 0: // Primer tab
                        selectedFragment = new CartaListFragment(); // Fragmento de cartas principales
                        break;
                    case 1: // Segundo tab
                        selectedFragment = new OtraCartaListFragment(); // Fragmento de cartas adicionales
                        break;
                }

                // Cambiar el fragmento en el FrameLayout
                if (selectedFragment != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, selectedFragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // No se requiere realizar ninguna acción al deseleccionar un tab
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No se requiere realizar ninguna acción al re-seleccionar un tab
            }
        });
    }
    private RecyclerView recyclerView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Cambiar la orientación del RecyclerView cuando cambie la orientación del dispositivo
        updateRecyclerViewOrientation(newConfig.orientation);
    }

    private void updateRecyclerViewOrientation(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Si la pantalla está en modo horizontal (landscape), usar orientación horizontal
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        } else {
            // Si la pantalla está en modo vertical (portrait), usar orientación vertical
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
    }
}
