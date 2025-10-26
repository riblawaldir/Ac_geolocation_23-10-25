package com.tuempresa.ap_geolocation_23_10_25;

public class LugarTuristico {
    private String nombre;
    private String descripcion;
    private int imagenResId;
    private double lat;
    private double lng;

    public LugarTuristico(String nombre, String descripcion, int imagenResId, double lat, double lng) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getImagenResId() { return imagenResId; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
}
