package modelo;

import java.io.Serializable;
import java.util.Date;

public class Asistencia implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
    private Date fechaLogueo;
    private boolean justificado;
    private int minutosRetrazo;

    protected Asistencia()
    {
    }

    public Asistencia(Date fechaLogueo, boolean justificado, int minutosRetrazo)
    {
        this.fechaLogueo = fechaLogueo;
        this.justificado = justificado;
        this.minutosRetrazo = minutosRetrazo;
    }

    public Date getFechaLogueo()
    {
        return fechaLogueo;
    }

    public void setFechaLogueo(Date fechaLogueo)
    {
        this.fechaLogueo = fechaLogueo;
    }

    public long getId()
    {
        return id;
    }

    protected void setId(long id)
    {
        this.id = id;
    }

    public boolean isJustificado()
    {
        return justificado;
    }

    public void setJustificado(boolean justificado)
    {
        this.justificado = justificado;
    }

    public int getMinutosRetrazo()
    {
        return minutosRetrazo;
    }

    public void setMinutosRetrazo(int minutosRetrazo)
    {
        this.minutosRetrazo = minutosRetrazo;
    }

    @Override
    public String toString()
    {
        return "Asistencia{" + "id=" + id + ", fechaLogueo=" + fechaLogueo + ", justificado=" + justificado + ", minutosRetrazo=" + minutosRetrazo + '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Asistencia other = (Asistencia) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (this.fechaLogueo != other.fechaLogueo && (this.fechaLogueo == null || !this.fechaLogueo.equals(other.fechaLogueo)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + (this.fechaLogueo != null ? this.fechaLogueo.hashCode() : 0);
        return hash;
    }
}
