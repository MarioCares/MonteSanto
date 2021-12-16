package dev.mariocares.montesanto.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CancionFormData {
    @NotNull(message = "La canción debe tener un número")
    private Integer numero;

    @NotNull(message = "La canción debe estar en una página")
    private Integer pagina;

    @NotNull(message = "La canción debe tener un título")
    @Size(min = 3, max = 255)
    private String titulo;

    @NotNull(message = "La canción debe tener contenido")
    @Size(min = 50, message = "La canción debe tener al menos 50 caracteres")
    private String texto;

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

    public CancionModel toModel(){
        return new CancionModel(titulo, texto, numero, pagina);
    }
}
