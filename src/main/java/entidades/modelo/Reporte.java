package entidades.modelo;

import java.math.BigInteger;
import java.util.Date;

public class Reporte {
    private Cooker usuario;
    private MotivoReporte motivo;
    private Date fecha;
    private boolean resuelto;
    private BigInteger idReceta;

    public Reporte(Cooker usuario, MotivoReporte motivo, Date fecha, boolean resuelto, BigInteger idReceta) {
        this.usuario = usuario;
        this.motivo = motivo;
        this.fecha = fecha;
        this.resuelto = resuelto;
        this.idReceta = idReceta;
    }

    public Reporte(Cooker usuario, MotivoReporte motivo, boolean resuelto, BigInteger idReceta) {
        this.usuario = usuario;
        this.motivo = motivo;
        this.resuelto = resuelto;
        this.idReceta = idReceta;
    }

    public Reporte() {
    }

    public Cooker getUsuario() {
        return usuario;
    }

    public void setUsuario(Cooker usuario) {
        this.usuario = usuario;
    }

    public MotivoReporte getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoReporte motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public BigInteger getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(BigInteger idReceta) {
        this.idReceta = idReceta;
    }
}
