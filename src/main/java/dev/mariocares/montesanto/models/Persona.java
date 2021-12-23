package dev.mariocares.montesanto.models;

public enum Persona {
    JCABEZAS("Jéssica Cabezas", "jcabezas.png"),
    MCARESB("Mario Cares Sr.", "mcaresb.png"),
    MCARESC("Mario Cares", "mcaresc.jpeg"),
    PBAIGORRI("Pedro Baigorrí", "pbaiborri.png"),
    RPARDO("Rilma Pardo", "rpardo.png"),
    VCARES("Verónica Cares", "vcares.png");

    private final String nombre, avatar;

    Persona(String nombre, String avatar) {
        this.nombre = nombre;
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAvatar() {
        return avatar;
    }
}
