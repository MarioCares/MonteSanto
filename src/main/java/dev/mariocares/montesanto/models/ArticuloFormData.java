package dev.mariocares.montesanto.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArticuloFormData {
    @NotNull(message = "El artículo debe tener un título")
    @Size(min = 3, max = 255, message = "El título debe tener entre 3 y 255 caracteres")
    private String titulo;

    @NotNull(message = "El artículo debe tener contenido")
    @Size(min = 50, message = "El artículo debe tener al menos 50 caracteres")
    private String texto;

    private Integer tipo;
    private String articuloAt, publicador;

    public ArticuloFormData() {
    }

    public ArticuloFormData(String titulo, String texto, Integer tipo, String publicador, String articuloAt) {
        this.titulo = titulo;
        this.texto = texto;
        this.tipo = tipo;
        this.publicador = publicador;
        this.articuloAt = articuloAt;
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

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getPublicador() {
        return publicador;
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
    }

    public String getArticuloAt() {
        return articuloAt;
    }

    public void setArticuloAt(String articuloAt) {
        this.articuloAt = articuloAt;
    }

    public ArticuloModel toModel() throws ParseException {
        return new ArticuloModel(
            this.titulo,
            this.texto,
            new SimpleDateFormat("yyyy-MM-dd").parse(this.articuloAt),
            this.tipo,
            this.publicador
        );
    }

    @Override
    public String toString() {
        return "ArticuloFormData{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", tipo=" + tipo +
                ", articuloAt='" + articuloAt + '\'' +
                ", publicador='" + publicador + '\'' +
                '}';
    }
}
