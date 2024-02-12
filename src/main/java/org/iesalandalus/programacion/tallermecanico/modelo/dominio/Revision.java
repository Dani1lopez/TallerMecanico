package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setFechaFin(fechaInicio);
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula");
        if (!fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la actual");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        if (fechaFin.isEqual(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser igual a la fecha de inicio");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser posterior a la fecha actual");
        }
        this.fechaFin = fechaFin;
    }


    public int getHoras() {
        return horas;
    }

    private void setHoras(int horas) {
        this.horas = horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    private void setPrecioMaterial(float precioMaterial) {
        this.precioMaterial = precioMaterial;
    }

    public int anadirHoras(int horas) {
        Objects.requireNonNull(horas, "Las horas no puede ser nulas");
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas añadidas no debe de ser mayor que cero");
        }
        this.horas += horas;
    }

    public float anadirPrecioMaterial(float precioMaterial) {
       Objects.requireNonNull(precioMaterial,"El precio material no puede ser nulo");
       if (precioMaterial <= 0) {
          throw new IllegalArgumentException("El precio material añadido  debe de ser mayor que cero");
       }
       this.precioMaterial += precioMaterial;

    }
}
