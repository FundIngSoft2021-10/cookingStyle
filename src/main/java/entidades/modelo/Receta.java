package entidades.modelo;

import java.util.List;

public class Receta {
    private int idReceta;
    private String nombre;
    private String descripcion;
    private boolean videoReceta;
    private String linkVideo;
    private TipoVideo tipoVideo;
    private boolean imagenReceta;
    private String linkImagen;
    private List<LineaIngrediente> lineasIngrediente;
    private List<PasoReceta> pasosReceta;
    private List<Categoria> categorias;
    private List<Calificacion> calificaciones;
    private List<Reporte> reportes;

    public Receta(int idReceta, String nombre, String descripcion, boolean videoReceta, String linkVideo, TipoVideo tipoVideo,
                  boolean imagenReceta, String linkImagen, List<LineaIngrediente> lineasIngrediente, List<PasoReceta> pasosReceta, List<Categoria> categorias,
                  List<Calificacion> calificaciones, List<Reporte> reportes) {
        this.idReceta = idReceta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.videoReceta = videoReceta;
        this.linkVideo = linkVideo;
        this.tipoVideo = tipoVideo;
        this.imagenReceta = imagenReceta;
        this.linkImagen = linkImagen;
        this.lineasIngrediente = lineasIngrediente;
        this.pasosReceta = pasosReceta;
        this.categorias = categorias;
        this.calificaciones = calificaciones;
        this.reportes = reportes;
    }

    public Receta() {
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isVideoReceta() {
        return videoReceta;
    }

    public void setVideoReceta(boolean videoReceta) {
        this.videoReceta = videoReceta;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public TipoVideo getTipoVideo() {
        return tipoVideo;
    }

    public void setTipoVideo(TipoVideo tipoVideo) {
        this.tipoVideo = tipoVideo;
    }

    public boolean isImagenReceta() {
        return imagenReceta;
    }

    public void setImagenReceta(boolean imagenReceta) {
        this.imagenReceta = imagenReceta;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public List<LineaIngrediente> getLineasIngrediente() {
        return lineasIngrediente;
    }

    public void setLineasIngrediente(List<LineaIngrediente> lineasIngrediente) {
        this.lineasIngrediente = lineasIngrediente;
    }

    public List<PasoReceta> getPasosReceta() {
        return pasosReceta;
    }

    public void setPasosReceta(List<PasoReceta> pasosReceta) {
        this.pasosReceta = pasosReceta;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    // Métodos

    public float calcularCalificacion() {
        if (this.calificaciones.size() == 0) {
            return -1;
        }

        float total = 0;
        for (Calificacion calificacion : this.calificaciones) {
            total += calificacion.getValor();
        }
        return total / this.calificaciones.size();
    }
}
