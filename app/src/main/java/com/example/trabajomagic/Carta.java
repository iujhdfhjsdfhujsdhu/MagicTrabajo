package com.example.trabajomagic;

public class Carta {
    private String nombre; // Nombre de la carta
    private String cardText; // Descripción de la carta
    private int imagenResId; // Imagen de la carta
    private boolean isExpanded; // Si el texto está expandido o no

    public Carta(String nombre, String cardText, int imagenResId) {
        this.nombre = nombre;
        this.cardText = cardText;
        this.imagenResId = imagenResId;
        this.isExpanded = false; // Estado inicial: no expandida
    }

    public String getNombre() {
        return nombre;
    }

    public String getCardText() {
        return cardText;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
