package dev.mariocares.montesanto.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "montesanto", name = "articulo")
public class ArticuloModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "articulo_at", nullable = false)
    @Temporal(TemporalType.DATE)
    Date articuloAt;

    @Column(name = "tipo_id", nullable = false)
    Integer tipo;

    @Column(name = "publicador", nullable = false)
    String publicador;

    @Transient
    private List<String> etiquetas;

    public ArticuloModel() {
    }

    public ArticuloModel(String titulo, String texto, Date articuloAt, Integer tipo, String publicador) {
        this.titulo = titulo;
        this.texto = texto;
        this.articuloAt = articuloAt;
        this.tipo = tipo;
        this.publicador = publicador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getTexto(Integer largo){
        return texto.substring(0, Math.min(100, texto.length()));
    }

    public String getTextoSaltos() {
        return texto.replace("\n", "<br />");
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getArticuloAt() {
        return articuloAt;
    }

    public void setArticuloAt(Date articuloAt) {
        this.articuloAt = articuloAt;
    }

    public Integer getTipo() {
        return tipo;
    }

    public String getTipo(Integer id) {
        return Arrays.stream(ArticuloTipo.values()).filter(
                tipo -> tipo.id().equals(id)
        ).findFirst().get().descripcion();
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getPublicador() {
        return publicador;
    }

    public Persona getPublicador(String nombre){
        return Arrays.stream(Persona.values()).filter(
                persona -> persona.name().equals(nombre)
        ).findFirst().get();
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
    }

    public List<String> etiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
