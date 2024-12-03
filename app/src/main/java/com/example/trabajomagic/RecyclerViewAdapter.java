package com.example.trabajomagic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Carta> cartas;
    private Context context;
    private boolean isNightMode; // Atributo para modo noche
    private List<Integer> selectedPositions = new ArrayList<>(); // Posiciones seleccionadas

    public RecyclerViewAdapter(List<Carta> cartas, Context context, boolean isNightMode) {
        this.cartas = cartas;
        this.context = context;
        this.isNightMode = isNightMode; // Inicializar el modo noche
        if (cartas == null || cartas.isEmpty()){
            Log.i("Warning", context.getString(R.string.la_lista_est_vac_a_o_es_null));
        }
    }

    // Getter y Setter para actualizar el modo
    public void setNightMode(boolean isNightMode) {
        this.isNightMode = isNightMode;
        notifyDataSetChanged(); // Refrescar la lista
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Carta carta = cartas.get(position);
        holder.textViewNombre.setText(carta.getNombre());
        holder.textViewCardText.setText(carta.getCardText());
        holder.imageViewImagen.setImageResource(carta.getImagenResId());


        // Cambiar colores basados en el modo noche/día
        if (isNightMode) {
            holder.textViewNombre.setTextColor(ContextCompat.getColor(context, R.color.md_theme_surfaceDim));
            holder.textViewCardText.setTextColor(ContextCompat.getColor(context, R.color.md_theme_inverseOnSurface));
        } else {
            holder.textViewNombre.setTextColor(ContextCompat.getColor(context, R.color.md_theme_inverseSurface));
            holder.textViewCardText.setTextColor(ContextCompat.getColor(context, R.color. md_theme_onSurface));
        }


        // Mostrar u ocultar el texto dependiendo del estado de 'isExpanded'
        if (carta.isExpanded()) {
            holder.textViewCardText.setVisibility(View.VISIBLE);
            holder.arrowButton.setImageResource(R.drawable.arrow_up);
        } else {
            holder.textViewCardText.setVisibility(View.GONE);
            holder.arrowButton.setImageResource(R.drawable.arrow_down);
        }
        // Cambiar el fondo si está seleccionado para eliminar
        if (selectedPositions.contains(position)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.md_theme_primaryFixedDim_highContrast));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));

        }
        // Seleccionar carta al pulsar
        holder.itemView.setOnClickListener(v -> {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove((Integer) position); // Quitar selección
            } else {
                selectedPositions.add(position); // Agregar selección
            }
            notifyItemChanged(position); // Actualizar el elemento clickeado
        });
        // Manejar el clic en el botón de la flecha
        holder.arrowButton.setOnClickListener(v -> {
            carta.setExpanded(!carta.isExpanded()); // Cambiar el estado de expansión
            notifyItemChanged(position); // Actualizar solo el elemento clickeado

        });
        // Manejar el clic en el botón del menú contextual
        holder.menudesplegable.setOnClickListener(v -> {
            showpopup(v, position); // Pasar la posición para identificar la carta
        });



    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewCardText;
        ImageView imageViewImagen, arrowButton, menudesplegable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewCardText = itemView.findViewById(R.id.textViewCardText);
            imageViewImagen = itemView.findViewById(R.id.imageViewImagen);
            arrowButton = itemView.findViewById(R.id.arrowButton);
            menudesplegable = itemView.findViewById(R.id.menudesplegable);
        }
    }
    // Método para eliminar las cartas seleccionadas
    public void removeSelectedCards() {
        Log.i("Warning", context.getString(R.string.se_ha_pulsado_el_bot_n_para_borrar_cartas));
        Collections.sort(selectedPositions, Collections.reverseOrder()); // Ordenar posiciones en reversa
        for (int position : selectedPositions) {
            cartas.remove(position); // Eliminar por posición
        }
        selectedPositions.clear(); // Limpiar las selecciones
        notifyDataSetChanged(); // Refrescar el RecyclerView
    }
    // Método para optener las posiciones seleccionadas
    public List<Integer> getSelectedPositions() {
        return new ArrayList<>(selectedPositions);
    }

    public void showpopup(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(context, v);  // Usa 'context' aquí
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.download) {
                    // Acción que ocurre cuando se selecciona "Descargar"
                    String cartaNombre = cartas.get(position).getNombre();

                    // Crear y mostrar un Snackbar
                    Snackbar.make(v, "Descargar carta: " + cartaNombre, Snackbar.LENGTH_SHORT)
                            .setAction("Ver", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Acción adicional si el usuario pulsa en "Ver"
                                    Toast.makeText(context, "Ver detalles de: " + cartaNombre, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
                return true;
            }
        });
        popupMenu.show(); // Mostrar el menú
    }




}
