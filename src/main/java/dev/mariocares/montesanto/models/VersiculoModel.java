package dev.mariocares.montesanto.models;

import javax.persistence.*;

@Entity
@Table(name = "versiculo", schema = "montesanto", indexes = {
    @Index(name = "idx_versiculomodel_versiculo", columnList = "versiculo"),
    @Index(name = "idx_versiculomodel_capitulo", columnList = "capitulo")
})
public class VersiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "libro_id", nullable = false)
    private Long libro;

    @Column(name = "capitulo", nullable = false)
    private Integer capitulo;

    @Column(name = "versiculo", nullable = false)
    private Integer versiculo;

    @Column(name = "texto", nullable = false)
    private String texto;

    public VersiculoModel() {
    }

    public VersiculoModel(Long id, Long libro, Integer capitulo, Integer versiculo, String texto) {
        this.id = id;
        this.libro = libro;
        this.capitulo = capitulo;
        this.versiculo = versiculo;
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibro() {
        return libro;
    }

    public void setLibro(Long libro) {
        this.libro = libro;
    }

    public Integer getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Integer capitulo) {
        this.capitulo = capitulo;
    }

    public Integer getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(Integer versiculo) {
        this.versiculo = versiculo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Transient
    public LibroModel libroModel;
}