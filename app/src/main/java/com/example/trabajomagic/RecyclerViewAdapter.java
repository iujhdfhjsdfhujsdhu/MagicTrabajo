package com.example.trabajomagic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Carta> cartas;
    private Context context;
    private boolean isNightMode; // Nuevo atributo para modo noche

    public RecyclerViewAdapter(List<Carta> cartas, Context context, boolean isNightMode) {
        this.cartas = cartas;
        this.context = context;
        this.isNightMode = isNightMode; // Inicializar el modo noche
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
            holder.textViewNombre.setTextColor(context.getResources().getColor(android.R.color.white));
            holder.textViewCardText.setTextColor(context.getResources().getColor(android.R.color.white));
        } else {
            holder.textViewNombre.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.textViewCardText.setTextColor(context.getResources().getColor(android.R.color.black));
        }

        // Mostrar u ocultar el texto dependiendo del estado de 'isExpanded'
        if (carta.isExpanded()) {
            holder.textViewCardText.setVisibility(View.VISIBLE);
            holder.arrowButton.setImageResource(R.drawable.arrow_up);
        } else {
            holder.textViewCardText.setVisibility(View.GONE);
            holder.arrowButton.setImageResource(R.drawable.arrow_down);
        }

        // Manejar el clic en el botón de la flecha
        holder.arrowButton.setOnClickListener(v -> {
            carta.setExpanded(!carta.isExpanded()); // Cambiar el estado de expansión
            notifyItemChanged(position); // Actualizar solo el elemento clickeado
        });
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewCardText;
        ImageView imageViewImagen, arrowButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewCardText = itemView.findViewById(R.id.textViewCardText);
            imageViewImagen = itemView.findViewById(R.id.imageViewImagen);
            arrowButton = itemView.findViewById(R.id.arrowButton);
        }
    }
}
