package model;

public class Repartidor {
    private String nombreRepartidor;
    private boolean tieneMochila;

    public Repartidor(String nombreRepartidor, boolean tieneMochila) {
        this.nombreRepartidor = nombreRepartidor;
        this.tieneMochila = tieneMochila;
    }

    public String getNombreRepartidor() {return nombreRepartidor;}
    public void setNombreRepartidor(String nombreRepartidor) {this.nombreRepartidor = nombreRepartidor;}

    public boolean isTieneMochila() {return tieneMochila;}
    public void setTieneMochila(boolean tieneMochila) {this.tieneMochila = tieneMochila;}
}
