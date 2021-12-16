package dev.mariocares.montesanto.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "montesanto", name = "cancion")
public class CancionModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "pagina", nullable = false)
    private Integer pagina;

    public CancionModel(){}

    public CancionModel(String titulo, String texto, Integer numero, Integer pagina) {
        this.titulo = titulo;
        this.texto = texto;
        this.numero = numero;
        this.pagina = pagina;
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

    public String getTextoSaltos() {
        return texto.replace("\n", "<br />");
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }
}
