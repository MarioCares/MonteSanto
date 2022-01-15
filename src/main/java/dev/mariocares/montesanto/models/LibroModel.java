package dev.mariocares.montesanto.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "montesanto", name = "libro")
public class LibroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "testamento", nullable = false)
    private String testamento;

    @Transient
    private List<String> abreviaturas;

    @Transient
    private Integer capitulo;

    public LibroModel() {
    }

    public LibroModel(Long id, String nombre, String testamento) {
        this.id = id;
        this.nombre = nombre;
        this.testamento = testamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTestamento() {
        return testamento;
    }

    public void setTestamento(String testamento) {
        this.testamento = testamento;
    }

    public List<String> getAbreviaturas() {
        return abreviaturas;
    }

    public String getAbreviaturas(Boolean juntas){
        return String.join(", ", this.abreviaturas);
    }

    public void setAbreviaturas(List<String> abreviaturas) {
        this.abreviaturas = abreviaturas;
    }

    public Integer getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Integer capitulo) {
        this.capitulo = capitulo;
    }
}
