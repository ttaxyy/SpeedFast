package model;

public class Direccion {
    private String region;
    private String comuna;
    private String calle;
    private int numero;

    public Direccion(String region, String comuna, String calle, int numero) {
        this.region = region;
        this.comuna = comuna;
        this.calle = calle;
        this.numero = numero;
    }

    public String getRegion() {return region;}
    public void setRegion(String region) {this.region = region;}

    public String getComuna() {return comuna;}
    public void setComuna(String comuna) {this.comuna = comuna;}

    public String getCalle() {return calle;}
    public void setCalle(String calle) {this.calle = calle;}

    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}

    @Override
    public String toString() {
        return String.format(calle + " " + numero + ", " + comuna + ", " + region);
    }
}
