package dev.mariocares.montesanto.models;

public enum ArticuloTipo {
    DEVOCIONAL("Devocional", 1),
    ESTUDIO("Estudio", 2),
    DOMINICAL("Palabra Dominical", 3);

    private final String descripcion;
    private final Integer id;

    ArticuloTipo(String descripcion, Integer id) {
        this.descripcion = descripcion;
        this.id = id;
    }

    public String descripcion() {
        return descripcion;
    }

    public Integer id() {
        return id;
    }
}
